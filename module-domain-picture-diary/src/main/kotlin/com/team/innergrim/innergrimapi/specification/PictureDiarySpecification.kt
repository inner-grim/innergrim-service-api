package com.team.innergrim.innergrimapi.specification

import com.team.innergrim.innergrimapi.dto.SearchPictureDiaryDto
import com.team.innergrim.innergrimapi.entity.PictureDiary
import org.springframework.data.jpa.domain.Specification

object PictureDiarySpecification {

    fun setSpecification (searchDto: SearchPictureDiaryDto) : Specification<PictureDiary> {

        var spec: Specification<PictureDiary> = Specification.where(null)

        // ID
        if (searchDto.id != null) {
            spec = spec.and(Specification { root, query, criteriaBuilder ->
                criteriaBuilder.equal(root.get<Long>("id"), searchDto.id)
            })
        }

        return spec
    }

}