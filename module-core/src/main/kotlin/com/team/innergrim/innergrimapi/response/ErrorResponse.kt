package com.team.innergrim.innergrimapi.response

import com.team.innergrim.innergrimapi.enums.ErrorCode

data class ErrorResponse<T>(
    val statusCode: ErrorCode,
    val message: String,
) {

}