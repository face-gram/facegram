package com.facegram.facegrambackend.dto.response.history.analysishistory.analysisstatus

import javax.persistence.Column

data class WrinkleResponseDto
constructor(

    val forehead: String?,

    val glabella: String?,

    val eyes: String?,

    val mouth: String?,

    val cheek: String?,

    val lip: String?,
) {


}