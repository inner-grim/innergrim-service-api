package com.team.innergrim.innergrimapi.app.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.team.innergrim.innergrimapi.app.web.dto.ChatbotRequestDto
import com.team.innergrim.innergrimapi.app.web.dto.ChatbotResponseDto
import com.team.innergrim.innergrimapi.dto.ExternalApiResponseDto
import com.team.innergrim.innergrimapi.enums.ExternalServiceType
import com.team.innergrim.innergrimapi.service.ExternalApiService
import org.springframework.stereotype.Service

@Service
class ChatBotService (
    private val objectMapper: ObjectMapper,
    private val externalApiService: ExternalApiService,
) {
    fun sendChat(sendChatRequestDto: ChatbotRequestDto.SendChat): ChatbotResponseDto.SendChat {
        val jsonResponse = externalApiService.post(
            ExternalServiceType.chatbot,
            "/chatbot/get-response/",
            sendChatRequestDto.createExternalApiRequest()
        ).block()

        val sendChatResponse = objectMapper.readValue(jsonResponse, ExternalApiResponseDto.SendChat::class.java)

        return ChatbotResponseDto.SendChat (
            answer = sendChatResponse.response
        )
    }
}