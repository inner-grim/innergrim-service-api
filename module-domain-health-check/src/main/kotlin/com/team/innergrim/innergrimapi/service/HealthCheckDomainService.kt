package com.team.innergrim.innergrimapi.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class HealthCheckDomainService(
    @Value("\${profile}") private val profile: String
) {

    // ::::: [GET] :::::

    fun getHealthCheck(): String {
        return """ 
                ::::: Health Check Ok! :::::
                1. profile : ${profile}
                """.trimIndent()
    }
}