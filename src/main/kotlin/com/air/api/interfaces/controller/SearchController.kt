package com.air.api.interfaces.controller

import com.air.api.application.SearchService
import com.air.api.interfaces.request.SearchRequest
import com.air.api.interfaces.response.SearchView
import com.air.api.support.enums.Cabin
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class SearchController(
    private val searchService: SearchService
) {
    @GetMapping(
        value = [
            "/search/{origin}-{destination}/{outboundDate}",
            "/search/{origin}-{destination}/{outboundDate}/{inboundDate}"
        ]
    )
    fun search(
        @PathVariable origin: String,
        @PathVariable destination: String,
        @PathVariable outboundDate: String,
        @PathVariable(required = false) inboundDate: String?,
        @RequestParam(value = "adult", required = true) adult: Int,
        @RequestParam(value = "child", required = false) child: Int = 0,
        @RequestParam(value = "infant", required = false) infant: Int = 0,
        @RequestParam(value = "cabins", required = true) cabins: List<Cabin>
    ): ResponseEntity<SearchView> {
        return ResponseEntity.ok(
            searchService.search(SearchRequest.of(
                origin = origin,
                destination = destination,
                outboundDate = outboundDate,
                inboundDate = inboundDate,
                adult = adult,
                child = child,
                infant = infant,
                cabins = cabins
            ))
        )
    }
}