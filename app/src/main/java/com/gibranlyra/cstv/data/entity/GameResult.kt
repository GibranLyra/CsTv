package com.gibranlyra.cstv.data.entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameResult(
    @SerialName("score")
    val score: Int = 0,
    @SerialName("team_id")
    val teamId: Int = 0
)