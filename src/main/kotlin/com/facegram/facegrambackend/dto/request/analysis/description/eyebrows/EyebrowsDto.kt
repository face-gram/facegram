package com.facegram.facegrambackend.dto.request.analysis.description.eyebrows

import javax.persistence.Column

data class EyebrowsDto(
    val type: String,
    val deep: String,
    val length: String? = null,
    val thick: String? = null,
    val glabella: String? = null,
) {
}