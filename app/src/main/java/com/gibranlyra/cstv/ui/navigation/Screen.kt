package com.gibranlyra.cstv.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

internal enum class ScreenType(val screen: Screen) {
    HOME(Screen.Home),
    MATCH_DETAILS(Screen.MatchDetails),
}

internal sealed class Screen(val route: String, val appTopBar: AppTopBar) {
    data object Home : Screen(route = ScreenType.HOME.name, appTopBar = AppTopBar.SIMPLE)

    data object MatchDetails : Screen(
        route = ScreenType.MATCH_DETAILS.name,
        appTopBar = AppTopBar.CENTERED,
    ) {
        const val MATCH_ID_ARG = "matchId"

        val routeWithArgs = "$route/{$MATCH_ID_ARG}"

        val arguments = listOf(navArgument(MATCH_ID_ARG) { type = NavType.IntType })
    }
}
