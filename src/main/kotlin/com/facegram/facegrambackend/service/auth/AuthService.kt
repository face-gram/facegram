package com.facegram.facegrambackend.service.auth

import com.facegram.facegrambackend.domain.user.UserRepository
import com.facegram.facegrambackend.security.CustomUserDetails
import com.facegram.facegrambackend.security.oauth2.jwt.JwtTokenProvider
import com.facegram.facegrambackend.util.CookieUtil
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Service
class AuthService
constructor(
    @Value("\${app.auth.token.refresh-cookie-key}")
    private val cookieKey: String,

    private val userRepository: UserRepository,
    private val jwtTokenProvider: JwtTokenProvider
){
    // refresh token을 위한 서비스
    fun refreshToken(response: HttpServletResponse,
                     request: HttpServletRequest,
                     oldAccessToken: String): String{
        // 1. 리프레쉬 토큰 검증
        val oldRefreshToken: String = CookieUtil.getCookie(request, cookieKey)
            .map { cookie -> cookie.value }.orElseThrow()

        if(!jwtTokenProvider.validateToken(oldAccessToken)){
            throw IllegalArgumentException("유효하지 않은 리프레쉬 토큰입니다.")
        }

        // 2. 유저 정보 얻기
        val authentication: Authentication = jwtTokenProvider.getAuthentication(oldAccessToken)
        val user : CustomUserDetails = authentication.principal as CustomUserDetails

        val id: Long = user.name.toLong()

        // 리프레쉬 토큰이랑 맞춰보기
        val savedToken: String = userRepository.getRefreshTokenById(id)

        if(savedToken != oldRefreshToken){
            throw IllegalArgumentException("맞지 않는 리프레쉬 토큰입니다.")
        }

        // 4. JWT 갱신
        val accessToken: String = jwtTokenProvider.createAccessToken(authentication)
        jwtTokenProvider.createRefreshToken(authentication,response)
        return accessToken

    }
}