package com.facegram.facegrambackend.dto.request.analysis.description.face

data class FaceDto(
    val type: String,
    val size: String,
    val forehead: String,
    val foreheadSize: String? = null,
    val chinType: String? = null,
    val chinSize: String? = null,
    val cheek: String? = null,

) {
}