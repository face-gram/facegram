package com.facegram.facegrambackend.security.oauth2.jwt

import antlr.StringUtils
import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

// 모든 Request에서 JWT를 검사하는 필터
// Http Request header에 Authorization : Bearer <JWT> 형태로 전송된 Access Token을 검사하고 유효한다면 TokenProvider에서 생성된 Authentication 객체를 SecurityContext에 저장한다
// 여기서 SecurityContext에 저장된 Authentication 정보는 Controller에서 @AuthenticationPrincipal로 꺼내 사용가능하다
// tokenProvider.getAuthentication()에서 제공된 class타입과 @AuthenticationPrincipal에서 사용하는 class 타입이 일치해야 한다
@Slf4j
@Component
class JwtAuthenticationFilter
constructor(
    private val tokenProvider: JwtTokenProvider
    // 필터가 두 번 실행되는 것을 방지하기 위한 필터
    // 모든 서블릿에 일관된 요청을 처리하기 위해 만들어진 필터이다.
):OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain // securityFilterChain
    ) {
        val log: Logger = LoggerFactory.getLogger(JwtAuthenticationFilter::class.java)

        val token: String? = parseBearerToken(request)

        if (token != null) {
            if (token.isNotEmpty() && tokenProvider.validateToken(token)){
                val authentication: Authentication = tokenProvider.getAuthentication(token)
                val context: SecurityContext = SecurityContextHolder.getContext()
                context.authentication = authentication
                log.info("인증정보 저장 => ",authentication.name)
                println("인증필터 도착")
            }else{
                log.debug("유효한 JWT 토큰이 없습니다.")
            }
        }else{
            log.debug("유효한 JWT 토큰이 없습니다.")
        }
        filterChain.doFilter(request,response)
    }

    private fun parseBearerToken(request: HttpServletRequest): String? {

        val bearerToken: String = request.getHeader("Authorization")

        // Validation Access Token
        if(bearerToken.isNotEmpty() && bearerToken.startsWith("Bearer ") ){
            return bearerToken.substring(7)
        }
        return null
    }
}