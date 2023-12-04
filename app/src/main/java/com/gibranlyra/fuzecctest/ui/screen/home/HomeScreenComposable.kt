package com.gibranlyra.fuzecctest.ui.screen.home

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.gibranlyra.fuzecctest.domain.model.ToolbarData
import com.gibranlyra.fuzecctest.ui.navigation.Screen
import com.gibranlyra.fuzecctest.ui.navigation.slideInEnterTransition
import com.gibranlyra.fuzecctest.ui.navigation.slideIntoTransition
import com.gibranlyra.fuzecctest.ui.navigation.slideOutEnterTransition
import com.gibranlyra.fuzecctest.ui.navigation.slideOutOfContainerTransition

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

        uiState.navigateToMatchDetailScreen?.let {
            // TODO
        }

        HomeScreen(
            uiState = uiState,
            onRetryButtonClicked = homeViewModel::getMatches,
            onToolbarComposition = onToolbarComposition,
            onMatchClicked = {
                // TODO
            },
        )
    }
}
