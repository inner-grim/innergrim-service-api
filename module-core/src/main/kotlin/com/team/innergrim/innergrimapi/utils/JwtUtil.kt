package com.team.innergrim.innergrimapi.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.DecodedJWT
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import java.util.*

object JwtUtil {

    private val secret = "yourSecretKey"
    private val tokenKey = Algorithm.HMAC256(secret)
    private val accessTokenValidity: Long = 1000L * 60 * 60 // 1시간 유효
    private val refreshTokenValidity: Long = 1000L * 60 * 60 * 24 * 30 // 한달 유효

    fun validateToken (token: String): Boolean {
        try {
            val decodedJWT:DecodedJWT = getDecodeToken(token)
            // 토큰의 만료 시간을 확인
            return decodedJWT.expiresAt.after(Date())
        } catch (e:JWTVerificationException) {
            return false
        }
    }

    // 토큰에서 사용자 이름 추출
    fun getUsername(): String {
        return (SecurityContextHolder.getContext().authentication.principal as User).username
    }

    fun getUsername(token: String): String {
        val decodedJWT:DecodedJWT = getDecodeToken(token)
        return decodedJWT.subject
    }

    fun getAccessTokenExpiry(): Long {
        return accessTokenValidity
    }

    fun getRefreshTokenExpiry(): Long {
        return refreshTokenValidity
    }

    fun createAccessToken(id: String): String {
        return createToken(id, accessTokenValidity)
    }

    fun createRefreshToken(id: String): String {
        return createToken(id, refreshTokenValidity)
    }

//    fun getAccessToken() {
//        val bearerToken = request.getHeader("Authorization")
//        return if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
//            bearerToken.substring(7) // "Bearer " 이후의 토큰 부분 추출
//        } else null
//    }

    private fun createToken(id: String, validity: Long): String {
        val now = Date()
        val expiryDate = Date(now.time + validity) // 현재 시간에서 유효 기간만큼 더해 만료시간 설정

        return JWT.create()
            .withSubject(id) // id
            .withIssuedAt(now) // 발행 시간
            .withExpiresAt(expiryDate) // 만료 시간
            .sign(tokenKey) // 서명
    }

    private fun getDecodeToken(token: String): DecodedJWT {
        return JWT.require(tokenKey).build().verify(token)
    }

}