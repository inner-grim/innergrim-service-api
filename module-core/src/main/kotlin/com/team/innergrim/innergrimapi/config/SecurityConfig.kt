package com.team.innergrim.innergrimapi.config

import com.team.innergrim.innergrimapi.filter.JwtAuthenticationFilter
import com.team.innergrim.innergrimapi.service.CustomUserDetailService
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
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val customUserDetailService: CustomUserDetailService
) {
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
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/css/**",
                        "/js/**"
                    ).permitAll()
                    .requestMatchers("/health").permitAll()
                    .requestMatchers(
                        "/auth/member/login",
                        "/auth/admin/login",
                        "/auth/validate/access-token",
                        "/auth/issue/access-token"
                    ).permitAll() // 로그인 엔드포인트는 누구나 접근 가능
                    .requestMatchers(HttpMethod.POST,"/member").permitAll() // 회원가입은 토큰 불필요
                    .anyRequest().authenticated() // 나머지 요청은 인증 필요
            }
            .authenticationProvider(daoAuthenticationProvider())
            .userDetailsService(customUserDetailService)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
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
}

//class NoPasswordEncoder : PasswordEncoder {
//    override fun encode(rawPassword: CharSequence): String {
//        return rawPassword.toString() // 평문 그대로 반환
//    }
//
//    override fun matches(rawPassword: CharSequence, encodedPassword: String): Boolean {
//        return true // 항상 true 반환하여 비밀번호 검증을 우회
//    }
//}