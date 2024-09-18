package com.team.innergrim.innergrimapi.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("InnerGrim Service API Docs")
                    .version("1.0.0")
                    .description("InnerGrim Service API Docs")
            )
    }

}