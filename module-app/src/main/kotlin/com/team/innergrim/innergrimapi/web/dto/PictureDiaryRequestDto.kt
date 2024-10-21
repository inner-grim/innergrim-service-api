package com.team.innergrim.innergrimapi.web.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.springframework.web.multipart.MultipartFile

class PictureDiaryRequestDto {

    data class Create(
        @field:Schema(description = "제목", required = true)
        @field:NotBlank
        val title: String,

        @field:Schema(description = "내용", required = true)
        @field:NotBlank
        val content: String,

        @field:Schema(description = "그림일기", required = true)
        @field:NotNull
        val file: MultipartFile
    ) {}

}