package com.team.innergrim.innergrimapi.dto

import com.team.innergrim.innergrimapi.entity.PictureDiary
import com.team.innergrim.innergrimapi.specification.PictureDiarySpecification
import org.springframework.data.jpa.domain.Specification
import java.time.LocalDate

data class SearchPictureDiaryDto(
    val id: Long? = null,
    val createStartDateAt: LocalDate? = null,
    val createEndDateAt: LocalDate? = null,
    val updateStartDateAt: LocalDate? = null,
    val updateEndDateAt: LocalDate? = null,
) {
    val specification: Specification<PictureDiary> = PictureDiarySpecification.setSpecification(this)
}
