package com.facegram.facegrambackend.exceptionhandler.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.UNAUTHORIZED)
class UnAuthorizedException(message: String?): RuntimeException(message) {
}