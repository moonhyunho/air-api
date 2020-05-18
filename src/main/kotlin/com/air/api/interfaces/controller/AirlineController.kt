package com.air.api.interfaces.controller

import com.air.api.application.AirlineService
import com.air.api.domain.Airline
import com.air.api.interfaces.request.AirlineCreateRequest
import com.air.api.interfaces.request.AirlineModifyRequest
import com.air.api.interfaces.response.AirlineView
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
    fun create(request: AirlineCreateRequest): ResponseEntity<AirlineView> {
        request.validate()
        return ResponseEntity(
            AirlineView.of(airlineService.create(Airline.of(request))),
            HttpStatus.CREATED
        )
    }

    @PutMapping
    fun modify(request: AirlineModifyRequest): ResponseEntity<HttpStatus> {
        airlineService.modify(request.id, request.code, request.name)
        return ResponseEntity.accepted().build()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<HttpStatus> {
        airlineService.delete(airlineService.getAirline(id))
        return ResponseEntity.noContent().build()
    }


}