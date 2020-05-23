package com.air.api.interfaces.response

data class RestExceptionView(
    val status: Int,
    val exception: String,
    val message: String?
)