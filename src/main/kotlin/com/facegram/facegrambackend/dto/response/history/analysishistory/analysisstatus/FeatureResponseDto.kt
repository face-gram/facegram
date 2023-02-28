package com.facegram.facegrambackend.dto.response.history.analysishistory.analysisstatus

import javax.persistence.Column

data class FeatureResponseDto
constructor(

    val mustache: String,

    val sideburns: String,

    val dimple: String?,

    val scar: String?,

    val mole: String?,

    val freckles: String?,

    val spots: String?,

    val tattoo: String?,

    val makeup: String?,

    val description: String,
) {


}