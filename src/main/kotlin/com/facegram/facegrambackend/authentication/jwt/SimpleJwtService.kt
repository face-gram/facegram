package com.facegram.facegrambackend.authentication.jwt


import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.lang.Exception
import java.nio.charset.StandardCharsets
import java.security.Key
import java.util.Base64
import java.util.Date
import javax.annotation.PostConstruct
import javax.crypto.SecretKey

@Service
class SimpleJwtService: JwtService {
    companion object{
        private var SECRET: String = "박세열의백엔드JWT토큰입니다."
    }

    @PostConstruct
    protected fun init(){
        SECRET = Base64.getEncoder().encodeToString(SECRET.toByteArray(StandardCharsets.UTF_8))
    }


    override fun issuedToken(subject: String, role: String, periodSecond: Long): String {
        val claims: Claims = Jwts.claims().setSubject(subject)
        claims["role"] = role

        val now: Date = Date()

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(Date(now.time + periodSecond*1000))
            .signWith(getSigningKey())
            .compact() // 스트링화
    }

    override fun verifyToken(token: String): Boolean {
        return try {
            val claims: Claims = getBody(token)
            claims.expiration.after(Date())
        }catch (e: Exception){
            false
        }
    }

    override fun getSubject(token: String): String {
        val claims: Claims = getBody(token)
        return claims.subject
    }

    private fun getSigningKey(): Key {
        val keyBytes = SECRET.toByteArray(StandardCharsets.UTF_8)
        return Keys.hmacShaKeyFor(keyBytes)
    }

    private fun getBody(token: String): Claims{
        return Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJwt(token)
            .body
    }
}