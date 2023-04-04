package com.facegram.facegrambackend.security.oauth2.jwt

import com.facegram.facegrambackend.domain.user.UserRepository
import com.facegram.facegrambackend.security.CustomUserDetails
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.http.ResponseCookie
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component
import java.util.*
import java.util.stream.Collector
import java.util.stream.Collectors
import javax.servlet.http.HttpServletResponse
import kotlin.math.log
import org.springframework.security.core.authority.SimpleGrantedAuthority


// JWT 토큰을 생성하는 클래스
// Access Token : LocalStorage / Refresh Token : Cookie(http only secure)에 저장한다
// Refresh Token은 DB에 저장하여 갱신시 일치여부 판단을 하는데 사용한다
// @Component를 안 지우면 에러가 난다.
@Slf4j
@ConstructorBinding
@Component
class JwtTokenProvider(
    @Value("\${app.auth.token.secret-key}") secretKey: String,
    @Value("\${app.auth.token.refresh-cookie-key}") cookieKey: String,
    @Autowired
    private val userRepository: UserRepository
) {
    private val SECRET_KEY: String
    private val COOKIE_REFRESH_TOKEN_KEY: String
    private val ACCESS_TOKEN_EXPIRE_LENGTH = 1000L * 60 * 60 // 1hour
    private val REFRESH_TOKEN_EXPIRE_LENGTH = 1000L * 60 * 60 * 24 * 7 // 1week
    private val AUTHORITIES_KEY = "role"

    private val log: Logger = LoggerFactory.getLogger(JwtTokenProvider::class.java)




    init {
        SECRET_KEY = Base64.getEncoder().encodeToString(secretKey.toByteArray())
        COOKIE_REFRESH_TOKEN_KEY = cookieKey
    }

    fun createAccessToken(authentication: Authentication?): String {
        if(authentication == null){
            throw IllegalArgumentException("유저 어선시케이션이 없습니다.")
        }
        val now = Date()
        val validity = Date(now.time + ACCESS_TOKEN_EXPIRE_LENGTH)
        val user: CustomUserDetails = authentication.principal as CustomUserDetails
        val userId: String = user.name
        val role: String = authentication.authorities.stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(","))
        return Jwts.builder()
            .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
            .setSubject(userId)
            .claim(AUTHORITIES_KEY, role)
            .setIssuer("debrains")
            .setIssuedAt(now)
            .setExpiration(validity)
            .compact()
    }

    fun createRefreshToken(authentication: Authentication?, response: HttpServletResponse?) {
        if (authentication == null || response == null){
            throw IllegalArgumentException("유저 어선시케이션이 없거나 리스폰스가 없습니다.")
        }
        val now = Date()
        val validity = Date(now.time + REFRESH_TOKEN_EXPIRE_LENGTH)
        val refreshToken = Jwts.builder()
            .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
            .setIssuer("debrains")
            .setIssuedAt(now)
            .setExpiration(validity)
            .compact()
        saveRefreshToken(authentication, refreshToken)
        val cookie: ResponseCookie = ResponseCookie.from(COOKIE_REFRESH_TOKEN_KEY, refreshToken)
            .httpOnly(true)
            .secure(true)
            .sameSite("Lax")
            .maxAge(REFRESH_TOKEN_EXPIRE_LENGTH / 1000)
            .path("/")
            .build()
        response.addHeader("RefreshToken", cookie.toString())
    }

    private fun saveRefreshToken(authentication: Authentication, refreshToken: String) {
        val user: CustomUserDetails = authentication.principal as CustomUserDetails
        val id: Long = java.lang.Long.valueOf(user.name)
        userRepository.updateRefreshToken(id, refreshToken)
    }

    // Access Token을 검사하고 얻은 정보로 Authentication 객체 생성
    fun getAuthentication(accessToken: String): Authentication {
        val claims = parseClaims(accessToken)
        val authorities: Collection<GrantedAuthority> = claims[AUTHORITIES_KEY].toString().split(",")
            .map { SimpleGrantedAuthority(it) }
            .toList()
        val principal = CustomUserDetails(java.lang.Long.valueOf(claims.subject), "test", authorities)
        log.info("CustomUserDetials = {} {}",principal.name,principal.username)
        log.info("principal 확인$principal")
        return UsernamePasswordAuthenticationToken(principal, "", authorities)
    }

    fun validateToken(token: String?): Boolean {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token)
            return true
        } catch (e: ExpiredJwtException) {
            log.info("만료된 JWT 토큰입니다.")
//        } catch (e: UnsupportedJwtException) {
//            log.info("지원되지 않는 JWT 토큰입니다.")
        } catch (e: IllegalStateException) {
            log.info("JWT 토큰이 잘못되었습니다")
        }
        return false
    }

    // Access Token 만료시 갱신때 사용할 정보를 얻기 위해 Claim 리턴
    private fun parseClaims(accessToken: String): Claims {
        return try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(accessToken).body
        } catch (e: ExpiredJwtException) {
            e.claims
        }
    }
}