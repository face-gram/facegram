package com.facegram.facegrambackend.util

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.FORBIDDEN)
class NoAuthorizationException(message: String?) : RuntimeException(message) {
}