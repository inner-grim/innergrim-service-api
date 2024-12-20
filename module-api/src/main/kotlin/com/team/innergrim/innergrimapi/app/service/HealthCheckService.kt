package com.team.innergrim.innergrimapi.app.service

import com.team.innergrim.innergrimapi.service.HealthCheckDomainService
import org.springframework.stereotype.Service

@Service
class HealthCheckService (
    private val healthCheckDomainService : HealthCheckDomainService,
) {
    fun getHealthCheck(): String {
        return healthCheckDomainService.getHealthCheck();
    }
}