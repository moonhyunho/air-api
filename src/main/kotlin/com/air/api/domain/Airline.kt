package com.air.api.domain

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

)