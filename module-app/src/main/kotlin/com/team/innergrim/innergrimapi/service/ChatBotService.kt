package com.team.innergrim.innergrimapi.service

import com.team.innergrim.innergrimapi.web.dto.ChatbotResponseDto
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