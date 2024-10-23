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
) {

    // ::::: [GET] :::::

    @GetMapping
    fun getPictureDiaryList() {
        // TODO: getPictureDiaryList
    }

    // ::::: [POST] :::::
    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun createPictureDiary(
        @ModelAttribute createPictureDiaryRequestDto: PictureDiaryRequestDto.Create
    ): BaseResponse<Unit> {
        pictureDiaryService.createPictureDiary(createPictureDiaryRequestDto)
        return BaseResponse.successWithoutData()
    }

}