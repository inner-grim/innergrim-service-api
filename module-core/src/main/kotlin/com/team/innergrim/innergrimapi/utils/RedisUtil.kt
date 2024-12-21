package com.team.innergrim.innergrimapi.utils

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class RedisUtil(
    private val redisTemplate: RedisTemplate<String, String>
) {

    fun getRedisValue(keyType: String, keyValue: String): String? {
        return redisTemplate.opsForValue().get("${keyType}_${keyValue}")
    }

    fun setRedisValue(keyType: String, keyValue: String, data:String, timeout: Long) {
        redisTemplate.opsForValue().set(
            "${keyType}_${keyValue}"
            , data
            , timeout
            , TimeUnit.MILLISECONDS
        )
    }
}