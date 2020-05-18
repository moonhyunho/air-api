package com.air.api.interfaces.request

data class AirlineCreateRequest(
    val code: String,
    val name: String
)

data class AirlineModifyRequest(
    val id: Long,
    val code: String?,
    val name: String?
)