package com.facegram.facegrambackend.domain.hairstyle

import org.springframework.data.jpa.repository.JpaRepository

interface HairstyleRepository: JpaRepository<Hairstyle,Long> {
}