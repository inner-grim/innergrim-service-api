package com.team.innergrim.innergrimapi.service

import com.team.innergrim.innergrimapi.entity.Member
import com.team.innergrim.innergrimapi.repository.MemberRepository
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.util.*

@Service
class MemberDomainService (private val memberRepository: MemberRepository) {

    // ::::: [GET] :::::
    fun getMembers (): List<Member> {
        return memberRepository.findAll()
    }

    fun getMemberDetail (id: Long): Optional<Member> {
        return memberRepository.findById(id)
    }

    fun getMemberDetail(specification: Specification<Member>): Optional<Member> {
        return memberRepository.findOne(specification)
    }

    // ::::: [CREATE] :::::
    fun createMember(member: Member) {
        memberRepository.save(member)
    }

    // ::::: [UPDATE] :::::
    fun updateMember(member: Member) {
        memberRepository.save(member)
    }

}