package com.gibranlyra.fuzecctest.ui.navigation

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
        appTopBar = AppTopBar.SIMPLE
    ) {
        const val matchIdArg = "matchId"

        val routeWithArgs = "$route/{$matchIdArg}"

        val arguments = listOf(navArgument(matchIdArg) { type = NavType.IntType })
    }

}