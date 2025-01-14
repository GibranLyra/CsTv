package com.gibranlyra.cstv.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideogameTitle(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("name")
    val name: String = "",
    @SerialName("slug")
    val slug: String = "",
    @SerialName("videogame_id")
    val videogameId: Int = 0,
)
