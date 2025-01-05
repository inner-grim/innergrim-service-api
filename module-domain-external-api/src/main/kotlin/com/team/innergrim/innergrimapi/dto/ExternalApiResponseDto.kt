package com.team.innergrim.innergrimapi.dto

import io.swagger.v3.oas.annotations.media.Schema

class ExternalApiResponseDto {

    data class SendChat (
        @field:Schema(description = "응답 문구")
        val response: String
    ) {

    }
}