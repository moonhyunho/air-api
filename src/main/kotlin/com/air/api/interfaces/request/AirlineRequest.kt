package com.air.api.interfaces.request

import com.air.api.support.MessageKey
import com.air.api.support.exception.MethodArgumentInvalidException

data class AirlineCreateRequest(
    val code: String,
    val name: String
) {
    fun validate() {
        if(code.length != 2) throw MethodArgumentInvalidException(MessageKey.INVALID_PARAMETER)
    }
}

data class AirlineModifyRequest(
    val id: Long,
    val code: String?,
    val name: String?
)