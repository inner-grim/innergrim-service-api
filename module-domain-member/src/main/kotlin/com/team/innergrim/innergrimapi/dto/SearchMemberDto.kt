package com.team.innergrim.innergrimapi.dto

import java.time.LocalDateTime

data class SearchMemberDto(
    val name: String? = null,
    val membershipId: Long? = null,
    val createdAfter: LocalDateTime? = null,
    val createdBefore: LocalDateTime? = null
)
