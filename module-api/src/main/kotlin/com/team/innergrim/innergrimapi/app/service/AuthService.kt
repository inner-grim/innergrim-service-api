package com.team.innergrim.innergrimapi.app.service

import com.team.innergrim.innergrimapi.app.web.dto.AuthRequestDto
import com.team.innergrim.innergrimapi.app.web.dto.AuthResponseDto
import com.team.innergrim.innergrimapi.dto.SearchMemberDto
import com.team.innergrim.innergrimapi.enums.ErrorCode
import com.team.innergrim.innergrimapi.enums.MemberType
import com.team.innergrim.innergrimapi.exception.BusinessException
import com.team.innergrim.innergrimapi.service.MemberDomainService
import com.team.innergrim.innergrimapi.utils.AES256EncryptUtil
import com.team.innergrim.innergrimapi.utils.JwtUtil
import com.team.innergrim.innergrimapi.utils.RedisUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService (
    private val memberDomainService: MemberDomainService,
    private val passwordEncoder: PasswordEncoder,
    @Autowired val redisTemplate: RedisTemplate<String, String>,
    private val redisUtil: RedisUtil
) {
    
    // ::::: [GET] :::::

    // ::::: [CREATE] :::::
    fun memberLogin(memberLoginDto: AuthRequestDto.MemberLogin): AuthResponseDto.MemberLogin {

        // 1. 사용자 조회
        val member = memberDomainService.getMemberDetail(
                SearchMemberDto(
                    loginId = memberLoginDto.loginId,
                    memberType = MemberType.user
                ).specification
                ).orElseThrow{BusinessException(ErrorCode.NOT_FOUND, "member") }

        // 2. 인증처리
        val authenticationToken = UsernamePasswordAuthenticationToken(
            member.loginId, passwordEncoder.encode("")
        )

        // 3. token 생성
        val accessToken = JwtUtil.createAccessToken(member.id.toString())
        val refreshToken = JwtUtil.createRefreshToken(member.id.toString())

        // 4. redis에 토큰 저장
        redisUtil.setRedisValue(
            "member_accessToken"
            , member.id.toString()
            , accessToken
            , JwtUtil.getAccessTokenExpiry()
        )

        redisUtil.setRedisValue(
            "member_refreshToken"
            , member.id.toString()
            , refreshToken
            , JwtUtil.getAccessTokenExpiry()
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

        val memberId = JwtUtil.getUsername(issueAccessTokenDto.refreshToken)

        // 2. Redis에서 refreshToken 조회
        val savedRefreshToken = redisTemplate.opsForValue().get("member_refreshToken_${memberId}")
        if (savedRefreshToken.isNullOrEmpty() || savedRefreshToken != issueAccessTokenDto.refreshToken) {
            throw BusinessException(ErrorCode.NOT_FOUND, "RefreshToken Is Not Found or Mismatch")
        }

        // 3. 새로운 accessToken 생성
        val newAccessToken = JwtUtil.createAccessToken(memberId)
        redisUtil.setRedisValue(
            "member_accessToken"
            , memberId
            , newAccessToken
            ,JwtUtil.getAccessTokenExpiry()
        )

//        redisTemplate.opsForValue().set(
//            "member_accessToken_${memberId}",
//            newAccessToken,
//            JwtUtil.getAccessTokenExpiry(), // Redis에 저장될 Access token의 만료 시간
//            TimeUnit.MILLISECONDS
//        )

        // 4. 반환
        return AuthResponseDto.IssueAccessToken(
            accessToken = newAccessToken,
            refreshToken = issueAccessTokenDto.refreshToken // 기존 refreshToken 그대로 반환
        )
    }
}