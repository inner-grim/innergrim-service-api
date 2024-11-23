package com.team.innergrim.innergrimapi.dto

import com.team.innergrim.innergrimapi.entity.Member
import com.team.innergrim.innergrimapi.specification.MemberSpecification
import org.springframework.data.jpa.domain.Specification

data class SearchMemberDto(
    val id: Long? = null,
    val socialId: String? = null,
    val name: String? = null,
    val roleId: Long? = null,
) {
    val specification: Specification<Member> = MemberSpecification.setSpecification(this)
}
