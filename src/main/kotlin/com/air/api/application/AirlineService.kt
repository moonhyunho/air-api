package com.air.api.application

import com.air.api.domain.Airline
import com.air.api.domain.repository.AirlineRepository
import com.air.api.support.MessageKey
import com.air.api.support.exception.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AirlineService(
    private val airlineRepository: AirlineRepository
) {

    fun findAll(): List<Airline> {
        return airlineRepository.findAll()
    }

    fun getAirline(id: Long): Airline {
        return airlineRepository.findByIdOrNull(id) ?: throw EntityNotFoundException(MessageKey.NOT_FOUND_AIRLINE)
    }

    fun getByCode(code: String): Airline {
        return airlineRepository.findByCode(code) ?: throw EntityNotFoundException(MessageKey.NOT_FOUND_AIRLINE)
    }

    @Transactional
    fun create(airline: Airline): Airline {
        return airlineRepository.save(airline)
    }

    @Transactional
    fun createAll(airlines: List<Airline>): List<Airline> {
        return airlineRepository.saveAll(airlines)
    }

    @Transactional
    fun modify(id: Long, code: String?, name: String?) {
        return getAirline(id).modify(code, name)
    }

    @Transactional
    fun delete(airline: Airline) {
        airlineRepository.delete(airline)
    }
}