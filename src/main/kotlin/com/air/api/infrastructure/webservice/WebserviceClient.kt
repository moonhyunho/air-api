package com.air.api.infrastructure.webservice

import com.air.api.interfaces.request.SearchRequest
import com.air.api.interfaces.response.SearchView
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.jackson.defaultMapper
import com.github.kittinunf.fuel.jackson.jacksonDeserializerOf
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component

@Component
class WebserviceClient(
    @Value("\${webservice.endpoint}") private val endpoint: String,
    @Value("\${webservice.timeout}") private val timeout: Int
) {

    fun search(request: SearchRequest): SearchView {
        print("search body : " + defaultMapper.writeValueAsString(request))

        val (_, _, result) = "$endpoint/search".httpPost().header(
            mapOf(
                HttpHeaders.CONTENT_TYPE to MediaType.APPLICATION_JSON
            )
        ).body(defaultMapper.writeValueAsString(request)).timeout(timeout).timeoutRead(timeout)
            .responseObject(jacksonDeserializerOf<SearchView>())

        return result.fold(
            success = { it },
            failure = { throw it.exception }
        )
    }

}