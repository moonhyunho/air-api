package com.air.api.domain

import com.air.api.interfaces.request.AirportCreateRequest
import javax.persistence.*

@Entity
@Table(name = "airport")
data class Airport(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "code")
    var code: String,

    @Column(name = "name")
    var name: String,

    @Column(name = "nt_code")
    var nt_code: String? = null

) {
    fun modify(code: String?, name: String?, nt_code: String?) {
        this.apply {
            code?.let { this.code = it }
            name?.let { this.name = it }
            nt_code?.let { this.nt_code = it }
        }
    }

    companion object {
        fun of(request: AirportCreateRequest): Airport {
            return Airport(
                code = request.code,
                name = request.name,
                nt_code = request.nt_code
            )
        }
    }
}