package com.facegram.facegrambackend.dto.request.analysis.description.nose

import javax.persistence.Column

data class NoseDto(
    val size: String,
    val length: String,
    val heights: String? = null,
    val top: String? = null,
    val noseTrills: String?= null,
    val philtrum: String? = null,
) {
}