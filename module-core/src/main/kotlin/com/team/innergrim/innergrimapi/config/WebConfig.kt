package com.team.innergrim.innergrimapi.config

import com.team.innergrim.innergrimapi.filter.RequestResponseLoggingFilter
import com.team.innergrim.innergrimapi.interceptor.ApiLoggingInterceptor
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(
    private val apiLoggingInterceptor: ApiLoggingInterceptor
) : WebMvcConfigurer  {

    /**
     * Add Interceptors
     */
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(apiLoggingInterceptor)
    }

    /**
     * Add Filters
     */
    @Bean
    fun loggingFilter(): FilterRegistrationBean<RequestResponseLoggingFilter> {
        val registrationBean = FilterRegistrationBean<RequestResponseLoggingFilter>()
        registrationBean.filter = RequestResponseLoggingFilter()
        registrationBean.addUrlPatterns("/*")
        return registrationBean
    }

}