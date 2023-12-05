package com.gibranlyra.fuzecctest.data.ext

import com.gibranlyra.fuzecctest.data.entity.Match
import com.gibranlyra.fuzecctest.data.entity.Opponent
import com.gibranlyra.fuzecctest.data.entity.OpponentX
import com.gibranlyra.fuzecctest.domain.model.MatchData
import com.gibranlyra.fuzecctest.domain.model.PandaImage
import com.gibranlyra.fuzecctest.util.convertToLocalDateTime

internal fun Match.toMatchData(): MatchData {
    val team1 = getOpponent(opponents, 0)
    val team2 = getOpponent(opponents, 1)
    return MatchData(
        id = id,
        leagueImageUrl = league.imageUrl.orEmpty(),
        team1Image = PandaImage(team1.imageUrl.orEmpty()),
        team1Name = team1.name,
        team2Image = PandaImage(team2.imageUrl.orEmpty()),
        team2Name = team2.name,
        matchStatus = status,
        beginAt = beginAt?.let { convertToLocalDateTime(it) } ?: "TBD",
        leagueName = league.name,
        serieName = serie.name.orEmpty(),
    )
}

private fun getOpponent(opponents: List<Opponent>, index: Int) =
    opponents.getOrElse(index) { Opponent(OpponentX(name = "TBD")) }.opponent