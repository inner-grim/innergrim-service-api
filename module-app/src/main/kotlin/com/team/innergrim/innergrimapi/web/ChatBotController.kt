package com.team.innergrim.innergrimapi.web

import com.team.innergrim.innergrimapi.service.ChatBotService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/chat-bot")
@RestController
@Tag(name = "chat-bot")
class ChatBotController(
    private val chatBotService: ChatBotService
) {

    @GetMapping("/send/chat")
    @Operation(summary = "대화 전송", description = "대화 전송")
    fun sendChat () : String? {
        return chatBotService.sendChat()
    }
}