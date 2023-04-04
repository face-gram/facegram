package com.facegram.facegrambackend.dto.request.gcs

import org.springframework.web.multipart.MultipartFile

data class GCSUploadRequestDto
constructor(
    val image: MultipartFile,
    val imageName: String
){
}