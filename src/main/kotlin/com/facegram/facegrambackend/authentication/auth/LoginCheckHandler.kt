package com.facegram.facegrambackend.authentication.auth

import com.facegram.facegrambackend.authentication.jwt.JwtHeader
import com.facegram.facegrambackend.authentication.jwt.JwtService
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Component
import javax.security.auth.Subject
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest

@RequiredArgsConstructor
@Component
class LoginCheckHandler constructor(
    private val jwtService: JwtService
) {

    fun getUserId(request: HttpServletRequest): Long{
        val token: String = request.getHeader(JwtHeader.AUTH)

        if (jwtService.verifyToken(token)){
            val subject: String = jwtService.getSubject(token)
            return convertToUserId(subject)
        }else{
            throw NotAuthException()
        }
    }

    private fun convertToUserId(subject: String): Long{
        try{
            return subject.toLong()
        }catch (e: NotAuthException){
            throw NotAuthException()
        }
    }

}