package com.team.innergrim.innergrimapi.config

import com.team.innergrim.innergrimapi.filter.ApiLoggingFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(
) : WebMvcConfigurer  {

    @Bean
    fun loggingFilter(): FilterRegistrationBean<ApiLoggingFilter> {
        val registrationBean = FilterRegistrationBean<ApiLoggingFilter>()
        registrationBean.filter = ApiLoggingFilter()
        registrationBean.addUrlPatterns(
            "/auth/*",
            "/chat-bot/*",
            "/health/*",
            "/member/*",
            "/picture-diary/*",
        )
        registrationBean.order = 1 // 우선순위 설정
        return registrationBean
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**") // 모든 경로에 대해 CORS 허용
            .allowedOrigins(
                "http://localhost:3000",
                "http://3.39.72.21:3000",
                "http://innergrim-cms:3000",
                "https://api.innergrim.info/",
                "https://cms.innergrim.info",
            ) // 허용할 Origin
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용할 HTTP 메서드
            .allowedHeaders("*") // 모든 헤더 허용
            .allowCredentials(true) // 쿠키 허용
    }
}