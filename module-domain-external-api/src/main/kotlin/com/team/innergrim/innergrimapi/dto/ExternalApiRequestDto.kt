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

    data class SendDiscordMessage (
        @JsonProperty("content")
        @field:Schema(description = "메시지 본문")
        val content: String, // 기본 텍스트 메시지

        @JsonProperty("username")
        @field:Schema(description = "발신자 이름 (옵션)")
        val username: String? = null, // 사용자 이름 (옵션)

        @JsonProperty("avatar_url")
        @field:Schema(description = "발신자 아바타 URL (옵션)")
        val avatarUrl: String? = null, // 아바타 URL (옵션)

        @JsonProperty("embeds")
        @field:Schema(description = "임베드 메시지 목록 (옵션)")
        val embeds: List<Embed>? = null // 임베드 메시지
    ) {
        data class Embed(
            @JsonProperty("title")
            @field:Schema(description = "임베드 제목")
            val title: String? = null,

            @JsonProperty("description")
            @field:Schema(description = "임베드 설명")
            val description: String? = null,

            @JsonProperty("color")
            @field:Schema(description = "임베드 색상 (정수형 RGB)")
            val color: Int? = null // 색상 (RGB 값, 예: 0x00FF00)
        )
    }

}