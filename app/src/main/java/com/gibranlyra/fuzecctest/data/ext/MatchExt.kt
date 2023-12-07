package com.gibranlyra.fuzecctest.data.ext

import com.gibranlyra.fuzecctest.data.entity.Match
import com.gibranlyra.fuzecctest.data.entity.Opponent
import com.gibranlyra.fuzecctest.data.entity.Team
import com.gibranlyra.fuzecctest.domain.model.MatchData
import com.gibranlyra.fuzecctest.domain.model.PandaImage
import com.gibranlyra.fuzecctest.util.convertToLocalDateTime

internal fun Match.toMatchData(): MatchData {
    val team1 = getOpponent(opponents, 0)
    val team2 = getOpponent(opponents, 1)

    return MatchData(
        id = id,
        leagueImageUrl = league.imageUrl.orEmpty(),
        team1Id = team1.id,
        team1Name = team1.name,
        team1Image = PandaImage(team1.imageUrl.orEmpty()),
        team2Id = team2.id,
        team2Name = team2.name,
        team2Image = PandaImage(team2.imageUrl.orEmpty()),
        matchStatus = status,
        beginAt = if (beginAt.isNullOrBlank()) "TBD" else convertToLocalDateTime(beginAt),
        leagueName = league.name,
        serieName = serie.name.orEmpty(),
    )
}

private fun getOpponent(opponents: List<Opponent>, index: Int) =
    opponents.getOrElse(index) { Opponent(Team(name = "TBD")) }.opponent