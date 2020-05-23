package com.air.api.support

enum class MessageKey(private val messageSourceKey: String) {

    INVALID_PARAMETER("invalid.parameter"),
    NOT_FOUND_AIRLINE("not.found.airline"),
    NOT_FOUND_AIRPORT("not.found.airport"),
    EXCEPTION("exception");

    fun getMessageSourceKey(): String {
        return messageSourceKey
    }
}