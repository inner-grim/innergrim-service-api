package com.team.innergrim.innergrimapi.web

import com.team.innergrim.innergrimapi.service.S3Service
import com.team.innergrim.innergrimapi.web.dto.PictureDiaryRequestDto
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@Tag(name = "PictureDiary")
@RequestMapping("/picture-diary")
@RestController
class PictureDiaryController(
    private val s3S3Service: S3Service
) {

    // ::::: [GET] :::::

    @GetMapping
    fun getPictureDiaryList() {

    }

    // ::::: [POST] :::::
    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun createPictureDiary(
//        @RequestBody createPictureDiaryRequestDto: PictureDiaryRequestDto.Create
        @Parameter(description = "Name of the Picture Diary") @RequestPart(value = "name") name: String,
        @Parameter(description = "File to upload") @RequestParam(value = "file") file: MultipartFile
    ) {
        s3S3Service.uploadFile(file);
    }

}