package com.gibranlyra.cstv.data.entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Team(
    @SerialName("acronym")
    val acronym: String? = null,
    @SerialName("id")
    val id: Int = 0,
    @SerialName("image_url")
    val imageUrl: String? = null,
    @SerialName("location")
    val location: String? = "",
    @SerialName("modified_at")
    val modifiedAt: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("slug")
    val slug: String = "",
    @SerialName("players")
    val players: List<Player>? = listOf()
)