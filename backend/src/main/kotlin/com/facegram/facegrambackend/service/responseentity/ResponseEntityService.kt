package com.facegram.facegrambackend.service.responseentity

import com.facegram.facegrambackend.dto.response.Message
import com.facegram.facegrambackend.dto.response.ResponseType
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.nio.charset.Charset

@Service
class ResponseEntityService {


    fun createResponseEntity(message: Message,httpStatus: HttpStatus): ResponseEntity<Any>{

        val header = HttpHeaders()
        header.contentType = MediaType("application",
            "json",
            Charset.forName("UTF-8"))
        return ResponseEntity(message, header, httpStatus)
    }
}