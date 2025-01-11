package com.team.innergrim.innergrimapi.specification

import com.team.innergrim.innergrimapi.dto.SearchMemberDto
import com.team.innergrim.innergrimapi.entity.Member
import com.team.innergrim.innergrimapi.entity.Role
import com.team.innergrim.innergrimapi.enums.MemberStatus
import org.springframework.data.jpa.domain.Specification
import java.time.LocalDateTime
import java.time.LocalTime

object MemberSpecification {

    fun setSpecification (searchMemberDto: SearchMemberDto) : Specification<Member> {

        var spec: Specification<Member> = Specification.where(null)

        // ID
        if (searchMemberDto.id != null) {
            spec = spec.and(Specification { root, _, criteriaBuilder ->
                criteriaBuilder.equal(root.get<Long>("id"), searchMemberDto.id)
            })
        }

        // memberId
        if (!searchMemberDto.loginId.isNullOrEmpty()) {
            spec = spec.and(Specification { root, _, criteriaBuilder ->
                criteriaBuilder.equal(root.get<String>("loginId"), searchMemberDto.loginId)
            })
        }

        // memberType
        if (searchMemberDto.memberType != null) {
            spec = spec.and(Specification { root, _, criteriaBuilder ->
                criteriaBuilder.equal(root.get<String>("memberType"), searchMemberDto.memberType.name)
            })
        }

        // 이름
        if (!searchMemberDto.name.isNullOrEmpty()) {
            spec = spec.and(Specification { root, _, criteriaBuilder ->
                criteriaBuilder.equal(root.get<String>("name"), searchMemberDto.name)
            })
        }

        // 닉네임
        if (!searchMemberDto.nickname.isNullOrEmpty()) {
            spec = spec.and(Specification { root, _, criteriaBuilder ->
                criteriaBuilder.equal(root.get<String>("nickname"), searchMemberDto.nickname)
            })
        }

        // 역할
        if (searchMemberDto.roleId != null) {
            spec = spec.and(Specification { root, _, criteriaBuilder ->
                criteriaBuilder.equal(root.get<Role>("role").get<Long>("id"), searchMemberDto.roleId)
            })
        }

        // 멤버상태
        if (searchMemberDto.memberStatus != null) {
            spec = spec.and(Specification { root, _, criteriaBuilder ->
                criteriaBuilder.equal(root.get<MemberStatus>("memberStatus"), searchMemberDto.memberStatus)
            })
        }

        // 생성일자(LocalDate)
        if (searchMemberDto.createStartDateAt != null
            && searchMemberDto.createEndDateAt != null) {
            val createdStartDateTime =LocalDateTime.of(
                searchMemberDto.createStartDateAt,
                LocalTime.of(0,0,0)
            )
            val createdEndDateTime =LocalDateTime.of(
                searchMemberDto.createEndDateAt,
                LocalTime.of(23,59,59)
            )
            spec = spec.and(Specification { root, _, criteriaBuilder ->
                criteriaBuilder.between(root.get<LocalDateTime>("createdAt"), createdStartDateTime, createdEndDateTime)
            })
        }

        // 수정일자(LocalDate)
        if (searchMemberDto.updateStartDateAt != null
            && searchMemberDto.updateEndDateAt != null) {
            val updatedStartDateTime =LocalDateTime.of(
                searchMemberDto.updateStartDateAt,
                LocalTime.of(0,0,0)
            )
            val updatedEndDateTime =LocalDateTime.of(
                searchMemberDto.updateEndDateAt,
                LocalTime.of(23,59,59)
            )
            spec = spec.and(Specification { root, _, criteriaBuilder ->
                criteriaBuilder.between(root.get<LocalDateTime>("updatedAt"), updatedStartDateTime, updatedEndDateTime)
            })
        }
        return spec
    }

}