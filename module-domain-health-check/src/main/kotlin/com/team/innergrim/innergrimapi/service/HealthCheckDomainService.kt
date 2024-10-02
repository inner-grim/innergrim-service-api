package com.team.innergrim.innergrimapi.service

import org.springframework.stereotype.Service

@Service
class HealthCheckDomainService () {

    // ::::: [GET] :::::

    fun getHealthCheck (): String {
        return "::::: Health Check Ok! :::::"
    }
}