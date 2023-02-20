package com.facegram.facegrambackend.security.oauth2

import com.facegram.facegrambackend.util.CookieUtil
import org.springframework.security.core.AuthenticationException
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

//OAuth2 로그인 실패시 호출되는 Handler
//인증요청시 생성된 쿠키들을 삭제하고 error를 담아 보낸다
@Component
class OAuth2AuthenticationFailureHandler(
    private val authorizationRequestRepository: CookieAuthorizationRequestRepository
): SimpleUrlAuthenticationFailureHandler() {

    override fun onAuthenticationFailure(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        exception: AuthenticationException?
    ) {
        var targetUrl: String = CookieUtil.getCookie(request!!,"redirect_uri")
            .map{cookie -> cookie.value}.orElse("/")

        targetUrl = exception?.let {
            UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam("error", it.localizedMessage)
                .build().toUriString()
        }.toString()

        // 쿠키 삭제
        authorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }


}