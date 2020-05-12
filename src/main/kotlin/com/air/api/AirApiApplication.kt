package com.air.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AirApiApplication

fun main(args: Array<String>) {
    runApplication<AirApiApplication>(*args)
}
