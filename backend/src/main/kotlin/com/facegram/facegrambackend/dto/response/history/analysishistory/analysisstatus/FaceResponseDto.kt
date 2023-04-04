package com.facegram.facegrambackend.dto.response.history.analysishistory.analysisstatus

import javax.persistence.Column

data class FaceResponseDto
constructor(
    val type: String,

    val size: String,

    val foreheadType: String,

    val foreheadSize: String?,

    val chinType: String?,

    val chinSize: String?,

    val cheek: String?,
) {


}