package com.team.innergrim.innergrimapi.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtil {

    private val secret = "yourSecretKey"
    private val algorithm = Algorithm.HMAC256(secret)

    fun createToken(username: String): String {
        return JWT.create()
            .withSubject(username)
            .withExpiresAt(Date(System.currentTimeMillis() + 600_000)) // 10분 유효
            .sign(algorithm)
    }

    fun validateToken(token: String): String? {
        val verifier = JWT.require(algorithm).build()
        val decodedJWT = verifier.verify(token)
        return decodedJWT.subject
    }

}