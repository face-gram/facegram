package com.facegram.facegrambackend.dto.response

enum class ResponseType(
    val statusCode: Int,
    val code: String
) {
    OK(200,"ok"),
}