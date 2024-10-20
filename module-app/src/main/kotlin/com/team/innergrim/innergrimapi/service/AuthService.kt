package com.team.innergrim.innergrimapi.service

import com.team.innergrim.innergrimapi.dto.SearchMemberDto
import com.team.innergrim.innergrimapi.enums.ErrorCode
import com.team.innergrim.innergrimapi.exception.BusinessException
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

    fun validateAccessToken(validateAccessToken: AuthRequestDto.ValidateAccessToken) {
        //토큰 검증
        if (!JwtUtil.validateToken(validateAccessToken.accessToken))
            throw BusinessException(ErrorCode.NOT_VALID, "AccessToken Is Not Valid")
    }
}