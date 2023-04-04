package com.facegram.facegrambackend.dto.request.analysis.description.wrinkle

import javax.persistence.Column

data class WrinkleDto(
    val forehead: String?,
    val glabella: String?,
    val eyes: String?,
    val mouth: String?,
    val cheek: String?,
    val lip: String?,
    val neck: String?,
) {

}