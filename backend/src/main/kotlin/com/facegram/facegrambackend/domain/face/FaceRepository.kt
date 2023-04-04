package com.facegram.facegrambackend.domain.face

import org.springframework.data.jpa.repository.JpaRepository

interface FaceRepository: JpaRepository<Face,Long> {
}