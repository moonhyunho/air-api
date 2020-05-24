package com.air.api.application

import com.air.api.infrastructure.webservice.WebserviceClient
import com.air.api.interfaces.request.SearchRequest
import com.air.api.interfaces.response.SearchView
import org.springframework.stereotype.Service

@Service
class SearchService(
    private val webserviceClient: WebserviceClient
) {
    fun search(request: SearchRequest): SearchView {
        return webserviceClient.search(request)
    }
}