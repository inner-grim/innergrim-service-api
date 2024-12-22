package com.team.innergrim.innergrimapi.specification

import com.team.innergrim.innergrimapi.dto.SearchMemberDto
import com.team.innergrim.innergrimapi.entity.Member
import com.team.innergrim.innergrimapi.entity.Role
import org.springframework.data.jpa.domain.Specification

object MemberSpecification {

    fun setSpecification (searchDto: SearchMemberDto) : Specification<Member> {

        var spec: Specification<Member> = Specification.where(null)

        // ID
        if (searchDto.id != null) {
            spec = spec.and(Specification { root, query, criteriaBuilder ->
                criteriaBuilder.equal(root.get<Long>("id"), searchDto.id)
            })
        }

        // memberId
        if (!searchDto.loginId.isNullOrEmpty()) {
            spec = spec.and(Specification { root, query, criteriaBuilder ->
                criteriaBuilder.equal(root.get<String>("loginId"), searchDto.loginId)
            })
        }

        // memberType
        if (searchDto.memberType != null) {
            spec = spec.and(Specification { root, query, criteriaBuilder ->
                criteriaBuilder.equal(root.get<String>("memberType"), searchDto.memberType.name)
            })
        }

        // 이름
        if (!searchDto.name.isNullOrEmpty()) {
            spec = spec.and(Specification { root, query, criteriaBuilder ->
                criteriaBuilder.equal(root.get<String>("name"), searchDto.name)
            })
        }

        // 닉네임
        if (!searchDto.nickname.isNullOrEmpty()) {
            spec = spec.and(Specification { root, query, criteriaBuilder ->
                criteriaBuilder.equal(root.get<String>("nickname"), searchDto.nickname)
            })
        }

        // 멤버십
        if (searchDto.roleId != null) {
            spec = spec.and(Specification { root, query, criteriaBuilder ->
                criteriaBuilder.equal(root.get<Role>("role").get<Long>("id"), searchDto.roleId)
            })
        }

        return spec
    }

}