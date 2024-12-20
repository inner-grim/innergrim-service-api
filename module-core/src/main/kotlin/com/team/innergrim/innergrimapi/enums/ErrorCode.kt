package com.team.innergrim.innergrimapi.enums

enum class ErrorCode(
    val desc: String
) {
    BAD_REQUEST(""),
    NOT_FOUND(""),
    INTERNAL_SERVER_ERROR(""),
    NOT_VALID(""),
    FILE_UPLOAD_FAIL(""),
    CREATE_FAIL(""),
}