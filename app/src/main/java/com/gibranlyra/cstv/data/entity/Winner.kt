package com.gibranlyra.cstv.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Winner(
    @SerialName("id")
    val id: String? = null,
    @SerialName("type")
    val type: String = "",
)
