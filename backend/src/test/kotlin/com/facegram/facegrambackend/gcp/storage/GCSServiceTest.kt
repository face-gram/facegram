package com.facegram.facegrambackend.gcp.storage

import com.google.cloud.storage.Storage
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.mock.web.MockMultipartFile
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileInputStream

@SpringBootTest
class GCSServiceTest constructor(
    private val bucket:String,
    private val storage: Storage,
    private val gcsService: GCSService
) {


    @Test
    fun uploadFileToGCSTest() {
        // given
        val fileName = "testImageUpload"
        val contentType = "jpeg"
        val filePath = "src/test/resources/static/test-photo.jpeg"
        val image: MultipartFile = getMockMultipartFile(fileName,contentType,filePath)

        // when
        val imageUrl = gcsService.uploadFileToGCS(image)

        // then
//        Assertions.assertThat()
    }


    private fun getMockMultipartFile(fileName:String, contentType:String, path:String):MockMultipartFile{
        val fileInputStream = FileInputStream(File(path))
        return MockMultipartFile(fileName, "$fileName.$contentType",contentType,fileInputStream)
    }
}