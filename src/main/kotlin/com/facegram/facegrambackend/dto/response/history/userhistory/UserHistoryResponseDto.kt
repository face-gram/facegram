package com.facegram.facegrambackend.dto.response.history.userhistory

import com.facegram.facegrambackend.dto.response.history.userhistory.UserHistoryAnalysisDto

data class UserHistoryResponseDto
constructor(
    val history: MutableList<UserHistoryAnalysisDto>
){
}