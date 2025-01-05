package com.team.innergrim.innergrimapi.config

import com.team.innergrim.innergrimapi.filter.JwtAuthenticationFilter
import com.team.innergrim.innergrimapi.service.CustomUserDetailService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val customUserDetailService: CustomUserDetailService
) {

    companion object {
        val EXCLUDE_PATHS_GET = arrayOf(
            "/swagger-ui/**",
            "/h2-console/**",
            "/v3/api-docs/**",
            "/css/**",
            "/js/**",
            "/favicon.ico",
            "/health",
        )

        val EXCLUDE_PATHS_POST = arrayOf(
            "/auth/member/login",
            "/auth/admin/login",
            "/auth/validate/access-token",
            "/auth/issue/access-token",
            "/member",
        )
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            // 사이트 위변조 요청 방지
            .csrf { it.disable() }
            .sessionManagement { sessionManagement ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeHttpRequests { authorizeRequests ->
                authorizeRequests
                    .requestMatchers(
                        HttpMethod.GET,
                        *EXCLUDE_PATHS_GET
                    ).permitAll()
                    .requestMatchers(
                        HttpMethod.POST,
                        *EXCLUDE_PATHS_POST
                    ).permitAll()
                    .requestMatchers(
                        HttpMethod.OPTIONS,
                        "/**"
                    ).permitAll()
                    .anyRequest().authenticated() // 나머지 요청은 인증 필요
            }
            .authenticationProvider(daoAuthenticationProvider())
            .userDetailsService(customUserDetailService)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .exceptionHandling { exceptionHandling -> exceptionHandling.authenticationEntryPoint(authenticationEntryPoint()) }
        return http.build()
    }

    // DaoAuthenticationProvider 설정 추가
    @Bean
    fun daoAuthenticationProvider(): DaoAuthenticationProvider {
        val daoAuthenticationProvider = DaoAuthenticationProvider()
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder())
        daoAuthenticationProvider.setUserDetailsService(customUserDetailService)
        return daoAuthenticationProvider
    }

    // AuthenticationManager를 빈으로 등록
    @Bean
    fun authenticationManager(http: HttpSecurity): AuthenticationManager {
        return http.getSharedObject(AuthenticationManagerBuilder::class.java)
            .authenticationProvider(daoAuthenticationProvider())
            .build()
    }

    // 비밀번호 인코더 (선택사항: 비밀번호를 암호화할 때 사용)
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    // Unauthorized Entry point 설정
    @Bean
    fun authenticationEntryPoint(): AuthenticationEntryPoint {
        return AuthenticationEntryPoint { _, response, _ ->
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
        }
    }
}