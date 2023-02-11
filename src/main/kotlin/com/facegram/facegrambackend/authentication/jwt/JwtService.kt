package com.facegram.facegrambackend.authentication.jwt

import javax.security.auth.Subject

interface JwtService {

    fun issuedToken(subject: String, role: String, periodSecond: Long): String

    fun verifyToken(token: String): Boolean

    fun getSubject(token: String): String
}