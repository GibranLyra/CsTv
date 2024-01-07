package com.gibranlyra.cstv.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MatchDetailsTeamsData(
    val team1: TeamData,
    val team2: TeamData,
) : Parcelable