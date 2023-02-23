package com.facegram.facegrambackend.controller.history

import com.facegram.facegrambackend.dto.response.history.UserHistoryResponseDto
import com.facegram.facegrambackend.dto.response.history.analysishistory.UserHistoryAnalysisDto
import com.facegram.facegrambackend.security.CustomUserDetails
import com.facegram.facegrambackend.service.history.HistoryService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping


@Service
@RequestMapping("/history")
class HistoryController constructor(
    private val historyService: HistoryService,
) {
    @GetMapping("/")
    fun historySearchByUser(@AuthenticationPrincipal user: CustomUserDetails): MutableList<UserHistoryAnalysisDto>  {
        return historyService.historySearchByUser(user)
    }


}