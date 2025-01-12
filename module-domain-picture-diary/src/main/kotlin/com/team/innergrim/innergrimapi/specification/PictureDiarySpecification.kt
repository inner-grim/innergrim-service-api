package com.team.innergrim.innergrimapi.specification

import com.team.innergrim.innergrimapi.dto.SearchPictureDiaryDto
import com.team.innergrim.innergrimapi.entity.PictureDiary
import org.springframework.data.jpa.domain.Specification
import java.time.LocalDateTime
import java.time.LocalTime

object PictureDiarySpecification {

    fun setSpecification (searchPictureDiaryDto: SearchPictureDiaryDto) : Specification<PictureDiary> {

        var spec: Specification<PictureDiary> = Specification.where(null)

        // ID
        if (searchPictureDiaryDto.id != null) {
            spec = spec.and(Specification { root, query, criteriaBuilder ->
                criteriaBuilder.equal(root.get<Long>("id"), searchPictureDiaryDto.id)
            })
        }

        // 생성일자(LocalDate)
        if (searchPictureDiaryDto.createStartDateAt != null
            && searchPictureDiaryDto.createEndDateAt != null) {
            val createdStartDateTime = LocalDateTime.of(
                searchPictureDiaryDto.createStartDateAt,
                LocalTime.of(0,0,0)
            )
            val createdEndDateTime = LocalDateTime.of(
                searchPictureDiaryDto.createEndDateAt,
                LocalTime.of(23,59,59)
            )
            spec = spec.and(Specification { root, _, criteriaBuilder ->
                criteriaBuilder.between(root.get<LocalDateTime>("createdAt"), createdStartDateTime, createdEndDateTime)
            })
        }

        // 수정일자(LocalDate)
        if (searchPictureDiaryDto.updateStartDateAt != null
            && searchPictureDiaryDto.updateEndDateAt != null) {
            val updatedStartDateTime = LocalDateTime.of(
                searchPictureDiaryDto.updateStartDateAt,
                LocalTime.of(0,0,0)
            )
            val updatedEndDateTime = LocalDateTime.of(
                searchPictureDiaryDto.updateEndDateAt,
                LocalTime.of(23,59,59)
            )
            spec = spec.and(Specification { root, _, criteriaBuilder ->
                criteriaBuilder.between(root.get<LocalDateTime>("updatedAt"), updatedStartDateTime, updatedEndDateTime)
            })
        }

        return spec
    }

}