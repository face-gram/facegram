package com.facegram.facegrambackend.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class CorsConfig constructor(

): WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("https://localhost:3000")
            .allowedOrigins("https://localhost:3000/")
            .allowedOriginPatterns("*")
            .allowedMethods("*")
            .allowCredentials(true)
            .exposedHeaders("Authorization")
            .maxAge(3600)
    }
}