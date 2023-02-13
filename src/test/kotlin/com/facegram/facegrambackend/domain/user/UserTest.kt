package com.facegram.facegrambackend.domain.user

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.jpa.repository.JpaRepository

@SpringBootTest
class UserTest constructor(
    @Autowired
    private val userRepository: UserRepository
){

    @Test
    fun userInsert(){
        //given
        val user1  = User.newInstance("seyeol")
        //when
        val user = userRepository.save(user1)
        //then
        assertThat(user.id).isEqualTo(6L)
    }
}