package com.team.innergrim.innergrimapi.app.web.dto

import com.team.innergrim.innergrimapi.enums.ChatRole
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull

class ChatbotRequestDto {

    data class SendChat(
        @field:Schema(description = "chat", required = true)
        @field:NotNull
        val chatRole: ChatRole,

        @field:Schema(description = "chat history ", required = true)
        @field:NotNull
        val content : String,
    ) {}

}