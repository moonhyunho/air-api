package com.air.api.domain.repository

import com.air.api.domain.Airline
import org.springframework.data.jpa.repository.JpaRepository

interface AirlineRepository: JpaRepository<Airline, Long> {
    fun findByCode(code: String): Airline?
}
