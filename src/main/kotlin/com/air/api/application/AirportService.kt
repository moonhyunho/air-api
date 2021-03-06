package com.air.api.application

import com.air.api.domain.Airport
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import com.air.api.domain.repository.AirportRepository
import com.air.api.support.MessageKey
import com.air.api.support.exception.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull

@Service
class AirportService (
        private val airportRepository: AirportRepository
) {

    fun getAirport(id: Long): Airport {
        return airportRepository.findByIdOrNull(id) ?: throw EntityNotFoundException(MessageKey.NOT_FOUND_AIRPORT)
    }

    @Transactional
    fun create(airport: Airport): Airport {
        return airportRepository.save(airport)

    }

    @Transactional
    fun modify(id: Long, code: String?, name: String?, nt_code: String?)  {
        return getAirport(id).modify(code, name, nt_code)
    }

    @Transactional
    fun delete(id: Long)  {
        airportRepository.deleteById(id)
    }

    fun getByCode(code: String) : Airport {
        return airportRepository.findByCode(code) ?: throw EntityNotFoundException(MessageKey.NOT_FOUND_AIRPORT)
    }

    fun findAll() : List<Airport> {
        return airportRepository.findAll()
    }

    @Transactional
    fun createAll(airports: List<Airport>) : List<Airport> {
        return airportRepository.saveAll(airports)
    }
}
