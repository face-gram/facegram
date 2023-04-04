package com.facegram.facegrambackend.security.oauth2.jwt

import com.facegram.facegrambackend.exceptionhandler.exception.NoAuthorizationException
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAccessDeniedHandler:AccessDeniedHandler {
    override fun handle(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        accessDeniedException: AccessDeniedException?
    ) {
        response!!.sendError(HttpServletResponse.SC_UNAUTHORIZED,"권한이 없는 사용자입니다.")
    }
}