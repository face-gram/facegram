package com.facegram.facegrambackend.controller.face

import com.facegram.facegrambackend.dto.response.Message
import com.facegram.facegrambackend.dto.response.ResponseType
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.nio.charset.Charset

@RestController
@RequestMapping("/face")
class AnalysisController {

    @PostMapping("/low")
    fun createFaceLow(@RequestBody ): ResponseEntity<Any> {

        val message = Message(ResponseType.OK,"성공입니다.")

        val header = HttpHeaders()
        header.contentType = MediaType("application",
            "json",
            Charset.forName("UTF-8"))

        return ResponseEntity(message,header,HttpStatus.OK)
    }
}