package com.air.api.domain

import com.air.api.interfaces.request.AirlineCreateRequest
import javax.persistence.*

@Entity
@Table(name = "airline")
data class Airline(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "code")
    var code: String,

    @Column(name = "name")
    var name: String

) {
    fun modify(code: String?, name: String?) {
        this.apply {
            code?.let { this.code = it }
            name?.let { this.name = it }
        }
    }

    companion object {
        fun of(request: AirlineCreateRequest): Airline {
            return Airline(
                code = request.code,
                name = request.name
            )
        }
    }
}