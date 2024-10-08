package com.team.innergrim.innergrimapi.exception

import com.team.innergrim.innergrimapi.enums.ErrorCode

class BusinessException(
    val statusCode: ErrorCode,
    override val message: String,
): RuntimeException() {

}