package com.team.innergrim.innergrimapi.external.service

import com.team.innergrim.innergrimapi.dto.ExternalApiRequestDto
import com.team.innergrim.innergrimapi.enums.ExternalServiceType
import com.team.innergrim.innergrimapi.service.ExternalApiService
import org.springframework.stereotype.Service

@Service
class DiscordService(
    private val externalApiService: ExternalApiService
) {

    fun sendDiscordMessage(sendDiscordMessageRequestDto :ExternalApiRequestDto.SendDiscordMessage) {
        val jsonResponse = externalApiService.post(
            ExternalServiceType.discord,
            "/1287330459461750815/g7CZ68TiINkNwStUtR-xIziCsc0wOXQ_8Sn3uXdPaxNnoGj-lqv_VaeM7pg47a4Y9PmI",
            sendDiscordMessageRequestDto
        ).block()
    }
}