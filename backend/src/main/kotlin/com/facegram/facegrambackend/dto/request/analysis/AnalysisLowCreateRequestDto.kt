package com.facegram.facegrambackend.dto.request.analysis

import com.facegram.facegrambackend.dto.request.analysis.info.InfoDto
import com.facegram.facegrambackend.dto.request.analysis.description.DescriptionDto
import org.springframework.web.multipart.MultipartFile

data class AnalysisLowCreateRequestDto(
    val info: InfoDto,
    val description: DescriptionDto,
    val image: MultipartFile
) {
}