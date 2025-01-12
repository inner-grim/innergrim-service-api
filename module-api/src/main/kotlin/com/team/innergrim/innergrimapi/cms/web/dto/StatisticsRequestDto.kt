package com.team.innergrim.innergrimapi.cms.web.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotEmpty
import java.time.LocalDate

class StatisticsRequestDto {

    @Schema(name = "memberStatisticsRequestDto")
    data class Member (
        @field:Schema(description = "startDate", required = true)
        @field:NotEmpty
        val startDate : LocalDate,

        @field:Schema(description = "endDate", required = true)
        @field:NotEmpty
        val endDate : LocalDate,
    ) { }

    @Schema(name = "pictureDiaryStatisticsRequestDto")
    data class PictureDiary (
        @field:Schema(description = "startDate", required = true)
        @field:NotEmpty
        val startDate : LocalDate,

        @field:Schema(description = "endDate", required = true)
        @field:NotEmpty
        val endDate : LocalDate,
    ) { }

}