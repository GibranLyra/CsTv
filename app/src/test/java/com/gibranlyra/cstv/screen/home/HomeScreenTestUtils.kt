package com.gibranlyra.cstv.screen.home

import com.gibranlyra.cstv.domain.model.MatchData

internal class HomeScreenTestUtils {
    var matchClicked: MatchData? = null
    var retryButtonClicked = false
    var matchesRefreshed = false

    fun onMatchClicked(): (MatchData) -> Unit = { match -> matchClicked = match }

    fun onRetryButtonClicked(): () -> Unit = { retryButtonClicked = true }

    fun onRefreshMatches(): () -> Unit = { matchesRefreshed = true }
}
