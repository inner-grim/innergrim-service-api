package com.team.innergrim.innergrimapi.web.dto

import com.team.innergrim.innergrimapi.enums.SocialType
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

class AuthRequestDto {

    data class MemberLogin(

        @field:Schema(description = "소셜 Type", required = true)
        @field:NotNull
        val socialType: SocialType,

        @field:Schema(description = "소셜 ID", required = true)
        @field:NotBlank
        val socialId: String,
    ) {}

    data class ValidateAccessToken(

        @field:Schema(description = "AccessToken", required = true)
        @field:NotBlank
        val accessToken: String,

    ) {}

    data class IssueAccessToken(

        @field:Schema(description = "RefreshToken", required = true)
        @field:NotBlank
        val refreshToken: String,
    ) {}
}