package com.team.innergrim.innergrimapi.utils

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class RedisUtil(
    private val redisTemplate: RedisTemplate<String, String>
) {

    fun getRedisValue(key: String): String? {
        return redisTemplate.opsForValue().get(key)
    }

    fun setRedisValue (key: String, value: String, timeout: Long, timeUnit: TimeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit)
    }

    fun setRedisValueMap(key: String, valueMap: Map<String, String>) {
        redisTemplate.opsForHash<String, String>().putAll(key, valueMap)
    }

    fun setExpire (key: String, timeout: Long, timeUnit: TimeUnit) {
        redisTemplate.expire(key, timeout, timeUnit)
    }

    fun deleteMemberTokens(id: String) {
        val memberKey = "member:$id"
        redisTemplate.delete(memberKey)
    }
}