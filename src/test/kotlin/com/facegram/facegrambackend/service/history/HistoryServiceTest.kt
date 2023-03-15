package com.facegram.facegrambackend.service.history

import com.facegram.facegrambackend.domain.analyze.AnalysisRepository
import com.facegram.facegrambackend.domain.user.User
import com.facegram.facegrambackend.domain.user.UserRepository
import com.facegram.facegrambackend.domain.user.UserRole
import com.facegram.facegrambackend.dto.response.history.analysishistory.AnalysisHistoryResponseDto
import com.facegram.facegrambackend.security.oauth2.user.AuthProvider
import com.facegram.facegrambackend.service.responseentity.ResponseEntityService
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
class HistoryServiceTest constructor(
    private val analysisRepository: AnalysisRepository,
    private val userRepository: UserRepository,
    private val responseEntityService: ResponseEntityService,
    private val historyService: HistoryService

){

    @Test
    fun historySearchByUser() {
        // given
        val random = Random()
        val randomNumber = random.nextInt(10000,20000).toLong() // generates a random integer between 0 and 100
        val testUser = User.newInstance(
            "testUser",
            randomNumber,
        "testUser@gmail.com",
        "testUserImage",
            UserRole.USER,
            null,
            AuthProvider.GOOGLE,
            )
        userRepository.save(testUser)
        // when
        val historySearchByUser = historyService.historySearchByUser(randomNumber)

        // then
        assertThat(historySearchByUser.size).isEqualTo(0)

        //end
        val findUser = userRepository.findById(randomNumber)
        if (findUser.isPresent){
            throw IllegalArgumentException("테스트 에러")
        }
        userRepository.delete(findUser.get())
    }

    @Test
    fun historySearchById() {

    }

    @Test
    fun historyDeleteById() {
    }
}