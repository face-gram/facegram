package com.facegram.facegrambackend.dto.request.analysis.description.feature

data class FeatureDto(
    val mustache: String,
    val sideburns: String,
    val dimple: String?,
    val scar: String?,
    val mole: String?,
    val freckles: String?,
    val spots: String?,
    val tattoo: String?,
    val makeup: String?,
    val description: String
) {
}