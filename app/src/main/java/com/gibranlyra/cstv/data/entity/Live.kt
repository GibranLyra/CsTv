package com.gibranlyra.cstv.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Live(
    @SerialName("opens_at")
    val opensAt: String? = null,
    @SerialName("supported")
    val supported: Boolean = false,
    @SerialName("url")
    val url: String? = null,
)
