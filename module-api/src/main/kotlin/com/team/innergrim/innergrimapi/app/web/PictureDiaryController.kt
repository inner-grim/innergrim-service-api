package com.team.innergrim.innergrimapi.app.web

import com.team.innergrim.innergrimapi.app.service.PictureDiaryService
import com.team.innergrim.innergrimapi.app.web.dto.PictureDiaryRequestDto
import com.team.innergrim.innergrimapi.entity.PictureDiary
import com.team.innergrim.innergrimapi.response.BaseResponse
import io.swagger.v3.oas.annotations.Operation
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
    @Operation(summary = "그림일기 리스트 조회", description = "그림일기 리스트 조회")
    @GetMapping
    fun getPictureDiaries(): BaseResponse<List<PictureDiary>> {
        pictureDiaryService.getPictureDiaries()
        return BaseResponse.successWithoutData()
    }

    @Operation(summary = "그림일기 상세 조회", description = "그림일기 상세 조회")
    @GetMapping("/{id}")
    fun getPictureDiaryDetail(@PathVariable("id") id: Long): BaseResponse<PictureDiary> {
        return BaseResponse.successWithData(pictureDiaryService.getPictureDiaryDetail(id))
    }

    // ::::: [POST] :::::
    @Operation(summary = "그림일기 생성", description = "그림일기 생성")
    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun createPictureDiary(
        @ModelAttribute createPictureDiaryRequestDto: PictureDiaryRequestDto.CreatePictureDiary
    ): BaseResponse<Unit> {
        pictureDiaryService.createPictureDiary(createPictureDiaryRequestDto)
        return BaseResponse.successWithoutData()
    }

}