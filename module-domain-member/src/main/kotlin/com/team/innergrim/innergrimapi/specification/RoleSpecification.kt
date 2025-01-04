package com.team.innergrim.innergrimapi.specification

import com.team.innergrim.innergrimapi.dto.SearchRoleDto
import com.team.innergrim.innergrimapi.entity.Role
import org.springframework.data.jpa.domain.Specification

object RoleSpecification {

    fun setSpecification (searchDto: SearchRoleDto) : Specification<Role> {

        var spec: Specification<Role> = Specification.where(null)

        // ID
        if (searchDto.id != null) {
            spec = spec.and(Specification { root, query, criteriaBuilder ->
                criteriaBuilder.equal(root.get<Long>("id"), searchDto.id)
            })
        }

        // memberType
        if (searchDto.roleType != null) {
            spec = spec.and(Specification { root, query, criteriaBuilder ->
                criteriaBuilder.equal(root.get<String>("roleType"), searchDto.roleType.name)
            })
        }

        return spec
    }

}