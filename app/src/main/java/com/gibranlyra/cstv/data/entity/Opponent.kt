package com.gibranlyra.cstv.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Opponent(
    @SerialName("opponent")
    val opponent: Team = Team(),
    @SerialName("type")
    val type: String = "",
)
