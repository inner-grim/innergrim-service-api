package com.team.innergrim.innergrimapi.service

import com.team.innergrim.innergrimapi.dto.SearchMemberDto
import com.team.innergrim.innergrimapi.entity.Member
import com.team.innergrim.innergrimapi.search.Search
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class HealthCheckService (
    private val healthCheckDomainService : HealthCheckDomainService,
) {
    fun getHealthCheck(): String {
        return healthCheckDomainService.getHealthCheck();
    }
}