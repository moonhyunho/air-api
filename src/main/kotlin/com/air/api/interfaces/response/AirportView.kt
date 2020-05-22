package com.air.api.interfaces.response

import com.air.api.domain.Airport

data class AirportView(
    val id: Long,
    val code: String,
    val name: String,
    val nt_code: String?
) {
    companion object {
        fun of(airport: Airport): AirportView {
            return AirportView(
                id = airport.id!!,
                code = airport.code,
                name = airport.name,
                nt_code = airport.nt_code
            )
        }
    }
}