package com.facegram.facegrambackend.dto.request.analysis.description.mouth

import javax.persistence.Column

data class MouthDto(
    val type: String,
    val size: String,
    val thick: String?,
    val ratio: String?,
    val side: String?,
    val line: String?,
) {
}