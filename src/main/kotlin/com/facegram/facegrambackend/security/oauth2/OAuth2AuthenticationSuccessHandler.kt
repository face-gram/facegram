package com.facegram.facegrambackend.security.oauth2

import com.facegram.facegrambackend.security.oauth2.CookieAuthorizationRequestRepository.Companion.REDIRECT_URI_PARAM_COOKIE_NAME
import com.facegram.facegrambackend.security.oauth2.jwt.JwtTokenProvider
import com.facegram.facegrambackend.util.CookieUtil
import com.facegram.facegrambackend.exceptionhandler.exception.NotMatchRedirectURIException
import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI
import java.util.Optional
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

//OAuth2 로그인 성공시 호출되는 Handler
//로그인에 성공하면 JWT를 생성한 다음 authorizedRedirectUri로 client에게 전송한다

@Slf4j
@ConstructorBinding
@Component
class OAuth2AuthenticationSuccessHandler(
    @Value("\${app.oauth2.authorizedRedirectUri}")
    private val redirectUri: String,
    private val tokenProvider: JwtTokenProvider,
    private val cookieAuthorizationRequestRepository: CookieAuthorizationRequestRepository
): SimpleUrlAuthenticationSuccessHandler() {
    val log: Logger = LoggerFactory.getLogger(OAuth2AuthenticationSuccessHandler::class.java)

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication

    ) {
        val targetUrl: String = determineTargetUrl(request, response, authentication)

        if(response.isCommitted){
            log.info("이미 리스폰스가 커밋되었습니다.")
            return
        }
        clearAuthenticationAttributes(request,response)
        redirectStrategy.sendRedirect(request,response,targetUrl)


    }

    protected override fun determineTargetUrl(request: HttpServletRequest?,
                                              response: HttpServletResponse?,
                                              authentication: Authentication?)
    :String{
        println("성공 핸들러 도착")
        if (request!=null){
            val redirectUri: Optional<String> = CookieUtil.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
                .map { cookie -> cookie.value }

            if(redirectUri.isPresent && !isAuthorizedRedirectUri(redirectUri.get())){
                throw NotMatchRedirectURIException("리다이렉트URI가 맞지 않습니다.")
            }

            val targetUrl: String = redirectUri.orElse(defaultTargetUrl)

            // Jwt 생성
            val accessToken: String = tokenProvider.createAccessToken(authentication)
            tokenProvider.createRefreshToken(authentication,response)

            return UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam("accessToken",accessToken)
                .build().toUriString()
        }
        throw IllegalArgumentException("request가 비어있습니다.")
    }

    protected fun clearAuthenticationAttributes(request: HttpServletRequest?,response: HttpServletResponse?){
        super.clearAuthenticationAttributes(request)
        cookieAuthorizationRequestRepository.removeAuthorizationRequestCookies(request,response)
    }

    private fun isAuthorizedRedirectUri(uri:String):Boolean{
        val clientRedirectUri: URI = URI.create(uri)
        val authorizedUri: URI = URI.create(redirectUri)

        if(authorizedUri.host.equals(clientRedirectUri.host, ignoreCase = true)
            && authorizedUri.port == clientRedirectUri.port){
            return true
        }
        return false

    }





}