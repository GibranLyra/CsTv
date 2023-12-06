package com.gibranlyra.fuzecctest.data.ext

import com.gibranlyra.fuzecctest.data.entity.Player
import com.gibranlyra.fuzecctest.domain.model.PlayerData

fun Player.toPlayerData(): PlayerData =
    PlayerData(
        playerId = id,
        nickName = name,
        name = "$firstName $lastName",
        imageUrl = imageUrl.orEmpty()
    )