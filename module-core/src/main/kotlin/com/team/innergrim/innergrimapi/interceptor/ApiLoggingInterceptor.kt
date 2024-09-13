package com.team.innergrim.innergrimapi.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
class ApiLoggingInterceptor : HandlerInterceptor {

    private val logger = LoggerFactory.getLogger(ApiLoggingInterceptor::class.java)

    override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse, handler: Any, ex: Exception?) {
        logger.info(
            """
            ::::: [Save Api Logging] :::::
            """
        )
    }

}