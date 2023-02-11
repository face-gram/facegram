package com.facegram.facegrambackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing


@EnableJpaAuditing
@SpringBootApplication
class FacegramBackendApplication

fun main(args: Array<String>) {
    runApplication<FacegramBackendApplication>(*args)
}
