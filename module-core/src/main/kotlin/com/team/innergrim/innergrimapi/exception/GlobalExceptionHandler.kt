package com.team.innergrim.innergrimapi.exception

import com.team.innergrim.innergrimapi.enums.ErrorCode
import com.team.innergrim.innergrimapi.response.ErrorResponse
import org.slf4j.MDC
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ErrorResponse<Unit> {
        val errors = ex.bindingResult.fieldErrors.associate { it.field to it.defaultMessage }
        return ErrorResponse(ErrorCode.BAD_REQUEST, errors.toString())
    }

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(ex: BusinessException): ErrorResponse<Unit> {
        return ErrorResponse(ex.statusCode, ex.message)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ErrorResponse<Unit> {
        return ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR, ex.message)
    }

    @ModelAttribute
    fun setThreadId() {
        MDC.put("threadId", Thread.currentThread().threadId().toString())
    }

}