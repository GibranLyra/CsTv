package com.gibranlyra.cstv.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TeamData(
    val teamId: Int,
    val name: String = "",
    val players: List<PlayerData>,
    val teamImage: PandaImage = PandaImage(),
) : Parcelable