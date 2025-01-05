package com.team.innergrim.innergrimapi.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.team.innergrim.innergrimapi.enums.ChatRole
import io.swagger.v3.oas.annotations.media.Schema

class ExternalApiRequestDto {

    data class SendChat (
        @JsonProperty(value = "previous_conversion")
        @field:Schema(description = "이전 대화내용 목록")
        val previous_conversion: List<PreviousConversion>,
        @field:Schema(description = "사용자가 입력한 문구")
        val question: String

    ) {
        data class PreviousConversion (
            @field:Schema(description = "역할")
            val role: ChatRole,
            @field:Schema(description = "내화 내용")
            val content: String
        ) {}
    }

}