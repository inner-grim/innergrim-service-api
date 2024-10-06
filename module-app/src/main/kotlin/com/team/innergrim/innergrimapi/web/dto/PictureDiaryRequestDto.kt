package com.team.innergrim.innergrimapi.web.dto

import org.springframework.web.multipart.MultipartFile

class PictureDiaryRequestDto {

    data class Create(
        val name: String,
        val file: MultipartFile
    ) {}

}