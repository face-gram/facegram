package com.facegram.facegrambackend.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.Optional

@Repository
interface UserRepository: JpaRepository<User,Long> {

    override fun findById(id:Long): Optional<User>

    fun findByEmail(email:String): User?

    fun findByUsername(username:String):User?

    @Query("select u.refreshToken FROM User u WHERE u.id = :id")
    fun getRefreshTokenById():String


    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.refreshToken=:token WHERE u.id=:id")
    fun updateRefreshToken(@Param("id")id:Long, @Param("token") token:String)
}