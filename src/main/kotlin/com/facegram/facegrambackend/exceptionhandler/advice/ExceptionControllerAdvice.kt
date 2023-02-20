package com.facegram.facegrambackend.exceptionhandler.advice

import com.facegram.facegrambackend.exceptionhandler.ErrorResponse
import com.facegram.facegrambackend.util.NoAuthorizationException
import com.facegram.facegrambackend.util.NotMatchRedirectURIException
import com.facegram.facegrambackend.util.UnAuthorizedException
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

    @ResponseStatus(UNAUTHORIZED) // 에러코드
    @ExceptionHandler(UnAuthorizedException::class)
    fun JwtAuthenticationEntryPointExceptionHandler(e: UnAuthorizedException): ErrorResponse{
        return ErrorResponse("UnAuthorized",e.message.orEmpty())
    }

    @ResponseStatus(FORBIDDEN) // 에러코드
    @ExceptionHandler(NoAuthorizationException::class)
    fun JwtAccessDeniedExceptionHandler(e: NoAuthorizationException): ErrorResponse{
        return ErrorResponse("Forbidden",e.message.orEmpty())
    }

    @ResponseStatus(BAD_REQUEST) // 에러코드
    @ExceptionHandler(NotMatchRedirectURIException::class)
    fun NotMatchRedirectURIExceptionHandler(e: NotMatchRedirectURIException): ErrorResponse{
        return ErrorResponse("Bad_Request",e.message.orEmpty())
    }


}