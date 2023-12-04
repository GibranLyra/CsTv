package com.gibranlyra.fuzecctest.data.ext

import com.gibranlyra.fuzecctest.data.entity.Match
import com.gibranlyra.fuzecctest.data.entity.Opponent
import com.gibranlyra.fuzecctest.data.entity.OpponentX
import com.gibranlyra.fuzecctest.domain.model.MatchData
import com.gibranlyra.fuzecctest.domain.model.PandaImage
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter


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
        isLive = beginAt?.let { isLiveInBrazil(it) } ?: false,
        beginAt = beginAt?.let { convertToLocalDateTime(it) } ?: "TBD",
        leagueName = league.name,
        serieName = serie.name.orEmpty(),
    )
}


fun convertToLocalDateTime(utcString: String): String {
    val brazilTimeZone = ZoneId.of("America/Sao_Paulo")
    val brazilDateTime = ZonedDateTime.ofInstant(Instant.parse(utcString), brazilTimeZone)

    val currentBrazilTime = ZonedDateTime.now(brazilTimeZone)

    val isLive = isLiveInBrazil(utcString)

    val longDateFormat =
        DateTimeFormatter.ofPattern("dd.MM.YYYY, HH:mm").let { brazilDateTime.format(it) }
    val smallDateFormat = DateTimeFormatter.ofPattern("HH:mm").let { brazilDateTime.format(it) }

    return when {
        isLive -> "Live"
        isToday(currentBrazilTime, brazilDateTime) -> "Today, $smallDateFormat"
        else -> longDateFormat
    }
}

private fun isLiveInBrazil(utcString: String): Boolean {
    val brazilTimeZone = ZoneId.of("America/Sao_Paulo")
    val currentBrazilTime = ZonedDateTime.now(brazilTimeZone)
    val brazilDateTime = ZonedDateTime.ofInstant(Instant.parse(utcString), brazilTimeZone)

    return isToday(
        currentBrazilTime,
        brazilDateTime
    ) && currentBrazilTime.hour == brazilDateTime.hour
}

private fun isToday(
    currentBrazilTime: ZonedDateTime,
    brazilDateTime: ZonedDateTime
) = currentBrazilTime.dayOfYear == brazilDateTime.dayOfYear

private fun getOpponent(opponents: List<Opponent>, index: Int) =
    opponents.getOrElse(index) { Opponent(OpponentX(name = "TBD")) }.opponent