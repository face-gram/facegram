package com.facegram.facegrambackend.service.history

import com.facegram.facegrambackend.domain.analyze.Analysis
import com.facegram.facegrambackend.domain.analyze.AnalysisRepository
import com.facegram.facegrambackend.domain.user.User
import com.facegram.facegrambackend.domain.user.UserRepository
import com.facegram.facegrambackend.dto.response.Message
import com.facegram.facegrambackend.dto.response.ResponseType
import com.facegram.facegrambackend.dto.response.history.analysishistory.AnalysisHistoryResponseDto
import com.facegram.facegrambackend.dto.response.history.analysishistory.analysisstatus.*
import com.facegram.facegrambackend.dto.response.history.userhistory.UserHistoryAnalysisDto
import com.facegram.facegrambackend.security.CustomUserDetails
import com.facegram.facegrambackend.service.responseentity.ResponseEntityService
import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Optional
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.IllegalArgumentException

@Slf4j
@Service
class HistoryService
    constructor(
        private val analysisRepository: AnalysisRepository,
        private val userRepository: UserRepository,
        private val responseEntityService: ResponseEntityService
    ) {

        @Transactional
        fun historySearchByUser(userId: Long)
        : MutableList<UserHistoryAnalysisDto>{
            println("history 서치")
            val history: MutableList<UserHistoryAnalysisDto> = mutableListOf()
            val findUser: Optional<User> = userRepository.findById(userId)
            if(findUser.isEmpty){
                throw IllegalArgumentException("유저를 찾지 못했습니다.")
            }
            println("유저 서치")
            val userAnalysisHistory: List<Analysis> = analysisRepository.findAllByUser(findUser.get())
            println("history 서치")
            userAnalysisHistory.forEach {
                    analysis ->
                history.add(
                    UserHistoryAnalysisDto(
                    analysis.id,
                    analysis.image,
                    analysis.createdAt)
                )
            }
            println(history)
            println("히스토리 리턴")
            return history
        }

        @Transactional
        fun historySearchById(id: Long): AnalysisHistoryResponseDto{

            val findAnalysis:Optional<Analysis> = analysisRepository.findById(id)
            if (findAnalysis.isPresent){
                val face = FaceResponseDto(
                    findAnalysis.get().face.type,
                    findAnalysis.get().face.size,
                    findAnalysis.get().face.foreheadType,
                    findAnalysis.get().face.foreheadSize,
                    findAnalysis.get().face.chinType,
                    findAnalysis.get().face.chinSize,
                    findAnalysis.get().face.cheek,
                )
                val hairstyle = HairstyleResponseDto(
                    findAnalysis.get().hairstyle.type,
                    findAnalysis.get().hairstyle.topLength,
                    findAnalysis.get().hairstyle.sizeLength,
                    findAnalysis.get().hairstyle.part
                )
                val eyebrows = EyebrowsResponseDto(
                    findAnalysis.get().eyebrows.type,
                    findAnalysis.get().eyebrows.deep,
                    findAnalysis.get().eyebrows.length,
                    findAnalysis.get().eyebrows.thick,
                    findAnalysis.get().eyebrows.glabella,
                )
                val eyes = EyesResponseDto(
                    findAnalysis.get().eyes.type,
                    findAnalysis.get().eyes.size,
                    findAnalysis.get().eyes.distance,
                    findAnalysis.get().eyes.slant,
                    findAnalysis.get().eyes.shape,
                    findAnalysis.get().eyes.eyeLids,
                    findAnalysis.get().eyes.bottom,
                )
                val nose = NoseResponseDto(
                    findAnalysis.get().nose.size,
                    findAnalysis.get().nose.length,
                    findAnalysis.get().nose.heights,
                    findAnalysis.get().nose.top,
                    findAnalysis.get().nose.noseTrills,
                    findAnalysis.get().nose.philtrum,
                )
                val mouth = MouthResponseDto(
                    findAnalysis.get().mouth.type,
                    findAnalysis.get().mouth.size,
                    findAnalysis.get().mouth.thick,
                    findAnalysis.get().mouth.ratio,
                    findAnalysis.get().mouth.side,
                    findAnalysis.get().mouth.line,
                )
                val wrinkle = WrinkleResponseDto(
                    findAnalysis.get().wrinkle.forehead,
                    findAnalysis.get().wrinkle.glabella,
                    findAnalysis.get().wrinkle.eyes,
                    findAnalysis.get().wrinkle.mouth,
                    findAnalysis.get().wrinkle.cheek,
                    findAnalysis.get().wrinkle.lip,
                )
                val feature = FeatureResponseDto(
                    findAnalysis.get().feature.mustache,
                    findAnalysis.get().feature.sideburns,
                    findAnalysis.get().feature.dimple,
                    findAnalysis.get().feature.scar,
                    findAnalysis.get().feature.mole,
                    findAnalysis.get().feature.freckles,
                    findAnalysis.get().feature.spots,
                    findAnalysis.get().feature.tattoo,
                    findAnalysis.get().feature.makeup,
                    findAnalysis.get().feature.description,
                )
                val impression = ImpressionResponseDto(
                    findAnalysis.get().impression.type
                )

                return AnalysisHistoryResponseDto(
                    face,
                    hairstyle,
                    eyebrows,
                    eyes,
                    nose,
                    mouth,
                    wrinkle,
                    feature,
                    impression,
                    findAnalysis.get().age,
                    findAnalysis.get().gender
                )

            }
            throw IllegalArgumentException("존재하지 않는 분석입니다.")
        }

        @Transactional
        fun historyDeleteById(id: Long, request: HttpServletRequest, response: HttpServletResponse): ResponseEntity<Any> {

            analysisRepository.deleteAnalysisById(id)

            val message = Message(ResponseType.OK,"성공입니다.")
            return responseEntityService.createResponseEntity(message, HttpStatus.OK)
        }
}