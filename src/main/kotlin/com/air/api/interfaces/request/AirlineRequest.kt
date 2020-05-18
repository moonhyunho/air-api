package com.air.api.interfaces.request

data class AirlineCreateRequest(
    val code: String,
    val name: String
) {
    fun validate() {
        if(code.length != 2) throw Exception("코드는 2자리!")
    }
}

data class AirlineModifyRequest(
    val id: Long,
    val code: String?,
    val name: String?
)