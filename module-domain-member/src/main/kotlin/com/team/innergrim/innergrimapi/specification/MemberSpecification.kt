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

        // SocialId
        if (!searchDto.socialId.isNullOrEmpty()) {
            spec = spec.and(Specification { root, query, criteriaBuilder ->
                criteriaBuilder.equal(root.get<String>("socialId"), searchDto.socialId)
            })
        }

        // 이름
        if (!searchDto.name.isNullOrEmpty()) {
            spec = spec.and(Specification { root, query, criteriaBuilder ->
                criteriaBuilder.equal(root.get<String>("name"), searchDto.name)
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