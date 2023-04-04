package com.facegram.facegrambackend.dto.response.history.userhistory

import java.time.LocalDateTime

data class UserHistoryAnalysisDto(
    val id: Long?,
    val image: String?,
    val createdAt: LocalDateTime
){
}