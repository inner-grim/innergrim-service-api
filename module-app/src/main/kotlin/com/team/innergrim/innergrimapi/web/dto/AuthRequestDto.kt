package com.team.innergrim.innergrimapi.web.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

class AuthRequestDto {

    data class MemberLogin(
        @field:Schema(description = "소셜 ID", required = true)
        @field:NotBlank
        val socialId: String,
    ) {

    }
}