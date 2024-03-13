package com.gibranlyra.cstv.ui.navigation

import androidx.navigation.NavHostController

internal fun NavHostController.navigateToMatchDetailsScreen(matchId: Int) {
    navigateSingleTopTo("${Screen.MatchDetails.route}/$matchId")
}

private fun NavHostController.navigateSingleTopTo(route: String) = navigate(route) {
    launchSingleTop = true
}
