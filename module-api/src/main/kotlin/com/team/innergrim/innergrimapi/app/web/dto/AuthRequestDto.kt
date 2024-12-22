package com.team.innergrim.innergrimapi.app.web.dto

import com.team.innergrim.innergrimapi.enums.SocialType
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

class AuthRequestDto {

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

    data class MemberLogin(

        @field:Schema(description = "멤버 ID", required = true)
        @field:NotBlank
        val loginId: String,

        @field:Schema(description = "소셜 Type", required = true)
        @field:NotNull
        val socialType: SocialType,

    ) {}

    data class AdminLogin(

        @field:Schema(description = "로그인 ID", required = true)
        @field:NotBlank
        val loginId: String,

        @field:Schema(description = "로그인 Password", required = true)
        @field:NotBlank
        val password: String,
    ) {}
}