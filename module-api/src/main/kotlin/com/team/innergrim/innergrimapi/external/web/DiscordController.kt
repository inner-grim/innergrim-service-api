package com.team.innergrim.innergrimapi.external.web

import com.team.innergrim.innergrimapi.external.service.DiscordService
import com.team.innergrim.innergrimapi.response.BaseResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Discord")
@RequestMapping("/discord")
@RestController
class DiscordController(
    private val discordService: DiscordService
) {

    @Operation(summary = "디스코드 메시지 전송", description = "디스코드 메시지 전송")
    @PostMapping("/send")
    fun sendDiscordMessage (): BaseResponse<Unit> {
//        discordService.sendDiscordMessage();
        return BaseResponse.successWithoutData()
    }

}