package com.team.innergrim.innergrimapi.service

import org.springframework.stereotype.Service

@Service
class PictureDiaryService (
    private val healthCheckDomainService : HealthCheckDomainService,
) {

    // ::::: [GET] :::::

    // ::::: [CREATE] :::::
    fun createPictureDiary(): String {
        return healthCheckDomainService.getHealthCheck();
    }

}