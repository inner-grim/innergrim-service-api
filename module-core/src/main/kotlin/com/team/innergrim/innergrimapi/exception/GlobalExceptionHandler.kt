package com.team.innergrim.innergrimapi.exception

import com.team.innergrim.innergrimapi.response.ErrorResponse
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ErrorResponse<Unit> {
        val errors = ex.bindingResult.fieldErrors.associate { it.field to it.defaultMessage }
        return ErrorResponse("ERROR", errors.toString())
    }

}