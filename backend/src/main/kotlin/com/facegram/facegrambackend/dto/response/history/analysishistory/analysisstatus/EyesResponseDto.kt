package com.facegram.facegrambackend.dto.response.history.analysishistory.analysisstatus

import javax.persistence.Column

data class EyesResponseDto
constructor(

    val type: String,

    val size: String,

    val distance: String?,

    val slant: String?,

    val shape: String?,

    val eyeLids: String?,

    val bottom: String?,
) {


}