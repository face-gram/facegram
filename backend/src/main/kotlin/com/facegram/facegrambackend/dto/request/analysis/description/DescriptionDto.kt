package com.facegram.facegrambackend.dto.request.analysis.description

import com.facegram.facegrambackend.domain.impression.Impression
import com.facegram.facegrambackend.dto.request.analysis.description.eyebrows.EyebrowsDto
import com.facegram.facegrambackend.dto.request.analysis.description.eyes.EyesDto
import com.facegram.facegrambackend.dto.request.analysis.description.face.FaceDto
import com.facegram.facegrambackend.dto.request.analysis.description.feature.FeatureDto
import com.facegram.facegrambackend.dto.request.analysis.description.hairstyle.HairstyleDto
import com.facegram.facegrambackend.dto.request.analysis.description.impression.ImpressionDto
import com.facegram.facegrambackend.dto.request.analysis.description.mouth.MouthDto
import com.facegram.facegrambackend.dto.request.analysis.description.neck.NeckDto
import com.facegram.facegrambackend.dto.request.analysis.description.nose.NoseDto
import com.facegram.facegrambackend.dto.request.analysis.description.wrinkle.WrinkleDto

data class DescriptionDto(
    val face:FaceDto,
    val hairstyle: HairstyleDto,
    val eyebrows: EyebrowsDto,
    val eyes: EyesDto,
    val nose: NoseDto,
    val mouth: MouthDto,
    val wrinkle: WrinkleDto,
    val feature: FeatureDto,
    val impression: ImpressionDto
) {

}