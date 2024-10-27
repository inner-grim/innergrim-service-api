package com.team.innergrim.innergrimapi.service

import org.springframework.stereotype.Service

@Service
class ChatBotService (
    private val externalApiService: ExternalApiService,
) {
    fun sendChat(): String? {
        return externalApiService.getData().block();
    }
}