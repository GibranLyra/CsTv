package com.gibranlyra.fuzecctest.data.entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Stream(
    @SerialName("embed_url")
    val embedUrl: String? = "",
    @SerialName("language")
    val language: String = "",
    @SerialName("main")
    val main: Boolean = false,
    @SerialName("official")
    val official: Boolean = false,
    @SerialName("raw_url")
    val rawUrl: String = ""
)