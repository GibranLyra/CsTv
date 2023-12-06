package com.gibranlyra.fuzecctest.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TeamData(
    val teamId: Int,
    val name: String = "",
    val players: List<PlayerData>,
    val imageUrl: String = ""
) : Parcelable