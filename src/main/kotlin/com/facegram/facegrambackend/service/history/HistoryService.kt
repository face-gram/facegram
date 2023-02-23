package com.facegram.facegrambackend.service.history

import com.facegram.facegrambackend.domain.analyze.Analysis
import com.facegram.facegrambackend.domain.analyze.AnalysisRepository
import com.facegram.facegrambackend.domain.user.User
import com.facegram.facegrambackend.domain.user.UserRepository
import com.facegram.facegrambackend.dto.response.history.analysishistory.UserHistoryAnalysisDto
import com.facegram.facegrambackend.security.CustomUserDetails
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Optional
import kotlin.IllegalArgumentException

@Service
class HistoryService
    constructor(
        private val analysisRepository: AnalysisRepository,
        private val userRepository: UserRepository
    ) {

        @Transactional
        fun historySearchByUser(user: CustomUserDetails)
        : MutableList<UserHistoryAnalysisDto>{
            val history: MutableList<UserHistoryAnalysisDto> = mutableListOf()
            val findUser: Optional<User> = userRepository.findById(user.name.toLong())
            if(findUser.isEmpty){
                throw IllegalArgumentException("유저를 찾지 못했습니다.")
            }
            val userAnalysisHistory: List<Analysis> = analysisRepository.findAllByUser(findUser)
            userAnalysisHistory.forEach {
                    analysis ->
                history.add(
                    UserHistoryAnalysisDto(
                    analysis.id,
                    analysis.image,
                    analysis.createdAt)
                )
            }
            return history

        }
}