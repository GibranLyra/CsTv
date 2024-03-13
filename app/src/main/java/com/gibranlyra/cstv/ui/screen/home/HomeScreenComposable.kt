package com.gibranlyra.cstv.ui.screen.home

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.gibranlyra.cstv.domain.model.ToolbarData
import com.gibranlyra.cstv.ui.navigation.Screen
import com.gibranlyra.cstv.ui.navigation.navigateToMatchDetailsScreen
import com.gibranlyra.cstv.ui.navigation.slideInEnterTransition
import com.gibranlyra.cstv.ui.navigation.slideIntoTransition
import com.gibranlyra.cstv.ui.navigation.slideOutEnterTransition
import com.gibranlyra.cstv.ui.navigation.slideOutOfContainerTransition

internal fun NavGraphBuilder.homeScreenComposable(
    navController: NavHostController,
    onToolbarComposition: (ToolbarData<Any>) -> Unit,
    enterTransition: AnimatedContentTransitionScope<*>.() -> EnterTransition = { slideInEnterTransition() },
    exitTransition: AnimatedContentTransitionScope<*>.() -> ExitTransition = { slideOutEnterTransition() },
    popEnterTransition: AnimatedContentTransitionScope<*>.() -> EnterTransition = { slideIntoTransition() },
    popExitTransition: AnimatedContentTransitionScope<*>.() -> ExitTransition = { slideOutOfContainerTransition() },
) {
    composable(
        route = Screen.Home.route,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
    ) {
        val homeViewModel: HomeViewModel = hiltViewModel()
        val uiState by homeViewModel.uiState.collectAsState()

        uiState.navigateToMatchDetailScreen?.let { event ->
            navController.navigateToMatchDetailsScreen(event.matchId)
            homeViewModel.navigatedMatchDetailsScreen()
        }

        HomeScreen(
            uiState = uiState,
            onRetryButtonClicked = homeViewModel::getMatches,
            onRefreshMatches = homeViewModel::getMatches,
            onToolbarComposition = onToolbarComposition,
            onMatchClicked = { match ->
                homeViewModel.navigateToMatchDetailsScreen(
                    match.id,
                    match.team1Id,
                    match.team2Id,
                )
            },
        )
    }
}
