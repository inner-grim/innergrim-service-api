package com.team.innergrim.innergrimapi.external.service

import com.team.innergrim.innergrimapi.dto.ExternalApiRequestDto
import com.team.innergrim.innergrimapi.enums.ExternalServiceType
import com.team.innergrim.innergrimapi.service.ExternalApiService
import org.springframework.stereotype.Service

@Service
class DiscordService(
    private val externalApiService: ExternalApiService
) {
//https://discord.com/api/webhooks/1287330346182115362/Bp1QlfhsBJ1cGTQnpa3ZI6VFJa0CqgcUqpmk8fpBizqeiPXxBcW9glFIenSNS7-txW9H
    fun sendDiscordMessage(sendDiscordMessageRequestDto :ExternalApiRequestDto.SendDiscordMessage) {
        val jsonResponse = externalApiService.post(
            ExternalServiceType.discord,

            "/1287330346182115362/Bp1QlfhsBJ1cGTQnpa3ZI6VFJa0CqgcUqpmk8fpBizqeiPXxBcW9glFIenSNS7-txW9H",
            sendDiscordMessageRequestDto
        ).block()
    }
}