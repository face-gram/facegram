package com.facegram.facegrambackend.dto.request.analysis.description.eyes

import javax.persistence.Column

data class EyesDto(
    val size: String,
    val type: String,
    val distance: String? = null,
    val slent: String? = null,
    val shape: String? = null,
    val eyeLids: String? = null,
    val bottom: String? = null,

) {
}