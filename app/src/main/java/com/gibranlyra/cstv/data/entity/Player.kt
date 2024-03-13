package com.gibranlyra.cstv.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Player(
    @SerialName("age")
    val age: Int? = 0,
    @SerialName("birthday")
    val birthday: String? = "",
    @SerialName("first_name")
    val firstName: String? = "",
    @SerialName("id")
    val id: Int = 0,
    @SerialName("image_url")
    val imageUrl: String? = "",
    @SerialName("last_name")
    val lastName: String? = "",
    @SerialName("modified_at")
    val modifiedAt: String? = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("nationality")
    val nationality: String? = "",
    @SerialName("role")
    val role: String? = "",
    @SerialName("slug")
    val slug: String? = "",
)
