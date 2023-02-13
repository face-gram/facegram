package com.facegram.facegrambackend.domain.analyze

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AnalysisRepository: JpaRepository<Analysis,Long>{
    fun findAllById(id: Long){
    }
}