package com.facegram.facegrambackend.domain.impression

import org.springframework.data.jpa.repository.JpaRepository

interface ImpressionRepository: JpaRepository<Impression,Long> {
}