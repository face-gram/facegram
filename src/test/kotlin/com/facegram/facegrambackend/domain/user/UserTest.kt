package com.facegram.facegrambackend.domain.user

import com.facegram.facegrambackend.security.oauth2.user.AuthProvider
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

@SpringBootTest
class UserTest constructor(
    private val userRepository: UserRepository
){

    @Test
    fun userInsert(){
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
        // when
        val user = userRepository.save(testUser)
        // then
        assertThat(user.id).isEqualTo(randomNumber)
        // end
        userRepository.delete(user)
    }
}