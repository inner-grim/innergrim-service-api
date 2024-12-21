package com.team.innergrim.innergrimapi.app.web

import com.team.innergrim.innergrimapi.app.service.ChatBotService
import com.team.innergrim.innergrimapi.app.web.dto.ChatbotRequestDto
import com.team.innergrim.innergrimapi.app.web.dto.ChatbotResponseDto
import com.team.innergrim.innergrimapi.response.BaseResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/chat-bot")
@RestController
@Tag(name = "chat-bot")
class ChatBotController(
    private val chatBotService: ChatBotService
) {

    @PostMapping("/send/chat")
    @Operation(summary = "대화 전송", description = "대화 전송")
    fun sendChat (sendChat: ChatbotRequestDto.SendChat) : BaseResponse<ChatbotResponseDto.SendChat> {
        return BaseResponse.successWithData(chatBotService.sendChat())
    }
}