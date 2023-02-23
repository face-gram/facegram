package com.facegram.facegrambackend.dto.response.history.analysishistory

import java.time.LocalDateTime

data class UserHistoryAnalysisDto(
    val id: Long?,
    val image: String?,
    val createdAt: LocalDateTime
){
}