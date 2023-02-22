package com.facegram.facegrambackend.controller.face

import com.facegram.facegrambackend.dto.request.analysis.AnalysisLowCreateRequestDto
import com.facegram.facegrambackend.service.analysis.AnalysisService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/face")
class AnalysisController(
    private val analysisService: AnalysisService
) {

    @PostMapping("/low")
    fun createAnalysisLow(@RequestBody analysisLowCreateRequestDto: AnalysisLowCreateRequestDto ): ResponseEntity<Any> {
        return analysisService.createAnalysisLow(analysisLowCreateRequestDto)
    }
}