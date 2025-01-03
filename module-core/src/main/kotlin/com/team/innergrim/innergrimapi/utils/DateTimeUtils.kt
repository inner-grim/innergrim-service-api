package com.team.innergrim.innergrimapi.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateTimeUtils {

    const val FORMAT_yyyyMMdd = "yyyyMMdd"
    const val FORMAT_yyyyMMddHHmmss = "yyyyMMddHHmmss"

    fun getDateTimeNow (format: String): String {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format))
    }

}