package com.facegram.facegrambackend.domain.wrinkle

import lombok.extern.java.Log
import org.springframework.data.jpa.repository.JpaRepository

interface WrinkleRepository:JpaRepository<Wrinkle,Long> {
}