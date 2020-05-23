package com.air.api.interfaces.controller

import com.air.api.application.AirportService
import com.air.api.domain.Airport
import com.air.api.interfaces.request.AirportCreateRequest
import com.air.api.interfaces.request.AirportModifyRequest
import com.air.api.interfaces.response.AirportView
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/airports")
class AirportController(
    private val airportService : AirportService
) {

    @PostMapping
    fun create(@RequestBody request: AirportCreateRequest): ResponseEntity<AirportView> {
        request.validate()
        return ResponseEntity(
                AirportView.of(airportService.create(Airport.of(request))),
                HttpStatus.CREATED
        )
    }

    @PutMapping
    fun modify(@RequestBody request: AirportModifyRequest) : ResponseEntity<HttpStatus> {
        airportService.modify(request.id, request.code, request.name, request.name)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) : ResponseEntity<HttpStatus> {
        airportService.delete(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/{code}")
    fun getAirportByCode(@PathVariable code: String) : ResponseEntity<Airport> {
        return ResponseEntity.ok(
            airportService.getByCode(code)
        )
    }

    @GetMapping
    fun getAirports() : ResponseEntity<List<Airport>> {
        return ResponseEntity.ok(
            airportService.findAll()
        )
    }

    @PostMapping("/create-all")
    fun createAll(@RequestBody requests: List<AirportCreateRequest>): ResponseEntity<List<AirportView>> {
        return ResponseEntity(
            airportService.createAll(
                requests.map {
                    Airport.of(it) }).map {
                AirportView.of(it)
            }, HttpStatus.CREATED
        )
    }

}