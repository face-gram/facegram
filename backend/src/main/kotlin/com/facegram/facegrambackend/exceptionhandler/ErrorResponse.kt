package com.facegram.facegrambackend.exceptionhandler

data class ErrorResponse constructor(
    private val code: String,
    private val message: String
) {
}