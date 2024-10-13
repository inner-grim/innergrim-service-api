package com.team.innergrim.innergrimapi.service

import com.team.innergrim.innergrimapi.dto.SearchMemberDto
import com.team.innergrim.innergrimapi.entity.Member
import com.team.innergrim.innergrimapi.enums.ErrorCode
import com.team.innergrim.innergrimapi.exception.BusinessException
import com.team.innergrim.innergrimapi.web.dto.AuthRequestDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthService (
    private val memberDomainService: MemberDomainService,
    private val authenticationManager: AuthenticationManager,
    @Autowired val redisTemplate: RedisTemplate<String, String>
) {


    // ::::: [GET] :::::

    // ::::: [CREATE] :::::
    fun memberLogin(memberLoginDto: AuthRequestDto.MemberLogin): Member {

        // 1. 사용자 조회
        val member = memberDomainService.getMemberDetail(
                SearchMemberDto(
                    socialId = memberLoginDto.socialId,
                ).specification
                ).orElseThrow{BusinessException(ErrorCode.NOT_FOUND, "member") }

        // 2. UserDetails 객체 생성
//        val userDetails: UserDetails = User(
//            member.socialId,  // 사용자명
//            "",  // 비밀번호 (또는 다른 자격 증명)
//            true,   // 활성화 여부
//            true,   // 계정 만료 여부
//            true,   // 자격 증명 만료 여부
//            true,   // 계정 잠금 여부
//            emptyList() // 권한 목록
//        )

        // 3. 인증처리
        val authenticationToken = UsernamePasswordAuthenticationToken(
            member.socialId, null, Collections.emptyList()
        )
        try {
            val authentication: Authentication = authenticationManager.authenticate(authenticationToken)
        } catch (e:Exception) {
            e.printStackTrace()
        }
        return member
    }
}