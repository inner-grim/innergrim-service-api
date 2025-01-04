package com.team.innergrim.innergrimapi.app.service

import com.team.innergrim.innergrimapi.app.web.dto.MemberRequestDto
import com.team.innergrim.innergrimapi.dto.SearchMemberDto
import com.team.innergrim.innergrimapi.entity.Member
import com.team.innergrim.innergrimapi.enums.ErrorCode
import com.team.innergrim.innergrimapi.enums.MemberStatus
import com.team.innergrim.innergrimapi.enums.MemberType
import com.team.innergrim.innergrimapi.exception.BusinessException
import com.team.innergrim.innergrimapi.service.MemberDomainService
import com.team.innergrim.innergrimapi.service.RoleDomainService
import com.team.innergrim.innergrimapi.utils.JwtUtil
import com.team.innergrim.innergrimapi.utils.RedisUtil
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class MemberService (
    private val memberDomainService: MemberDomainService,
    private val roleDomainService: RoleDomainService,
    private val passwordEncoder: PasswordEncoder,
    private val redisUtil: RedisUtil
) {

    // ::::: [GET] :::::
    fun getMembers(): List<Member> {
        return memberDomainService.getMembers()
    }

    fun getMemberDetail(id: Long): Member {
        return memberDomainService.getMemberDetail(
            SearchMemberDto(
                id = id,
                memberType = MemberType.user
            ).specification
        ).orElseThrow { BusinessException(ErrorCode.NOT_FOUND, "member") }
    }

    fun getDuplicateNickname(duplicateNicknameDto: MemberRequestDto.DuplicateNickname): Boolean {
        return memberDomainService.getMemberDetail(
            SearchMemberDto(
                nickname = duplicateNicknameDto.nickname,
                memberType = MemberType.user
            ).specification
        ).isPresent
    }

    // ::::: [CREATE] :::::

    fun createMember(createMemberRequestDto: MemberRequestDto.CreateMember) {
        val role = roleDomainService.getMemberDetail(1L)
            .orElseThrow{ BusinessException(ErrorCode.NOT_FOUND, "membership") }
        memberDomainService.createMember(createMemberRequestDto.toMemberEntity(role, passwordEncoder.encode("")))
    }

    // ::::: [UPDATE] :::::

    fun createOnBoarding(createMemberRequestDto: MemberRequestDto.CreateOnBoarding) {
        // 멤버 조회
        val member = memberDomainService.getMemberDetail(JwtUtil.getUsername().toLong())
            .orElseThrow { BusinessException(ErrorCode.NOT_FOUND, "member") }

        // 닉네임 중복 검사
        if (memberDomainService.getMemberDetail(
                SearchMemberDto(
                    nickname = createMemberRequestDto.nickname,
                    memberType = MemberType.user
                ).specification
            ).isPresent
        )
        {
            throw BusinessException(ErrorCode.DUPLICATE, "nickname")
        }

        createMemberRequestDto.updateMemberEntity(member)
        memberDomainService.updateMember(member)
    }

    // ::::: [DELETE] :::::

    fun deleteMember() {
        // 멤버 조회
        val member = memberDomainService.getMemberDetail(JwtUtil.getUsername().toLong())
            .orElseThrow { BusinessException(ErrorCode.NOT_FOUND, "member") }

        // 멤버 삭제 처리
        member.delete(MemberStatus.withdraw);
        memberDomainService.updateMember(member)

        // 토큰 제거
        redisUtil.delete("${this.getMemberCacheKey(member.id)}:accessToken");
        redisUtil.delete("${this.getMemberCacheKey(member.id)}:refreshToken");
    }

    private fun getMemberCacheKey(id: Long): String {
        return "member:${id}"
    }


}