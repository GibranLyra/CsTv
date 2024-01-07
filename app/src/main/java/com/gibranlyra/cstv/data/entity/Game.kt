package com.gibranlyra.cstv.data.entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Game(
    @SerialName("begin_at")
    val beginAt: String? = null,
    @SerialName("complete")
    val complete: Boolean = false,
    @SerialName("detailed_stats")
    val detailedStats: Boolean = false,
    @SerialName("end_at")
    val endAt: String? = null,
    @SerialName("finished")
    val finished: Boolean = false,
    @SerialName("forfeit")
    val forfeit: Boolean = false,
    @SerialName("id")
    val id: Int = 0,
    @SerialName("length")
    val length: String? = null,
    @SerialName("match_id")
    val matchId: Int = 0,
    @SerialName("position")
    val position: Int = 0,
    @SerialName("status")
    val status: String = "",
    @SerialName("winner")
    val winner: Winner = Winner(),
    @SerialName("winner_type")
    val winnerType: String = ""
)