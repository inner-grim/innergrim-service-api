package com.team.innergrim.innergrimapi.dto

import com.team.innergrim.innergrimapi.entity.Role
import com.team.innergrim.innergrimapi.enums.RoleType
import com.team.innergrim.innergrimapi.specification.RoleSpecification
import org.springframework.data.jpa.domain.Specification

data class SearchRoleDto(
    val id: Long? = null,
    val roleType: RoleType,
) {
    val specification: Specification<Role> = RoleSpecification.setSpecification(this)
}
