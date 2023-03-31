package com.facegram.facegrambackend.controller.history

import com.facegram.facegrambackend.dto.response.history.analysishistory.AnalysisHistoryResponseDto
import com.facegram.facegrambackend.dto.response.history.userhistory.UserHistoryAnalysisDto
import com.facegram.facegrambackend.security.CustomUserDetails
import com.facegram.facegrambackend.service.history.HistoryService
import lombok.extern.slf4j.Slf4j
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.math.log

@Slf4j
@RestController
@RequestMapping("/history")
class HistoryController constructor(
    private val historyService: HistoryService,
) {
    @GetMapping("/")
    fun historySearchByUser(@AuthenticationPrincipal user: CustomUserDetails,
                            request: HttpServletRequest,
                            response: HttpServletResponse
    ): MutableList<UserHistoryAnalysisDto>  {
        println("컨트롤러 도착")
        val userId: Long = user.name.toLong()
        val historySearchByUser = historyService.historySearchByUser(userId)
        println("history 배열 가져옴")
        return historySearchByUser
    }

    @DeleteMapping("/{id}")
    fun historyDeleteById(@PathVariable id: Long,
                          request: HttpServletRequest,
                          response: HttpServletResponse): ResponseEntity<Any> {
        return historyService.historyDeleteById(id,request,response)
    }

    @GetMapping("/{id}")
    fun historySearchById(@PathVariable id: Long): AnalysisHistoryResponseDto{
        return historyService.historySearchById(id)
    }


}