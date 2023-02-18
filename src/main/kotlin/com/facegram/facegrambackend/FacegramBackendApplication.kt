package com.facegram.facegrambackend

import com.facegram.facegrambackend.security.oauth2.jwt.JwtTokenProvider
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing


@EnableJpaAuditing
@SpringBootApplication
class FacegramBackendApplication

fun main(args: Array<String>) {
    runApplication<FacegramBackendApplication>(*args)
}
