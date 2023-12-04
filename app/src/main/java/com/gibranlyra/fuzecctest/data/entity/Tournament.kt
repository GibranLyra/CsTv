package com.gibranlyra.fuzecctest.data.entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Tournament(
    @SerialName("begin_at")
    val beginAt: String? = "",
    @SerialName("detailed_stats")
    val detailedStats: Boolean = false,
    @SerialName("end_at")
    val endAt: String? = "",
    @SerialName("has_bracket")
    val hasBracket: Boolean = false,
    @SerialName("id")
    val id: Int = 0,
    @SerialName("league_id")
    val leagueId: Int = 0,
    @SerialName("live_supported")
    val liveSupported: Boolean = false,
    @SerialName("modified_at")
    val modifiedAt: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("prizepool")
    val prizepool: String? = null,
    @SerialName("serie_id")
    val serieId: Int = 0,
    @SerialName("slug")
    val slug: String = "",
    @SerialName("tier")
    val tier: String? = "",
    @SerialName("winner_id")
    val winnerId: String? = null,
    @SerialName("winner_type")
    val winnerType: String = ""
)