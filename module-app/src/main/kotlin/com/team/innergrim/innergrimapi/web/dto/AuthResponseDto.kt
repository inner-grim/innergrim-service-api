package com.team.innergrim.innergrimapi.web.dto

import io.swagger.v3.oas.annotations.media.Schema

class AuthResponseDto {

    data class IssueAccessToken(
        @field:Schema(description = "accessToken")
        val accessToken: String,

        @field:Schema(description = "refreshToken")
        val refreshToken: String,
    )

    data class MemberLogin(
        @field:Schema(description = "accessToken")
        val accessToken: String,

        @field:Schema(description = "refreshToken")
        val refreshToken: String,
    ) {}

    data class AdminLogin(
        @field:Schema(description = "accessToken")
        val accessToken: String,

        @field:Schema(description = "refreshToken")
        val refreshToken: String,
    ) {}

}
