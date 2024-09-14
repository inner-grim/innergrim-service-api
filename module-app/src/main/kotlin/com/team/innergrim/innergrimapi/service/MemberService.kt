package com.team.innergrim.innergrimapi.service

import com.team.innergrim.innergrimapi.entity.Member
import com.team.innergrim.innergrimapi.search.Search
import org.springframework.stereotype.Service

@Service
class MemberService (private val memberDomainService: MemberDomainService) {

    fun getMembers(): List<Member> {
        return memberDomainService.getMembers();
    }

    fun getMemberDetail(id: Long): Member {

        val searchMember = Search()
        searchMember.classInfo(Member(name = "member1"))

        return memberDomainService.getMemberDetail(id).get();
    }

}