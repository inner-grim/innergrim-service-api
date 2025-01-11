package com.team.innergrim.innergrimapi.cms.web.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotEmpty
import java.time.LocalDate

class StatisticsResponseDto {

    data class Member (
        @field:Schema(description = "createdMemberCount")
        val createdMemberCount: Int,

        @field:Schema(description = "deletedMemberCount")
        val deletedMemberCount: Int,
    ) { }

    data class PictureDiary (
        @field:Schema(description = "requestDate", required = true)
        @field:NotEmpty
        val requestDate : LocalDate,
    ) { }

}