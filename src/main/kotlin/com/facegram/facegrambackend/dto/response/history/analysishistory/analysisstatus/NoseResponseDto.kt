package com.facegram.facegrambackend.dto.response.history.analysishistory.analysisstatus

import javax.persistence.Column

data class NoseResponseDto
constructor(

    val size: String,

    val length: String,

    val heights: String?,

    val top: String?,

    val noseTrills: String?,

    val philtrum: String?,
) {


}