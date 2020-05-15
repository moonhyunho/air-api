package com.air.api.application

import com.air.api.domain.Airline
import com.air.api.domain.repository.AirlineRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class AirlineService(
    private val airlineRepository: AirlineRepository
) {

    fun findAll(): List<Airline> {
        return airlineRepository.findAll()
    }

    fun getById(id: Long): Airline {
        return airlineRepository.findByIdOrNull(id) ?: throw Exception()
    }

    fun getByCode(code: String): Airline {
        return airlineRepository.findByCode(code)
    }

    fun save(airline: Airline): Airline {
        return airlineRepository.save(airline)
    }

    fun update(airline: Airline): Airline {
        return airlineRepository.save(airline)
    }

    fun delete(airline: Airline) {
        airlineRepository.delete(airline)
    }
}