package com.team.innergrim.innergrimapi.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig {

    @Bean
    fun webClient(): WebClient {
        return WebClient.builder()
            .baseUrl("http://innergram-chatbot:8000") // 기본 URL 설정
            .defaultHeader("Content-Type", "application/json")
            .build()
    }

}