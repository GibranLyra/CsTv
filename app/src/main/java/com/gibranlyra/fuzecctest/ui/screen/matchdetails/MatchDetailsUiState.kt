package com.gibranlyra.fuzecctest.ui.screen.matchdetails

import com.gibranlyra.fuzecctest.domain.model.MatchData
import com.gibranlyra.fuzecctest.domain.model.MatchDetailsTeamsData
import com.gibranlyra.fuzecctest.domain.model.State

internal data class MatchDetailsUiState(
    val matchState: State<MatchData> = State.Uninitialized(),
    val teamsState: State<MatchDetailsTeamsData> = State.Uninitialized(),
)
