package com.team.innergrim.innergrimapi.app.service

import com.team.innergrim.innergrimapi.app.web.dto.MemberRequestDto
import com.team.innergrim.innergrimapi.dto.SearchMemberDto
import com.team.innergrim.innergrimapi.entity.Member
import com.team.innergrim.innergrimapi.enums.ErrorCode
import com.team.innergrim.innergrimapi.exception.BusinessException
import com.team.innergrim.innergrimapi.service.MemberDomainService
import com.team.innergrim.innergrimapi.service.RoleDomainService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

@Service
class MemberService (
    private val memberDomainService: MemberDomainService,
    private val roleDomainService: RoleDomainService,
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
        ).orElseThrow { BusinessException(ErrorCode.NOT_FOUND, "member") }
    }

    // ::::: [CREATE] :::::

    fun createMember(createMemberRequestDto: MemberRequestDto.CreateMember) {
        val role = roleDomainService.getMemberDetail(1L)
            .orElseThrow{ BusinessException(ErrorCode.NOT_FOUND, "membership") }
        memberDomainService.createMember(createMemberRequestDto.toMemberEntity(role))
    }

    // ::::: [UPDATE] :::::

    fun createOnBoarding(createMemberRequestDto: MemberRequestDto.CreateOnBoarding) {
        val member = memberDomainService.getMemberDetail(createMemberRequestDto.id)
            .orElseThrow { BusinessException(ErrorCode.NOT_FOUND, "member") }
        createMemberRequestDto.updateMemberEntity(member)
        memberDomainService.updateMember(member)
    }
}