package com.team.innergrim.innergrimapi.web

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/health")
@RestController
@Tag(name = "health")
class HealthCheckController {

    @GetMapping
    fun healthCheck () : String {
        return "::: [health check ok] :::"
    }
}