package com.gibranlyra.fuzecctest.domain.model

import android.os.Parcelable
import com.gibranlyra.fuzecctest.data.entity.MatchStatus
import kotlinx.parcelize.Parcelize

@Parcelize
data class MatchData(
    val id: Int,
    val leagueImageUrl: String = "",
    val team1Id: Int = -1,
    val team1Name: String = "",
    val team1Image: PandaImage = PandaImage(),
    val team2Id: Int = -1,
    val team2Name: String = "",
    val team2Image: PandaImage = PandaImage(),
    val matchStatus: MatchStatus = MatchStatus.NOT_STARTED,
    val isFinished: Boolean = false,
    val beginAt: String = "",
    val leagueName: String = "",
    val serieName: String = "",
) : Parcelable
