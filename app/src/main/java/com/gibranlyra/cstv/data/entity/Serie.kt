package com.gibranlyra.cstv.data.entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Serie(
    @SerialName("begin_at")
    val beginAt: String? = "",
    @SerialName("end_at")
    val endAt: String? = null,
    @SerialName("full_name")
    val fullName: String = "",
    @SerialName("id")
    val id: Int = 0,
    @SerialName("league_id")
    val leagueId: Int = 0,
    @SerialName("modified_at")
    val modifiedAt: String = "",
    @SerialName("name")
    val name: String? = "",
    @SerialName("season")
    val season: String? = "",
    @SerialName("slug")
    val slug: String = "",
    @SerialName("winner_id")
    val winnerId: String? = null,
    @SerialName("winner_type")
    val winnerType: String? = null,
    @SerialName("year")
    val year: Int = 0
)