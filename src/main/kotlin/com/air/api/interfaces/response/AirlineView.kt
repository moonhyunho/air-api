package com.air.api.interfaces.response

import com.air.api.domain.Airline

data class AirlineView(
    val id: Long,
    val code: String,
    val name: String
) {
    companion object {
        fun of(airline: Airline): AirlineView {
            return AirlineView(
                id = airline.id!!,
                code = airline.code,
                name = airline.name
            )
        }
    }
}