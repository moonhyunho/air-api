package com.air.api.interfaces.request

import com.air.api.support.enums.Cabin

data class SearchRequest(
    val adult: Int,
    val child: Int = 0,
    val infant: Int = 0,
    val cabins: List<Cabin>,
    val originDestinations: List<OriginDestination>
) {
    companion object {
        fun of(
            origin: String,
            destination: String,
            outboundDate: String,
            inboundDate: String?,
            adult: Int,
            child: Int,
            infant: Int,
            cabins: List<Cabin>
        ): SearchRequest {
            return SearchRequest(
                adult = adult,
                child = child,
                infant = infant,
                cabins = cabins,
                originDestinations = if(inboundDate == null) {
                    listOf(
                        OriginDestination.of(origin, destination, outboundDate)
                    )
                } else {
                    listOf(
                        OriginDestination.of(origin, destination, outboundDate),
                        OriginDestination.of(destination, origin, inboundDate)
                    )
                }
            )
        }
    }
}

data class OriginDestination(
    val origin: String,
    val destination: String,
    val departureDate: String
) {
    companion object {
        fun of(origin: String, destination: String, departureDate: String): OriginDestination {
            return OriginDestination(
                origin = origin,
                destination = destination,
                departureDate = departureDate
            )
        }
    }
}