package com.facegram.facegrambackend.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepository: JpaRepository<User,Long> {


    fun findByEmail(email:String): User?

    fun
}