package com.team.innergrim.innergrimapi.service

import com.team.innergrim.innergrimapi.dto.SearchMemberDto
import com.team.innergrim.innergrimapi.entity.Member
import com.team.innergrim.innergrimapi.enums.ErrorCode
import com.team.innergrim.innergrimapi.exception.BusinessException
import com.team.innergrim.innergrimapi.search.Search
import com.team.innergrim.innergrimapi.web.dto.MemberRequestDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.time.LocalDateTime

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
                BusinessException(ErrorCode.NOT_FOUND, "")
            }
        memberDomainService.createMember(createMemberRequestDto.toMemberEntity(membership))
    }
}