package com.facegram.facegrambackend.dto.response.history.analysishistory.analysisstatus

import javax.persistence.Column

data class EyebrowsResponseDto
constructor(

    val type: String,

    val deep: String,

    val length: String?,

    val thick: String?,

    val glabella: String?,
) {


}