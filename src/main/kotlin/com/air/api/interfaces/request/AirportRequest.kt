package com.air.api.interfaces.request

data class AirportCreateRequest(
    val code : String,
    val name : String,
    val nt_code : String?
){
    fun validate() {
        if(code.length != 3) throw Exception("코드는 3자리!")
        if(nt_code?.length != 2) throw Exception("국가코드는 2자리!")
    }
}

data class AirportModifyRequest(
    val id: Long,
    val code: String?,
    val name: String?,
    val nt_code: String?
)