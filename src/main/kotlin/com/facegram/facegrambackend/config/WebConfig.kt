package com.facegram.facegrambackend.config

import com.facegram.facegrambackend.authentication.auth.AuthInterceptor
import com.facegram.facegrambackend.authentication.auth.UserIdResolver
import lombok.RequiredArgsConstructor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class WebConfig constructor(
    private  val authInterceptor: AuthInterceptor,

    private val userIdResolver: UserIdResolver
): WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(authInterceptor)
    }

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