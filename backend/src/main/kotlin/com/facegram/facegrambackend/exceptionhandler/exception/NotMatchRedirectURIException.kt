package com.facegram.facegrambackend.exceptionhandler.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class NotMatchRedirectURIException(message: String?): RuntimeException(message) {
}