package com.facegram.facegrambackend.dto.response.history

import com.facegram.facegrambackend.domain.analyze.Analysis
import com.facegram.facegrambackend.dto.response.history.analysishistory.UserHistoryAnalysisDto
import java.time.LocalDateTime

data class UserHistoryResponseDto
constructor(
    val history: MutableList<UserHistoryAnalysisDto>
){
}