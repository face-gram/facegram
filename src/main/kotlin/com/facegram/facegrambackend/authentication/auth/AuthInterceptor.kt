package com.facegram.facegrambackend.authentication.auth

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthInterceptor constructor(
    private val loginCheckHandler: LoginCheckHandler
): HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if(handler !is HandlerMethod){
            return true // 없어도 뒤에 진행되야지
        }

        val handlerMethod: HandlerMethod = handler as HandlerMethod
        // 여기서 어노테이션 검증, 어노테이션 가져올 떄는 ::class.java
        var auth: Auth? = handlerMethod.getMethodAnnotation(Auth::class.java) ?: return true

        loginCheckHandler.getUserId(request)
        return true

    }
}