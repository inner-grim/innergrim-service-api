package com.team.innergrim.innergrimapi.web

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/health")
@RestController
class HealthCheckController {

    @GetMapping
    fun healthCheck () : String {
        return "::: [health check ok] :::"
    }
}