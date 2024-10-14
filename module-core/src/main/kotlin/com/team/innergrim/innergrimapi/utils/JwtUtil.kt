package com.team.innergrim.innergrimapi.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.DecodedJWT
import java.util.*

object JwtUtil {

    private val secret = "yourSecretKey"
    private val tokenKey = Algorithm.HMAC256(secret)
    private val accessTokenValidity: Long = 1000L * 60 * 60 // 1시간 유효
    private val refreshTokenValidity: Long = 1000L * 60 * 60 * 24 * 30 // 한달 유효

    fun validateToken (token: String): Boolean {
        return try {
            val verifier: JWTVerifier = JWT.require(tokenKey).build()
            val decodedJWT: DecodedJWT = verifier.verify(token)
            decodedJWT.expiresAt.after(Date()) // 토큰의 만료 시간을 확인
        } catch (ex: JWTVerificationException) {
            false // 토큰 검증에 실패하면 false 반환
        }
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

    private fun createToken(id: String, validity: Long): String {
        val now = Date()
        val expiryDate = Date(now.time + validity) // 현재 시간에서 유효 기간만큼 더해 만료시간 설정

        return JWT.create()
            .withSubject(id) // id
            .withIssuedAt(now) // 발행 시간
            .withExpiresAt(expiryDate) // 만료 시간
            .sign(tokenKey) // 서명
    }

}