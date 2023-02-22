package com.facegram.facegrambackend.domain.feature

import org.springframework.data.jpa.repository.JpaRepository

interface FeatureRepository: JpaRepository<Feature,Long> {
}