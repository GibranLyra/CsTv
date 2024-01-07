package com.gibranlyra.cstv.ui.screen.matchdetails

import com.gibranlyra.cstv.domain.model.MatchData
import com.gibranlyra.cstv.domain.model.MatchDetailsTeamsData
import com.gibranlyra.cstv.domain.model.State

internal data class MatchDetailsUiState(
    val matchState: State<MatchData> = State.Uninitialized(),
    val teamsState: State<MatchDetailsTeamsData> = State.Uninitialized(),
)
