package com.facegram.facegrambackend.service.history

import com.facegram.facegrambackend.domain.analyze.Analysis
import com.facegram.facegrambackend.domain.analyze.AnalysisRepository
import com.facegram.facegrambackend.domain.user.User
import com.facegram.facegrambackend.domain.user.UserRepository
import com.facegram.facegrambackend.dto.response.Message
import com.facegram.facegrambackend.dto.response.ResponseType
import com.facegram.facegrambackend.dto.response.history.analysishistory.AnalysisHistoryResponseDto
import com.facegram.facegrambackend.dto.response.history.userhistory.UserHistoryAnalysisDto
import com.facegram.facegrambackend.security.CustomUserDetails
import com.facegram.facegrambackend.service.responseentity.ResponseEntityService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Optional
import kotlin.IllegalArgumentException

@Service
class HistoryService
    constructor(
        private val analysisRepository: AnalysisRepository,
        private val userRepository: UserRepository,
        private val responseEntityService: ResponseEntityService
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

        @Transactional
        fun historySearchById(id: Long): AnalysisHistoryResponseDto{

            val findAnalysis:Optional<Analysis> = analysisRepository.findById(id)
            if (findAnalysis.isPresent){
                return AnalysisHistoryResponseDto(
                    findAnalysis.get().face,
                    findAnalysis.get().hairstyle,
                    findAnalysis.get().eyebrows,
                    findAnalysis.get().eyes,
                    findAnalysis.get().nose,
                    findAnalysis.get().mouth,
                    findAnalysis.get().wrinkle,
                    findAnalysis.get().feature,
                    findAnalysis.get().impression,
                    findAnalysis.get().age,
                    findAnalysis.get().gender
                )
            }
            throw IllegalArgumentException("존재하지 않는 분석입니다.")
        }

        @Transactional
        fun historyDeleteById(id: Long): ResponseEntity<Any> {

            analysisRepository.deleteAnalysisById(id)

            val message = Message(ResponseType.OK,"성공입니다.")
            return responseEntityService.createResponseEntity(message, HttpStatus.OK)
        }
}