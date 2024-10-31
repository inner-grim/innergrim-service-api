package com.team.innergrim.innergrimapi.service

import com.team.innergrim.innergrimapi.dto.SearchMemberDto
import com.team.innergrim.innergrimapi.entity.Member
import com.team.innergrim.innergrimapi.enums.ErrorCode
import com.team.innergrim.innergrimapi.exception.BusinessException
import com.team.innergrim.innergrimapi.web.dto.MemberRequestDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

@Service
class MemberService (
    private val memberDomainService: MemberDomainService,
    private val membershipDomainService: MembershipDomainService,
    @Autowired val redisTemplate: RedisTemplate<String, String>
) {

    // ::::: [GET] :::::
    fun getMembers(): List<Member> {
        return memberDomainService.getMembers()
    }

    fun getMemberDetail(id: Long): Member {

        redisTemplate.opsForValue().set("MemberService_getMemberDetail_id:$id", "value")

        return memberDomainService.getMemberDetail(
            SearchMemberDto(
                id = id,
            ).specification
        ).get()
    }

    // ::::: [CREATE] :::::

    fun createMember(createMemberRequestDto: MemberRequestDto.Create) {
        val membership = membershipDomainService.getMemberDetail(1L)
            .orElseThrow{
                BusinessException(ErrorCode.NOT_FOUND, "membership")
            }
        memberDomainService.createMember(createMemberRequestDto.toMemberEntity(membership))
    }
}