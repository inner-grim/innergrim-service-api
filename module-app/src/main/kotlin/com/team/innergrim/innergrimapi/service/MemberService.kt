package com.team.innergrim.innergrimapi.service

import com.team.innergrim.innergrimapi.entity.Member
import com.team.innergrim.innergrimapi.search.Search
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class MemberService (private val memberDomainService: MemberDomainService) {

    fun getMembers(): List<Member> {
        return memberDomainService.getMembers()
    }

    fun getMemberDetail(id: Long): Member {

        val searchMember: Search<Member> = Search(Member(name = "member1", createdAt = LocalDateTime.now()))
        println(searchMember.getPropertyMap())

        return memberDomainService.getMemberDetail(id).get()
    }

}