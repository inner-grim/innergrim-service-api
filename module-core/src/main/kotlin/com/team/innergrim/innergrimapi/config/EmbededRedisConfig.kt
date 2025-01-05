package com.team.innergrim.innergrimapi.config

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import redis.embedded.RedisServer
import java.io.IOException
import java.net.ServerSocket

@Configuration
@Profile("local")
class EmbededRedisConfig {

    private lateinit var redisServer: RedisServer
    private val redisPort = 6379

    @PostConstruct
    fun startRedis() {
        if (!isPortInUse(redisPort)) {
            redisServer = RedisServer(redisPort)
            redisServer.start()
            println("Redis server started on port $redisPort")
        } else {
            println("Redis server is already running on port $redisPort")
        }
    }

    @PreDestroy
    fun stopRedis() {
        redisServer.stop()
    }

    private fun isPortInUse(port: Int): Boolean {
        return try {
            ServerSocket(port).use { false }
        } catch (e: IOException) {
            true
        }
    }

}