package com.facegram.facegrambackend.authentication.auth

import com.facegram.facegrambackend.authentication.jwt.JwtHeader
import com.facegram.facegrambackend.authentication.jwt.JwtService
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import javax.servlet.http.HttpServletRequest


@Component
class UserIdResolver constructor(
    private val jwtService: JwtService,

): HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(UserId::class.java) && Long::class.java == parameter.parameterType
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        val request: HttpServletRequest = webRequest.nativeRequest as HttpServletRequest
        val token: String = request.getHeader(JwtHeader.AUTH)

        if(!jwtService.verifyToken(token)){
            return null
        }

        val subject: String = jwtService.getSubject(token)

        return try {
            subject.toLong()
        }catch (e: NotAuthException){
            null
        }
    }
}