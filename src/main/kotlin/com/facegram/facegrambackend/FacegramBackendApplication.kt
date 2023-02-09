package com.facegram.facegrambackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FacegramBackendApplication

fun main(args: Array<String>) {
    runApplication<FacegramBackendApplication>(*args)
}
