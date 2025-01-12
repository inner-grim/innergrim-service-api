package com.team.innergrim.innergrimapi.service

import com.team.innergrim.innergrimapi.enums.ErrorCode
import com.team.innergrim.innergrimapi.enums.ExternalServiceType
import com.team.innergrim.innergrimapi.exception.BusinessException
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class ExternalApiService (
    private val chatbotWebClient: WebClient,
    private val discordWebClient: WebClient,
) {

    fun get(
        externalServiceType: ExternalServiceType,
        endpoint: String,
    ): Mono<String> {
        if (externalServiceType == ExternalServiceType.chatbot) {
            return this.getData(chatbotWebClient, endpoint)
        } else if (externalServiceType == ExternalServiceType.discord) {
            return this.getData(discordWebClient, endpoint)
        } else {
            throw BusinessException(ErrorCode.INVALID_EXTERNAL_SERVICE, externalServiceType.name)
        }
    }

    fun <T : Any> post(
        externalServiceType: ExternalServiceType,
        endpoint: String,
        requestBody: T,
    ): Mono<String> {
        if (externalServiceType == ExternalServiceType.chatbot) {
            return this.postData(chatbotWebClient, endpoint, requestBody)
        } else if (externalServiceType == ExternalServiceType.discord) {
            return this.postData(discordWebClient, endpoint, requestBody)
        } else {
            throw BusinessException(ErrorCode.INVALID_EXTERNAL_SERVICE, externalServiceType.name)
        }
    }

    // ::::: [GET] :::::
    fun getData(webClient: WebClient, endpoint: String): Mono<String> {
        val result = webClient.get()
            .uri("/fact") // baseUrl에 추가되는 경로
            .retrieve()
            .onStatus({ it.is4xxClientError }) {
                Mono.error(RuntimeException("Client error: ${it.statusCode()}"))
            }
            .onStatus({ it.is5xxServerError }) {
                Mono.error(RuntimeException("Server error: ${it.statusCode()}"))
            }
            .bodyToMono(String::class.java) // 원하는 반환 타입
        return result
    }

    // ::::: [POST] :::::
    fun <T : Any> postData(webClient: WebClient, endpoint: String, requestBody: T): Mono<String> {
        return webClient.post()
            .uri(endpoint) // baseUrl에 추가되는 경로
            .bodyValue(requestBody) // 요청 본문 설정
            .retrieve()
            .onStatus({ it.is4xxClientError }) {
                Mono.error(RuntimeException("Client error: ${it.statusCode()}"))
            }
            .onStatus({ it.is5xxServerError }) {
                Mono.error(RuntimeException("Server error: ${it.statusCode()}"))
            }
            .bodyToMono(String::class.java) // 원하는 반환 타입
    }
}