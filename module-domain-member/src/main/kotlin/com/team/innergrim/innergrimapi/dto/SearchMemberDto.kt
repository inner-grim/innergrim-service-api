package com.team.innergrim.innergrimapi.dto

import com.team.innergrim.innergrimapi.entity.Member
import com.team.innergrim.innergrimapi.enums.MemberStatus
import com.team.innergrim.innergrimapi.enums.MemberType
import com.team.innergrim.innergrimapi.enums.SocialType
import com.team.innergrim.innergrimapi.specification.MemberSpecification
import org.springframework.data.jpa.domain.Specification
import java.time.LocalDate

data class SearchMemberDto(
    val id: Long? = null,
    val loginId: String? = null,
    val socialType: SocialType? = null,
    val memberType: MemberType? = null,
    val name: String? = null,
    val roleId: Long? = null,
    val nickname: String? = null,
    val memberStatus: MemberStatus? = null,
    val createStartDateAt: LocalDate? = null,
    val createEndDateAt: LocalDate? = null,
    val updateStartDateAt: LocalDate? = null,
    val updateEndDateAt: LocalDate? = null,
) {
    val specification: Specification<Member> = MemberSpecification.setSpecification(this)
}
