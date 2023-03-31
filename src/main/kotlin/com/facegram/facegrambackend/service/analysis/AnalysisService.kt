package com.facegram.facegrambackend.service.analysis

import com.facegram.facegrambackend.domain.analyze.Analysis
import com.facegram.facegrambackend.domain.analyze.AnalysisRepository
import com.facegram.facegrambackend.domain.eyebrows.Eyebrows
import com.facegram.facegrambackend.domain.eyebrows.EyebrowsRepository
import com.facegram.facegrambackend.domain.eyes.Eyes
import com.facegram.facegrambackend.domain.eyes.EyesRepository
import com.facegram.facegrambackend.domain.face.Face
import com.facegram.facegrambackend.domain.face.FaceRepository
import com.facegram.facegrambackend.domain.feature.Feature
import com.facegram.facegrambackend.domain.feature.FeatureRepository
import com.facegram.facegrambackend.domain.hairstyle.Hairstyle
import com.facegram.facegrambackend.domain.hairstyle.HairstyleRepository
import com.facegram.facegrambackend.domain.impression.Impression
import com.facegram.facegrambackend.domain.impression.ImpressionRepository
import com.facegram.facegrambackend.domain.mouth.Mouth
import com.facegram.facegrambackend.domain.mouth.MouthRepository
import com.facegram.facegrambackend.domain.nose.Nose
import com.facegram.facegrambackend.domain.nose.NoseRepository
import com.facegram.facegrambackend.domain.user.UserRepository
import com.facegram.facegrambackend.domain.wrinkle.Wrinkle
import com.facegram.facegrambackend.domain.wrinkle.WrinkleRepository
import com.facegram.facegrambackend.dto.request.analysis.AnalysisLowCreateRequestDto
import com.facegram.facegrambackend.dto.request.analysis.description.DescriptionDto
import com.facegram.facegrambackend.dto.request.analysis.info.InfoDto
import com.facegram.facegrambackend.dto.response.Message
import com.facegram.facegrambackend.dto.response.ResponseType
import com.facegram.facegrambackend.gcp.storage.GCSService
import com.facegram.facegrambackend.security.CustomUserDetails
import com.facegram.facegrambackend.service.auth.AuthService
import com.facegram.facegrambackend.service.responseentity.ResponseEntityService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.lang.IllegalArgumentException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Service
class AnalysisService constructor(
    private val userRepository: UserRepository,
    private val analysisRepository: AnalysisRepository,
    private val faceRepository: FaceRepository,
    private val hairstyleRepository: HairstyleRepository,
    private val eyebrowsRepository: EyebrowsRepository,
    private val eyesRepository: EyesRepository,
    private val noseRepository: NoseRepository,
    private val mouthRepository: MouthRepository,
    private val wrinkleRepository: WrinkleRepository,
    private val featureRepository: FeatureRepository,
    private val impressionRepository: ImpressionRepository,
    private val responseEntityService: ResponseEntityService,
    private val gcsService: GCSService,
    private val authService: AuthService

) {
    companion object{
        private var analysisNum = 0;

        fun numIncrease(){
            analysisNum++
        }
    }

    @Transactional
    fun createAnalysis(
            analysisLowCreateRequestDto: AnalysisLowCreateRequestDto,
//            image: MultipartFile,
//            info: InfoDto,
//            image: MultipartFile,
            user: CustomUserDetails,
            request: HttpServletRequest,
            response: HttpServletResponse
    )
    :ResponseEntity<Any>{
        val face = faceRepository.save(createFace(analysisLowCreateRequestDto))
        val hairstyle = hairstyleRepository.save(createHairstyle(analysisLowCreateRequestDto))
        val eyebrows = eyebrowsRepository.save(createEyebrows(analysisLowCreateRequestDto))
        val eye = eyesRepository.save(createEyes(analysisLowCreateRequestDto))
        val nose = noseRepository.save(createNose(analysisLowCreateRequestDto))
        val mouth = mouthRepository.save(createMouth(analysisLowCreateRequestDto))
        val wrinkle = wrinkleRepository.save(createWrinkle(analysisLowCreateRequestDto))
//        val characteristic =
        val feature = featureRepository.save(createFeature(analysisLowCreateRequestDto))
        val impression = impressionRepository.save(createImpression(analysisLowCreateRequestDto))
        println("다른 것들 생성 완료")
        val findUser = userRepository.findById(user.name.toLong())
        if(findUser.isEmpty){
            throw IllegalArgumentException("유저를 찾을 수 없습니다.")
        }
        println("유저 생성 완료")
        val imageUrl = gcsService.uploadFileToGCS(analysisLowCreateRequestDto.image)

        numIncrease()

        val analysis = Analysis.newInstance(
            null,
            "${user.username}님이 생성한 몽타주 $analysisNum",
            findUser.get(),
            face,
            hairstyle,
            eyebrows,
            eye,
            nose,
            mouth,
            wrinkle,
            feature,
            impression,
            "생성된 몽타주입니다.",
            analysisLowCreateRequestDto
                .info.age.toInt(),
            analysisLowCreateRequestDto
                .info.gender,
            imageUrl
        )

        analysisRepository.save(analysis)

        val message = Message(ResponseType.OK,"성공입니다.")

        return responseEntityService.createResponseEntity(message,HttpStatus.OK)
    }

    private fun createFace(analysisLowCreateRequestDto:
                           AnalysisLowCreateRequestDto): Face{
        return Face.newInstance(
            null,
            analysisLowCreateRequestDto
                .description
                .face
                .type,
            analysisLowCreateRequestDto
                .description
                .face
                .size,
            analysisLowCreateRequestDto
                .description
                .face
                .foreheadType,
            analysisLowCreateRequestDto
                .description
                .face
                .foreheadSize,
            analysisLowCreateRequestDto
                .description
                .face
                .chinType,
            analysisLowCreateRequestDto
                .description
                .face
                .chinSize,
            analysisLowCreateRequestDto
                .description
                .face
                .cheek,
        )
    }
    private fun createHairstyle(analysisLowCreateRequestDto:
                           AnalysisLowCreateRequestDto): Hairstyle{
        return Hairstyle.newInstance(
            null,
            analysisLowCreateRequestDto
                .description
                .hairstyle
                .type,
            analysisLowCreateRequestDto
                .description
                .hairstyle
                .topLength,
            analysisLowCreateRequestDto
                .description
                .hairstyle
                .sizeLength,
            analysisLowCreateRequestDto
                .description
                .hairstyle
                .part,
        )
    }
    private fun createEyebrows(analysisLowCreateRequestDto:
                           AnalysisLowCreateRequestDto): Eyebrows{
        return Eyebrows.newInstance(
            null,
            analysisLowCreateRequestDto
                .description
                .eyebrows
                .type,
            analysisLowCreateRequestDto
                .description
                .eyebrows
                .deep,
            analysisLowCreateRequestDto
                .description
                .eyebrows
                .length,
            analysisLowCreateRequestDto
                .description
                .eyebrows
                .thick,
            analysisLowCreateRequestDto
                .description
                .eyebrows
                .glabella,
        )
    }

    private fun createEyes(analysisLowCreateRequestDto:
                               AnalysisLowCreateRequestDto): Eyes{
        return Eyes.newInstance(
            null,
            analysisLowCreateRequestDto
                .description
                .eyes
                .size,
            analysisLowCreateRequestDto
                .description
                .eyes
                .type,
            analysisLowCreateRequestDto
                .description
                .eyes
                .distance,
            analysisLowCreateRequestDto
                .description
                .eyes
                .slant,
            analysisLowCreateRequestDto
                .description
                .eyes
                .shape,
            analysisLowCreateRequestDto
                .description
                .eyes
                .eyeLids,
            analysisLowCreateRequestDto
                .description
                .eyes
                .bottom,
        )
    }
    private fun createNose(analysisLowCreateRequestDto:
                           AnalysisLowCreateRequestDto): Nose{
        return Nose.newInstance(
            null,
            analysisLowCreateRequestDto
                .description
                .nose
                .size,
            analysisLowCreateRequestDto
                .description
                .nose
                .length,
            analysisLowCreateRequestDto
                .description
                .nose
                .heights,
            analysisLowCreateRequestDto
                .description
                .nose
                .top,
            analysisLowCreateRequestDto
                .description
                .nose
                .noseTrills,
            analysisLowCreateRequestDto
                .description
                .nose
                .philtrum,
        )
    }
    private fun createMouth(analysisLowCreateRequestDto:
                           AnalysisLowCreateRequestDto): Mouth{
        return Mouth.newInstance(
            null,
            analysisLowCreateRequestDto
                .description
                .mouth
                .type,
            analysisLowCreateRequestDto
                .description
                .mouth
                .size,
            analysisLowCreateRequestDto
                .description
                .mouth
                .thick,
            analysisLowCreateRequestDto
                .description
                .mouth
                .ratio,
            analysisLowCreateRequestDto
                .description
                .mouth
                .side,
            analysisLowCreateRequestDto
                .description
                .mouth
                .line,
        )
    }

    private fun createWrinkle(analysisLowCreateRequestDto:
                            AnalysisLowCreateRequestDto): Wrinkle{
        return Wrinkle.newInstance(
            null,
            analysisLowCreateRequestDto
                .description
                .wrinkle
                .forehead,
            analysisLowCreateRequestDto
                .description
                .wrinkle
                .glabella,
            analysisLowCreateRequestDto
                .description
                .wrinkle
                .eyes,
            analysisLowCreateRequestDto
                .description
                .wrinkle
                .mouth,
            analysisLowCreateRequestDto
                .description
                .wrinkle
                .cheek,
            analysisLowCreateRequestDto
                .description
                .wrinkle
                .lip,
        )
    }

    private fun createFeature(analysisLowCreateRequestDto:
                              AnalysisLowCreateRequestDto): Feature {
        return Feature.newInstance(
            null,
            analysisLowCreateRequestDto
                .description
                .feature
                .mustache,
            analysisLowCreateRequestDto
                .description
                .feature
                .sideburns,
            analysisLowCreateRequestDto
                .description
                .feature
                .dimple,
            analysisLowCreateRequestDto
                .description
                .feature
                .scar,
            analysisLowCreateRequestDto
                .description
                .feature
                .mole,
            analysisLowCreateRequestDto
                .description
                .feature
                .freckles,
            analysisLowCreateRequestDto
                .description
                .feature
                .spots,
            analysisLowCreateRequestDto
                .description
                .feature
                .tattoo,
            analysisLowCreateRequestDto
                .description
                .feature
                .makeup,
            analysisLowCreateRequestDto
                .description
                .feature
                .description,
        )
    }
    private fun createImpression(analysisLowCreateRequestDto:
                            AnalysisLowCreateRequestDto): Impression {
        return Impression.newInstance(
            null,
            analysisLowCreateRequestDto
                .description
                .impression
                .type,
        )
    }
//    private fun createCharacteristic(analysisLowCreateRequestDto:
//                                 AnalysisLowCreateRequestDto): Characteristic {
//        return Characteristic.newInstance(
//            null,
//            analysisLowCreateRequestDto
//                .description
//                .impression
//                .type,
//        )
//    }

}