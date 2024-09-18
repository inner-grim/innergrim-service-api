package com.team.innergrim.innergrimapi.service

import com.team.innergrim.innergrimapi.dto.SearchMemberDto
import com.team.innergrim.innergrimapi.entity.Member
import com.team.innergrim.innergrimapi.search.Search
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class MemberService (private val memberDomainService: MemberDomainService) {

    fun getMembers(): List<Member> {
        return memberDomainService.getMembers()
    }

    fun getMemberDetail(id: Long): Member {
        return memberDomainService.getMemberDetail(
            SearchMemberDto(
                id = id,
            ).specification
        ).get()
    }

}