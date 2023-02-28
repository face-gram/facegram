package com.facegram.facegrambackend.controller.face

import com.facegram.facegrambackend.dto.request.analysis.AnalysisLowCreateRequestDto
import com.facegram.facegrambackend.security.CustomUserDetails
import com.facegram.facegrambackend.service.analysis.AnalysisService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/face")
class AnalysisController(
    private val analysisService: AnalysisService
) {

    @PostMapping("/")
    fun createAnalysis(@RequestBody analysisLowCreateRequestDto: AnalysisLowCreateRequestDto,
                          @AuthenticationPrincipal user : CustomUserDetails,
                       request: HttpServletRequest,
                       response: HttpServletResponse
    ): ResponseEntity<Any> {
        return analysisService.createAnalysis(analysisLowCreateRequestDto,user,request,response)
    }
}