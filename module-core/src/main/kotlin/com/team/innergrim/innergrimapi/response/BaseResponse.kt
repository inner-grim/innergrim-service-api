package com.team.innergrim.innergrimapi.response

data class BaseResponse<T>(
    val statusCode: String,
    val message: String,
    val data: T? = null
) {
    companion object {
        const val SUCCESS_CODE = "OK"
        const val SUCCESS_DESCRIPTION = "success"

        // 성공 시 데이터를 포함한 응답 반환
        fun <T> successWithData(data: T): BaseResponse<T> {
            return BaseResponse(SUCCESS_CODE, SUCCESS_DESCRIPTION, data)
        }

        // 성공 시 데이터 없이 응답 반환
        fun <T> successWithoutData(): BaseResponse<T> {
            return BaseResponse(SUCCESS_CODE, SUCCESS_DESCRIPTION, null)
        }
    }
}