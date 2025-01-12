package com.team.innergrim.innergrimapi.cms.web

import com.team.innergrim.innergrimapi.cms.service.CmsStatisticsService
import com.team.innergrim.innergrimapi.cms.web.dto.StatisticsRequestDto
import com.team.innergrim.innergrimapi.cms.web.dto.StatisticsResponseDto
import com.team.innergrim.innergrimapi.response.BaseResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "cms-statistics", description = "CMS 통계 API")
@RequestMapping("/cms/statistics")
@RestController
class CmsStatisticsController(
    private val cmsStatisticsService: CmsStatisticsService
) {

    @Operation(summary = "가입,탈퇴한 멤버 통계", description = "가입,탈퇴한 멤버 통계")
    @GetMapping("/member")
    fun getMemberStatistics(memberStatisticsRequestDto: StatisticsRequestDto.Member): BaseResponse<StatisticsResponseDto.Member> {
        return BaseResponse.successWithData(cmsStatisticsService.getMemberStatistics(memberStatisticsRequestDto))
    }

    @Operation(summary = "생성된 그림일기 통계", description = "생성된 그림일기 통계")
    @GetMapping("/picture-diary")
    fun getPictureDiaryStatistics(pictureDiaryStatisticsRequestDto: StatisticsRequestDto.PictureDiary): BaseResponse<StatisticsResponseDto.PictureDiary> {
        return BaseResponse.successWithData(cmsStatisticsService.getPictureDiaryStatistics(pictureDiaryStatisticsRequestDto))
    }


}