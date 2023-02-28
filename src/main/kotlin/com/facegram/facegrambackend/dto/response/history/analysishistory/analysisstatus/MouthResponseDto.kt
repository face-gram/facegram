package com.facegram.facegrambackend.dto.response.history.analysishistory.analysisstatus

import javax.persistence.Column

data class MouthResponseDto
constructor(

    val type: String,

    val size: String,

    val thick: String?,

    val ratio: String?,

    val side: String?,

    val line: String?,
) {


}