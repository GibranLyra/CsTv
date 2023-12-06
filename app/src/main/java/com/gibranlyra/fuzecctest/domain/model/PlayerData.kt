package com.gibranlyra.fuzecctest.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlayerData(
    val playerId: Int,
    val nickName: String = "",
    val name: String = "",
    val playerImage: PandaImage = PandaImage()
) : Parcelable