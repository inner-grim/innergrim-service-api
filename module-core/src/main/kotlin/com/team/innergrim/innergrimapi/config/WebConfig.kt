package com.team.innergrim.innergrimapi.config

import com.team.innergrim.innergrimapi.filter.ApiLoggingFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
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
            "/picture-diary/*"
        )
        registrationBean.order = 1 // 우선순위 설정
        return registrationBean
    }



}