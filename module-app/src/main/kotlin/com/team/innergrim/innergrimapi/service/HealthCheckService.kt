package com.team.innergrim.innergrimapi.service

import org.springframework.stereotype.Service

@Service
class HealthCheckService (
    private val healthCheckDomainService : HealthCheckDomainService,
) {
    fun getHealthCheck(): String {
        return healthCheckDomainService.getHealthCheck();
    }
}