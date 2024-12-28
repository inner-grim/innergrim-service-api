package com.team.innergrim.innergrimapi.app.service

import com.team.innergrim.innergrimapi.app.web.dto.AuthRequestDto
import com.team.innergrim.innergrimapi.app.web.dto.AuthResponseDto
import com.team.innergrim.innergrimapi.dto.SearchMemberDto
import com.team.innergrim.innergrimapi.enums.ErrorCode
import com.team.innergrim.innergrimapi.enums.MemberType
import com.team.innergrim.innergrimapi.exception.BusinessException
import com.team.innergrim.innergrimapi.service.MemberDomainService
import com.team.innergrim.innergrimapi.utils.JwtUtil
import com.team.innergrim.innergrimapi.utils.RedisUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class AuthService (
    private val memberDomainService: MemberDomainService,
    @Autowired val redisTemplate: RedisTemplate<String, String>,
    private val redisUtil: RedisUtil,
    private val authenticationManager: AuthenticationManager,
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
                ).orElseThrow{ BusinessException(ErrorCode.NOT_FOUND, "member") }

        // 2. 인증처리
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(member.id, "")
        )
        SecurityContextHolder.getContext().authentication = authentication

        // 3. token 생성
        val accessToken = JwtUtil.createAccessToken(member.id.toString())
        val refreshToken = JwtUtil.createRefreshToken(member.id.toString())

        // 4. redis에 토큰 저장
        this.saveMemberTokens(member.id, accessToken, refreshToken, JwtUtil.getAccessTokenExpiry(), JwtUtil.getRefreshTokenExpiry())

        return AuthResponseDto.MemberLogin(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }

    fun adminLogin(adminLoginDto: AuthRequestDto.AdminLogin): AuthResponseDto.AdminLogin {
        // 1. 관리자 조회
        val member = memberDomainService.getMemberDetail(
            SearchMemberDto(
                loginId = adminLoginDto.loginId,
                memberType = MemberType.admin
            ).specification
        ).orElseThrow{ BusinessException(ErrorCode.NOT_FOUND, "admin") }

        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(member.id, adminLoginDto.password)
        )
        SecurityContextHolder.getContext().authentication = authentication

        // 3. 토큰 생성
        val accessToken = JwtUtil.createAccessToken(member.id.toString())
        val refreshToken = JwtUtil.createRefreshToken(member.id.toString())

        // 4. redis에 토큰 저장
        this.saveMemberTokens(member.id, accessToken, refreshToken, JwtUtil.getAccessTokenExpiry(), JwtUtil.getRefreshTokenExpiry())

        // 5. 응답
        return AuthResponseDto.AdminLogin(
            accessToken = accessToken,
            refreshToken = refreshToken
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
        this.saveAccessToken(memberId.toLong(), newAccessToken, JwtUtil.getAccessTokenExpiry())

        // 4. 반환
        return AuthResponseDto.IssueAccessToken(
            accessToken = newAccessToken,
            refreshToken = issueAccessTokenDto.refreshToken // 기존 refreshToken 그대로 반환
        )
    }

    private fun saveMemberTokens (
        id: Long,
        accessToken: String,
        refreshToken: String,
        accessTokenExpireTime: Long,
        refreshTokenExpireTime: Long
    ) {
        // accessToken 캐싱
        this.saveAccessToken(id, accessToken, accessTokenExpireTime)
        // refreshToken 캐싱
        this.saveRefreshToken(id, refreshToken, refreshTokenExpireTime)
    }

    private fun saveAccessToken (id: Long, accessToken: String, accessTokenExpireTime: Long) {
        val accessTokenKey = "member:$id:accessToken"
        redisUtil.setRedisValue(
            accessTokenKey,
            accessToken,
            accessTokenExpireTime,
            TimeUnit.MILLISECONDS
        )
    }

    private fun saveRefreshToken (id: Long, refreshToken: String, refreshTokenExpireTime: Long) {
        val refreshTokenKey = "member:$id:refreshToken"
        redisUtil.setRedisValue(
            refreshTokenKey,
            refreshToken,
            refreshTokenExpireTime,
            TimeUnit.MILLISECONDS
        )
    }
}