package com.gibranlyra.cstv.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class League(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("image_url")
    val imageUrl: String? = "",
    @SerialName("modified_at")
    val modifiedAt: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("slug")
    val slug: String = "",
    @SerialName("url")
    val url: String? = "",
)
