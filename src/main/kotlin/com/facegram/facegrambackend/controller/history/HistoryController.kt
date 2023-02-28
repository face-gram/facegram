package com.facegram.facegrambackend.controller.history

import com.facegram.facegrambackend.dto.response.history.analysishistory.AnalysisHistoryResponseDto
import com.facegram.facegrambackend.dto.response.history.userhistory.UserHistoryAnalysisDto
import com.facegram.facegrambackend.security.CustomUserDetails
import com.facegram.facegrambackend.service.history.HistoryService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*


@Service
@RequestMapping("/history")
class HistoryController constructor(
    private val historyService: HistoryService,
) {
    @GetMapping("/")
    fun historySearchByUser(@AuthenticationPrincipal user: CustomUserDetails): MutableList<UserHistoryAnalysisDto>  {
        return historyService.historySearchByUser(user)
    }

    @DeleteMapping("/{id}")
    fun historyDeleteById(@PathVariable id: Long): ResponseEntity<Any> {
        return historyService.historyDeleteById(id)
    }

    @GetMapping("/{id}")
    fun historySearchById(@PathVariable id: Long): AnalysisHistoryResponseDto{
        return historyService.historySearchById(id)
    }


}