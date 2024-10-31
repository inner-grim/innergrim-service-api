package com.team.innergrim.innergrimapi.dto

import com.team.innergrim.innergrimapi.entity.PictureDiary
import com.team.innergrim.innergrimapi.specification.PictureDiarySpecification
import org.springframework.data.jpa.domain.Specification

data class SearchPictureDiaryDto(
    val id: Long? = null,
) {
    val specification: Specification<PictureDiary> = PictureDiarySpecification.setSpecification(this)
}
