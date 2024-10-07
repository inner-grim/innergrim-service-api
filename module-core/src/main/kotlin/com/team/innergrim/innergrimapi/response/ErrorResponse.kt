package com.team.innergrim.innergrimapi.response

data class ErrorResponse<T>(
    val statusCode: String,
    val description: String,
) {

}