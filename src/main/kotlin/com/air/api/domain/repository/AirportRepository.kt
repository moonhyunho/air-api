package com.air.api.domain.repository

import com.air.api.domain.Airport
import org.springframework.data.jpa.repository.JpaRepository

interface AirportRepository: JpaRepository<Airport, Long> {
    fun findByCode(code: String): Airport?
}
