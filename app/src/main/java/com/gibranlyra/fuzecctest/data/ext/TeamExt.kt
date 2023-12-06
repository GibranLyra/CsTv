package com.gibranlyra.fuzecctest.data.ext

import com.gibranlyra.fuzecctest.data.entity.Team
import com.gibranlyra.fuzecctest.domain.model.PandaImage
import com.gibranlyra.fuzecctest.domain.model.TeamData

fun Team.toTeamData(): TeamData {
    val players = players?.map { player -> player.toPlayerData() } ?: listOf()
    return TeamData(
        teamId = id,
        name = name,
        players = players,
        teamImage = PandaImage(imageUrl.orEmpty())
    )
}