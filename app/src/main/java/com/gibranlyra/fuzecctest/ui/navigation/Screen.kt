package com.gibranlyra.fuzecctest.ui.navigation

internal enum class ScreenType(val screen: Screen) {
    HOME(Screen.Home),
}

internal sealed class Screen(val route: String, val appTopBar: AppTopBar) {
    data object Home : Screen(route = ScreenType.HOME.name, appTopBar = AppTopBar.SIMPLE)
}