package com.facegram.facegrambackend.service

import com.facegram.facegrambackend.domain.analyze.AnalysisRepository
import org.springframework.stereotype.Service


@Service
class AnalysisService constructor(
    private val analysisRepository: AnalysisRepository,

) {
}