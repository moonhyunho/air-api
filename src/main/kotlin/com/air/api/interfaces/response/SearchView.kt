package com.air.api.interfaces.response

import com.air.api.support.enums.Cabin
import com.air.api.support.enums.PassengerType

data class SearchView(
    val fareItineraries: List<FareItineraryView>
)

data class FareItineraryView(
    val schedules: List<ScheduleView>,
    val validatingCarrier: String,
    val passengerFares: List<PassengerFareView>
)

data class PassengerFareView(
    val type: PassengerType,
    val airPrice: Long,
    val tax: Long
)

data class ScheduleView(
    val departureLocation: String,
    val departureAt: String,
    val arrivalLocation: String,
    val arrivalAt: String,
    val operatingCarrier: String? = null,
    val flightNumber: String,
    val cabin: Cabin,
    val bookingClass: String,
    val seatAvailability: Int
)