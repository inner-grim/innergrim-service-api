package com.team.innergrim.innergrimapi.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
class SwaggerConfig {

    @Bean
    @Profile("!prd") // prd가 아닌 모든 프로파일에서 적용
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("InnerGrim Service API Docs")
                    .version("1.0.0")
                    .description("InnerGrim Service API Docs")
            )
            .components(
                Components()
                    .addSecuritySchemes("bearerAuth", SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                    )
            )
            .addSecurityItem(
                SecurityRequirement()
                    .addList("bearerAuth")
            )
    }

    @Bean
    @Profile("prd") // prd 프로파일에서만 적용
    fun customOpenAPIForPrd(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("InnerGrim Service API Docs (Production)")
                    .version("1.0.0")
                    .description("InnerGrim Service API Docs for Production")
            )
            .components(
                Components()
                    .addSecuritySchemes("bearerAuth", SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                    )
            )
            .addSecurityItem(
                SecurityRequirement()
                    .addList("bearerAuth")
            )
            .servers(
                listOf(
                    io.swagger.v3.oas.models.servers.Server()
                        .url("https://api.innergrim.info")
                        .description("Production Server")
                )
            )
    }
}