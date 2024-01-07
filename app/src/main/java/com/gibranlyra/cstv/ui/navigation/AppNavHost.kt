package com.gibranlyra.cstv.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.gibranlyra.cstv.domain.model.ToolbarData
import com.gibranlyra.cstv.ui.screen.home.homeScreenComposable
import com.gibranlyra.cstv.ui.screen.matchdetails.matchDetailsScreenComposable

@Composable
internal fun AppNavHost(
    navController: NavHostController,
    onToolbarComposition: (ToolbarData<Any>) -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = Screen.Home.route,
) {
    val context = LocalContext.current

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        homeScreenComposable(
            navController = navController,
            onToolbarComposition = onToolbarComposition
        )

        matchDetailsScreenComposable(
            context = context,
            onToolbarComposition = onToolbarComposition
        )
    }
}