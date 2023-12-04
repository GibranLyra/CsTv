package com.gibranlyra.fuzecctest.data.entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Opponent(
    @SerialName("opponent")
    val opponent: OpponentX = OpponentX(),
    @SerialName("type")
    val type: String = ""
)