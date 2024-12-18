package com.team.innergrim.innergrimapi.service

import com.team.innergrim.innergrimapi.dto.SearchMemberDto
import com.team.innergrim.innergrimapi.enums.ErrorCode
import com.team.innergrim.innergrimapi.exception.BusinessException
import com.team.innergrim.innergrimapi.utils.AES256EncryptUtil
import com.team.innergrim.innergrimapi.utils.JwtUtil
import com.team.innergrim.innergrimapi.web.dto.AuthRequestDto
import com.team.innergrim.innergrimapi.web.dto.AuthResponseDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class AuthService (
    private val memberDomainService: MemberDomainService,
    private val passwordEncoder: PasswordEncoder,
    @Autowired val redisTemplate: RedisTemplate<String, String>
) {
    
    // ::::: [GET] :::::

    // ::::: [CREATE] :::::
    fun memberLogin(memberLoginDto: AuthRequestDto.MemberLogin): AuthResponseDto.MemberLogin {

        // 1. 사용자 조회
        val member = memberDomainService.getMemberDetail(
                SearchMemberDto(
                    socialId = memberLoginDto.socialId,
                ).specification
                ).orElseThrow{BusinessException(ErrorCode.NOT_FOUND, "member") }

        // 2. 인증처리
        val authenticationToken = UsernamePasswordAuthenticationToken(
            member.socialId, passwordEncoder.encode("")
        )

        // 3. token 생성
        val accessToken = JwtUtil.createAccessToken(member.socialId)
        val refreshToken = JwtUtil.createRefreshToken(member.socialId)

        // 4. redis에 토큰 저장
        redisTemplate.opsForValue().set(
            "refreshToken_${member.socialId}",
            refreshToken,
            JwtUtil.getRefreshTokenExpiry(), // Redis에 저장될 refresh token의 만료 시간
            TimeUnit.MILLISECONDS
        )

        return AuthResponseDto.MemberLogin(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }

    fun adminLogin(adminLoginDto: AuthRequestDto.AdminLogin): AuthResponseDto.AdminLogin {

        AES256EncryptUtil.encrypt("")

        // 1. 관리자 조회

        // 2. 인증 처리

        // 3. 토큰 생성

        // 4. redis에 토큰 저장

        // 5. 응답
        return AuthResponseDto.AdminLogin(
            accessToken = "",
            refreshToken = ""
        )
    }

    fun validateAccessToken(validateAccessTokenDto: AuthRequestDto.ValidateAccessToken) {
        //토큰 검증
        if (!JwtUtil.validateToken(validateAccessTokenDto.accessToken))
            throw BusinessException(ErrorCode.NOT_VALID, "AccessToken Is Not Valid")
    }

    fun issueAccessToken(issueAccessTokenDto: AuthRequestDto.IssueAccessToken): AuthResponseDto.IssueAccessToken {
        // 토큰 검증
        if (!JwtUtil.validateToken(issueAccessTokenDto.refreshToken))
            throw BusinessException(ErrorCode.NOT_VALID, "RefreshToken Is Not Valid")

        val socialId = JwtUtil.getUsername(issueAccessTokenDto.refreshToken)

        // 2. Redis에서 refreshToken 조회
        val savedRefreshToken = redisTemplate.opsForValue().get("refreshToken_${socialId}")
        if (savedRefreshToken.isNullOrEmpty() || savedRefreshToken != issueAccessTokenDto.refreshToken) {
            throw BusinessException(ErrorCode.NOT_FOUND, "RefreshToken Is Not Found or Mismatch")
        }

        // 3. 새로운 accessToken 생성
        val newAccessToken = JwtUtil.createAccessToken(socialId)

        // 4. 반환
        return AuthResponseDto.IssueAccessToken(
            accessToken = newAccessToken,
            refreshToken = issueAccessTokenDto.refreshToken // 기존 refreshToken 그대로 반환
        )
    }
}