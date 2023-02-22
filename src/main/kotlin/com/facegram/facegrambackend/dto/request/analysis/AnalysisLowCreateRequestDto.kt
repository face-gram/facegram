package com.facegram.facegrambackend.dto.request.analysis

import com.facegram.facegrambackend.dto.request.analysis.info.InfoDto
import com.facegram.facegrambackend.dto.request.analysis.description.DescriptionDto

data class AnalysisLowCreateRequestDto(
    val info: InfoDto,
    val description: DescriptionDto
) {
}