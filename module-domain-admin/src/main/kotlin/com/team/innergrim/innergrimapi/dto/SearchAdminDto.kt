package com.team.innergrim.innergrimapi.dto

import com.team.innergrim.innergrimapi.entity.Admin
import com.team.innergrim.innergrimapi.specification.AdminSpecification
import org.springframework.data.jpa.domain.Specification

data class SearchAdminDto(
    val id: Long? = null,
    val socialId: String? = null,
    val name: String? = null
) {
    val specification: Specification<Admin> = AdminSpecification.setSpecification(this)
}
