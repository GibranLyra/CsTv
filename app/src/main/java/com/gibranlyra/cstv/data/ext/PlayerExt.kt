package com.gibranlyra.cstv.data.ext

import com.gibranlyra.cstv.data.entity.Player
import com.gibranlyra.cstv.domain.model.PandaImage
import com.gibranlyra.cstv.domain.model.PlayerData

fun Player.toPlayerData(): PlayerData =
    PlayerData(
        playerId = id,
        nickName = name,
        name = "${firstName.orEmpty()} ${lastName.orEmpty()}",
        playerImage = PandaImage(imageUrl.orEmpty())
    )