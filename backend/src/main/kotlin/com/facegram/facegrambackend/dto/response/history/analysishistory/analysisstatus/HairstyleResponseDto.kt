package com.facegram.facegrambackend.dto.response.history.analysishistory.analysisstatus

import javax.persistence.Column

data class HairstyleResponseDto
constructor(

    val type: String,

    val topLength: String,

    val sizeLength: String,

    val part: String?,
) {


}