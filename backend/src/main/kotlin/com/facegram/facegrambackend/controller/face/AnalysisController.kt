package com.facegram.facegrambackend.controller.face

import com.facegram.facegrambackend.dto.request.analysis.AnalysisLowCreateRequestDto
import com.facegram.facegrambackend.dto.request.analysis.description.DescriptionDto
import com.facegram.facegrambackend.dto.request.analysis.info.InfoDto
import com.facegram.facegrambackend.security.CustomUserDetails
import com.facegram.facegrambackend.service.analysis.AnalysisService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/face")
class AnalysisController(
    private val analysisService: AnalysisService
) {

    @PostMapping("/", consumes = ["multipart/form-data"])
    fun createAnalysis(
            @RequestPart info: InfoDto,
            @RequestPart description: DescriptionDto,
            @RequestPart image: MultipartFile,
            @AuthenticationPrincipal user : CustomUserDetails,
            request: HttpServletRequest,
            response: HttpServletResponse
    ): ResponseEntity<Any> {
        val analysisRequest = AnalysisLowCreateRequestDto(info,description,image)
        return analysisService.createAnalysis(analysisRequest,user,request,response)
    }
}