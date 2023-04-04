package com.facegram.facegrambackend.gcp.storage

import com.facegram.facegrambackend.dto.request.gcs.GCSUploadRequestDto
import com.google.cloud.storage.BlobInfo
import com.google.cloud.storage.Storage
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*


@Service
class GCSService constructor(
    @Value("\${app.gcp.storageName}")
    private val bucketName: String,
    private val storage: Storage
) {

    fun uploadFileToGCS(image: MultipartFile): String{
        val uuid: String = UUID.randomUUID().toString() // Google Cloud Storage에 저장될 파일 이름
        val ext = image.contentType // 파일의 형식, 확장자

        // Cloud에 이미지 업로드
//        val blobInfo:BlobInfo =
                storage.create(
            BlobInfo.newBuilder(bucketName, uuid)
                .setContentType(ext)
                .build(),
            image.inputStream
        )
        return "https://storage.googleapis.com/${bucketName}/${uuid}"//.${ext}"
    }
    //클라가 저장했던 이미지를 다시 보내 달라할 땐 사실 굉장히 간단하고 쉽습니다!
    //저희가 DB에 String 타입으로 저장해둔 UUID값을 보내주기만 하면 된답니당 ㅎㅎ
    //더 정확히는,  String 타입으로 https://storage.googleapis.com/버킷이름/UUID값 을 보내주심 됩니다!
    //해당 URL을 들어가면 이전에 storage에 저장해둔 이미지가 뜨니깐요!

}