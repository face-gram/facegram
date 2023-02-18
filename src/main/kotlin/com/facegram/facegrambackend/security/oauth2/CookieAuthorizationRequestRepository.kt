package com.facegram.facegrambackend.security.oauth2

import antlr.StringUtils
import com.facegram.facegrambackend.util.CookieUtil
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


//Provider와의 Authorization 과정에서 Authorization request를 cookie에 저장하기 위한 클래스
//oauth2_auth_request 쿠키 : 해당 Authorizaion request의 고유 아이디를 담는다
//redirect_uri 쿠키 : 해당 Authorization request시 파라미터로 넘어온 redirect_uri를 담는다.
// 이 쿠키는 나중에 applcation.yml의 authorizedRedirectUri와 일치하는지 확인시 사용된다
//http://localhost:8080/oauth2/authorize/google?redirect_uri=http://localhost:3000/oauth2/redirect
//인증 요청시 생성된 2개의 쿠키는 인증이 종료될 때 실행되는 successHandler와 failureHandler에서 제거된다
//2개의 쿠키 유효시간은 180초로 유효시간 내에 인증요청을 다시하면 만들어졌던 쿠키를 다시 사용한다
@Component
class CookieAuthorizationRequestRepository(
    
): AuthorizationRequestRepository<OAuth2AuthorizationRequest> {

    companion object{
        val OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME: String = "oauth2_auth_request"
        val REDIRECT_URI_PARAM_COOKIE_NAME: String = "redirect_uri"
        private const val COOKIE_EXPIRE_SECONDS: Int = 180
    }



    override fun loadAuthorizationRequest(request: HttpServletRequest?): OAuth2AuthorizationRequest {
        return CookieUtil.getCookie(request!!, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME)
            .map { cookie ->
                CookieUtil.deserialize(
                    cookie,
                    OAuth2AuthorizationRequest::class.java
                )
            }
            .orElse(null)


    }

    override fun saveAuthorizationRequest(
        authorizationRequest: OAuth2AuthorizationRequest?,
        request: HttpServletRequest,
        response: HttpServletResponse?
    ) {
        if (authorizationRequest == null) {
            CookieUtil.deleteCookie(
                request,
                // null은 절대 안된다.
                response!!,
                OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME
            )
            CookieUtil.deleteCookie(request,
                response,
                REDIRECT_URI_PARAM_COOKIE_NAME)
            return
        }
        CookieUtil.addCookie(
            response!!,
            OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME,
            CookieUtil.serialize(authorizationRequest),
            COOKIE_EXPIRE_SECONDS
        )
        val redirectUriAfterLogin = request.getParameter(REDIRECT_URI_PARAM_COOKIE_NAME)
        if (redirectUriAfterLogin.isNotBlank()) {
            CookieUtil.addCookie(response, REDIRECT_URI_PARAM_COOKIE_NAME, redirectUriAfterLogin, COOKIE_EXPIRE_SECONDS)
        }
    }

    override fun removeAuthorizationRequest(request: HttpServletRequest?): OAuth2AuthorizationRequest? {
        return loadAuthorizationRequest(request)
    }

    fun removeAuthorizationRequestCookies(request: HttpServletRequest?, response: HttpServletResponse?) {
        CookieUtil.deleteCookie(
            request!!,
            response!!,
            OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME
        )
        CookieUtil.deleteCookie(request, response, REDIRECT_URI_PARAM_COOKIE_NAME)
    }
}