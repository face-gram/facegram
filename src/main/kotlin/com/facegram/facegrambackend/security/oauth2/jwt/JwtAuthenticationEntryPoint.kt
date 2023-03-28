package com.facegram.facegrambackend.security.oauth2.jwt

import com.facegram.facegrambackend.exceptionhandler.exception.UnAuthorizedException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationEntryPoint
constructor(): AuthenticationEntryPoint
{
    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {
//        throw UnAuthorizedException("사용자 인증 혹은 로그인이 없이 요청하셨습니다.")
        response!!.sendError(HttpServletResponse.SC_UNAUTHORIZED,"사용자 인증 혹은 로그인이 없이 요청하셨습니다.")
    }

}