package com.gibranlyra.cstv.data.ext

import com.gibranlyra.cstv.data.entity.Team
import com.gibranlyra.cstv.domain.model.PandaImage
import com.gibranlyra.cstv.domain.model.TeamData

fun Team.toTeamData(): TeamData {
    val players = players?.map { player -> player.toPlayerData() } ?: listOf()
    return TeamData(
        teamId = id,
        name = name,
        players = players,
        teamImage = PandaImage(imageUrl.orEmpty()),
    )
}
