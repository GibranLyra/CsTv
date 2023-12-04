package com.gibranlyra.fuzecctest.data.entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Match(
    @SerialName("begin_at")
    val beginAt: String? = "",
    @SerialName("detailed_stats")
    val detailedStats: Boolean = false,
    @SerialName("draw")
    val draw: Boolean = false,
    @SerialName("end_at")
    val endAt: String? = "",
    @SerialName("forfeit")
    val forfeit: Boolean = false,
    @SerialName("game_advantage")
    val gameAdvantage: String? = "",
    @SerialName("games")
    val games: List<Game> = listOf(),
    @SerialName("id")
    val id: Int = 0,
    @SerialName("league")
    val league: League = League(),
    @SerialName("league_id")
    val leagueId: Int = 0,
    @SerialName("live")
    val live: Live = Live(),
    @SerialName("match_type")
    val matchType: String = "",
    @SerialName("modified_at")
    val modifiedAt: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("number_of_games")
    val numberOfGames: Int = 0,
    @SerialName("opponents")
    val opponents: List<Opponent> = listOf(),
    @SerialName("original_scheduled_at")
    val originalScheduledAt: String? = "",
    @SerialName("rescheduled")
    val rescheduled: Boolean = false,
    @SerialName("results")
    val results: List<GameResult> = listOf(),
    @SerialName("scheduled_at")
    val scheduledAt: String = "",
    @SerialName("serie")
    val serie: Serie = Serie(),
    @SerialName("serie_id")
    val serieId: Int = 0,
    @SerialName("slug")
    val slug: String = "",
    @SerialName("status")
    val status: String = "",
    @SerialName("streams_list")
    val streamsList: List<Stream> = listOf(),
    @SerialName("tournament")
    val tournament: Tournament = Tournament(),
    @SerialName("tournament_id")
    val tournamentId: Int = 0,
    @SerialName("videogame")
    val videoGame: Videogame = Videogame(),
    @SerialName("videogame_title")
    val videoGameTitle: VideogameTitle? = null,
    @SerialName("videogame_version")
    val videoGameVersion: String? = null,
    @SerialName("winner")
    val winner: Winner? = null,
    @SerialName("winner_id")
    val winnerId: String? = null,
    @SerialName("winner_type")
    val winnerType: String = ""
)