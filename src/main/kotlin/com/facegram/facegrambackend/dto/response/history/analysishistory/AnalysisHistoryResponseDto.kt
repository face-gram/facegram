package com.facegram.facegrambackend.dto.response.history.analysishistory

import com.facegram.facegrambackend.domain.eyebrows.Eyebrows
import com.facegram.facegrambackend.domain.eyes.Eyes
import com.facegram.facegrambackend.domain.face.Face
import com.facegram.facegrambackend.domain.feature.Feature
import com.facegram.facegrambackend.domain.hairstyle.Hairstyle
import com.facegram.facegrambackend.domain.impression.Impression
import com.facegram.facegrambackend.domain.mouth.Mouth
import com.facegram.facegrambackend.domain.nose.Nose
import com.facegram.facegrambackend.domain.user.User
import com.facegram.facegrambackend.domain.wrinkle.Wrinkle
import javax.persistence.*

data class AnalysisHistoryResponseDto
constructor(


    val face: Face,

    val hairstyle: Hairstyle,

    val eyebrows: Eyebrows,

    val eyes: Eyes,

    val nose: Nose,

    val mouth: Mouth,

    val wrinkle: Wrinkle,

    val feature: Feature,

    val impression: Impression,


    val age: Int,

    val gender: String,
){
}