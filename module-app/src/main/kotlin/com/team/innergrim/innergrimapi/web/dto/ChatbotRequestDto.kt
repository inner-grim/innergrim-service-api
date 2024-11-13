package com.team.innergrim.innergrimapi.web.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull

class ChatbotRequestDto {

    data class SendChat(
        @field:Schema(description = "chat", required = true)
        @field:NotNull
        val chat: String,
    ) {}

}