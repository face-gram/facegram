package com.facegram.facegrambackend.exceptionhandler.advice

import com.facegram.facegrambackend.exceptionhandler.ErrorResponse
import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RestControllerAdvice
import kotlin.IllegalArgumentException

@RestControllerAdvice
class ExceptionControllerAdvice() {

    @ResponseStatus(BAD_REQUEST) // 에러코드
    @ExceptionHandler(IllegalArgumentException::class)
    fun illegalExceptionHandler(e: IllegalArgumentException): ErrorResponse{
        return ErrorResponse("BadRequest",e.message.orEmpty())
    }


}