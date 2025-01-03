package com.team.innergrim.innergrimapi.filter

import com.team.innergrim.innergrimapi.config.SecurityConfig
import com.team.innergrim.innergrimapi.service.CustomUserDetailService
import com.team.innergrim.innergrimapi.utils.JwtUtil
import com.team.innergrim.innergrimapi.utils.RedisUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.util.AntPathMatcher
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val userDetailsService: CustomUserDetailService,
    private val redisUtil: RedisUtil,
): OncePerRequestFilter() {

    companion object {
        private const val BEARER_PREFIX = "Bearer "
        private const val AUTHORIZATION_HEADER = "Authorization"
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        // 필터 제외 처리
        if (SecurityConfig.EXCLUDE_PATHS.any { AntPathMatcher().match(it, request.requestURI) }) {
            filterChain.doFilter(request, response)
            return
        }

        try {
            val token = resolveToken(request)

            if (token != null && JwtUtil.validateToken(token)) {
                val id = JwtUtil.getUsername(token)

                // Redis에서 accessToken 유효성 확인
                val redisToken = redisUtil.getRedisValue("member:$id:accessToken")
                if (redisToken == null || redisToken != token) {
                    throw SecurityException("Invalid or expired accessToken in Redis.")
                }

                val userDetails = userDetailsService.loadUserByUsername(id)
                setAuthentication(userDetails, request)
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "AccessToken is invalid")
            }
        } catch (e: Exception) {
            logger.error("JWT Authentication failed: ${e.message}", e)
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.message)
        }

        filterChain.doFilter(request, response)
    }

    // 요청 헤더에서 Bearer 토큰을 추출
    private fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(AUTHORIZATION_HEADER)
        return if (!bearerToken.isNullOrBlank() && bearerToken.startsWith(BEARER_PREFIX)) {
            bearerToken.substring(BEARER_PREFIX.length)
        } else {
            null
        }
    }

    // SecurityContextHolder에 인증 설정
    private fun setAuthentication(userDetails: UserDetails, request: HttpServletRequest) {
        val authentication = UsernamePasswordAuthenticationToken(
            userDetails,
            null,
            userDetails.authorities
        )
        authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
        SecurityContextHolder.getContext().authentication = authentication
    }
}