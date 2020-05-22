package com.air.api.interfaces.controller

import com.air.api.application.AirportService
import com.air.api.domain.Airport
import com.air.api.interfaces.request.AirportCreateRequest
import com.air.api.interfaces.request.AirportModifyRequest
import com.air.api.interfaces.response.AirportView
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.apache.coyote.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/airports")
class AirportController(
    private val airportService : AirportService
) {

    @PostMapping
    @ApiOperation(value="공항등록")
    @ApiImplicitParams(
            ApiImplicitParam(name = "code", value = "공항코드", required = true, dataType = "string", paramType = "query", defaultValue = ""),
            ApiImplicitParam(name = "name", value = "공항명", required = true, dataType = "string", paramType = "query", defaultValue = ""),
            ApiImplicitParam(name = "nt_code", value = "국가코드", required = true, dataType = "string", paramType = "query", defaultValue = "")
    )
    fun create(request: AirportCreateRequest): ResponseEntity<AirportView> {
        request.validate()
        return ResponseEntity(
                AirportView.of(airportService.create(Airport.of(request))),
                HttpStatus.CREATED
        )
    }

    @PutMapping
    @ApiOperation(value="공항수정")
    @ApiImplicitParams(
            ApiImplicitParam(name = "id", value = "공항 관리 ID", required = true, dataType = "string", paramType = "query", defaultValue = ""),
            ApiImplicitParam(name = "code", value = "공항코드", required = true, dataType = "string", paramType = "query", defaultValue = ""),
            ApiImplicitParam(name = "name", value = "공항명", required = true, dataType = "string", paramType = "query", defaultValue = ""),
            ApiImplicitParam(name = "nt_code", value = "국가코드", required = true, dataType = "string", paramType = "query", defaultValue = "")
    )
    fun modify(request: AirportModifyRequest) : ResponseEntity<HttpStatus> {
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