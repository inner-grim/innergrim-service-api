package com.team.innergrim.innergrimapi.web

import com.team.innergrim.innergrimapi.response.BaseResponse
import com.team.innergrim.innergrimapi.service.PictureDiaryService
import com.team.innergrim.innergrimapi.web.dto.PictureDiaryRequestDto
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@Tag(name = "PictureDiary")
@RequestMapping("/picture-diary")
@RestController
class PictureDiaryController(
    private val pictureDiaryService: PictureDiaryService
//    private val s3S3Service: S3Service
) {

    // ::::: [GET] :::::

    @GetMapping
    fun getPictureDiaryList() {

    }

    // ::::: [POST] :::::
    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun createPictureDiary(
        @ModelAttribute createPictureDiaryRequestDto: PictureDiaryRequestDto.Create
    ): BaseResponse<Unit> {
        pictureDiaryService.createPictureDiary(createPictureDiaryRequestDto)
//        s3S3Service.uploadFile(file);
        return BaseResponse.successWithoutData()
    }

}