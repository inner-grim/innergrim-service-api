package com.team.innergrim.innergrimapi.app.service

import com.team.innergrim.innergrimapi.app.web.dto.ChatbotResponseDto
import com.team.innergrim.innergrimapi.service.ExternalApiService
import org.springframework.stereotype.Service

@Service
class ChatBotService (
    private val externalApiService: ExternalApiService,
) {
    fun sendChat(): ChatbotResponseDto.SendChat {
        return ChatbotResponseDto.SendChat (
            answer = externalApiService.postData().block()
        )
    }
}