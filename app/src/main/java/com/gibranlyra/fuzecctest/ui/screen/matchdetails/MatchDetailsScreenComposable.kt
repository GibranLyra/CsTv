package com.gibranlyra.fuzecctest.ui.screen.matchdetails

import android.app.Activity
import android.content.Context
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gibranlyra.fuzecctest.domain.model.ToolbarData
import com.gibranlyra.fuzecctest.ui.ext.viewModelFactoryProvider
import com.gibranlyra.fuzecctest.ui.navigation.Screen
import com.gibranlyra.fuzecctest.ui.navigation.slideInEnterTransition
import com.gibranlyra.fuzecctest.ui.navigation.slideIntoTransition
import com.gibranlyra.fuzecctest.ui.navigation.slideOutEnterTransition
import com.gibranlyra.fuzecctest.ui.navigation.slideOutOfContainerTransition

internal fun NavGraphBuilder.matchDetailsScreenComposable(
    context: Context,
    onToolbarComposition: (ToolbarData<String>) -> Unit,
    enterTransition: AnimatedContentTransitionScope<*>.() -> EnterTransition = { slideInEnterTransition() },
    exitTransition: AnimatedContentTransitionScope<*>.() -> ExitTransition = { slideOutEnterTransition() },
    popEnterTransition: AnimatedContentTransitionScope<*>.() -> EnterTransition = { slideIntoTransition() },
    popExitTransition: AnimatedContentTransitionScope<*>.() -> ExitTransition = { slideOutOfContainerTransition() },
) {

    composable(
        route = Screen.MatchDetails.routeWithArgs,
        arguments = Screen.MatchDetails.arguments,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
    ) { backStackEntry ->
        val matchId = backStackEntry.arguments?.getInt(Screen.MatchDetails.matchIdArg) ?: -1

        val viewModelFactory = (context as Activity).viewModelFactoryProvider()
            .characterDetailViewModelFactory()

        val matchDetailsViewModel = viewModel<MatchDetailsViewModel>(
            factory = MatchDetailsViewModel.provideFactory(viewModelFactory, matchId)
        )

        val uiState by matchDetailsViewModel.uiState.collectAsState()

        MatchDetailsScreen(
            uiState = uiState,
            onToolbarComposition = onToolbarComposition,
            onRetryClick = { id -> matchDetailsViewModel.loadScreen(id) },
        )
    }
}
