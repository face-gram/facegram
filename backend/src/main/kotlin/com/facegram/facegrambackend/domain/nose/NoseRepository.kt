package com.facegram.facegrambackend.domain.nose

import org.springframework.data.jpa.repository.JpaRepository

interface NoseRepository: JpaRepository<Nose,Long> {
}