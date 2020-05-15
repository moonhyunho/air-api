package com.air.api.interfaces.controller

import com.air.api.application.AirlineService
import com.air.api.domain.Airline
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/airlines")
class AirlineController(
    private val airlineService: AirlineService
) {

    @GetMapping("/{code}")
    fun getAirlineByCode(@PathVariable code: String): ResponseEntity<Airline> {
        return ResponseEntity.ok(
            airlineService.getByCode(code)
        )
    }

    @GetMapping
    fun findAirlines(): ResponseEntity<List<Airline>> {
        return ResponseEntity.ok(
            airlineService.findAll()
        )
    }

    @PostMapping
    fun save(airline: Airline): ResponseEntity<Airline> {
        return ResponseEntity(
            airlineService.save(airline),
            HttpStatus.CREATED
        )
    }

    @PutMapping
    fun update(airline: Airline): ResponseEntity<Airline> {
        return ResponseEntity(
            airlineService.update(airline),
            HttpStatus.ACCEPTED
        )
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<HttpStatus> {
        airlineService.delete(airlineService.getById(id))
        return ResponseEntity.noContent().build()
    }


}