package com.gibranlyra.fuzecctest.ui.component

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.gibranlyra.fuzecctest.domain.model.ToolbarData
import com.gibranlyra.fuzecctest.ui.navigation.AppTopBar
import com.gibranlyra.fuzecctest.ui.navigation.Screen

@Composable
internal fun ScreenTopBar(
    navController: NavHostController,
    screen: Screen,
    toolbarData: ToolbarData<*>,
    navigateUp: () -> Unit = { navController.navigateUp() }
) {
    when (screen.appTopBar) {
        AppTopBar.SIMPLE -> {
            SimpleTopBar(
                title = toolbarData.title,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = navigateUp
            )
        }

        AppTopBar.CENTERED -> {
            CenteredTopBar(
                title = toolbarData.title,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = navigateUp
            )
        }
    }
}
