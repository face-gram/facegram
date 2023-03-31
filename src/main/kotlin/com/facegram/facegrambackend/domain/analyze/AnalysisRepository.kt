package com.facegram.facegrambackend.domain.analyze

import com.facegram.facegrambackend.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AnalysisRepository: JpaRepository<Analysis,Long>{
    fun findAllById(id: Long)


    fun findAllByUser(user:User): List<Analysis>

    fun deleteAnalysisById(id: Long)
}