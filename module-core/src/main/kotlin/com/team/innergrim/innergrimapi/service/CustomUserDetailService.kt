package com.team.innergrim.innergrimapi.service

import com.team.innergrim.innergrimapi.repository.MemberRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailService (
    private val memberRepository: MemberRepository
): UserDetailsService {
    override fun loadUserByUsername(socialId: String): UserDetails {
        val member = memberRepository.findBySocialId(socialId)
            ?: throw UsernameNotFoundException("사용자를 찾을 수 없습니다: $socialId")

        return User(
            socialId,              // 사용자명
            null,                       // 비밀번호 (소셜 로그인에서는 비밀번호가 없으므로 빈 문자열 사용)
            true,                     // 활성화 여부
            true,                     // 계정 만료 여부
            true,                     // 자격 증명 만료 여부
            true,                     // 계정 잠금 여부
            emptyList()              // 권한 목록
        )
    }
}