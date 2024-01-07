package com.gibranlyra.cstv.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.gibranlyra.cstv.domain.model.ToolbarData
import com.gibranlyra.cstv.ui.component.ScreenTopBar
import com.gibranlyra.cstv.ui.ext.getRouteWithoutArguments
import com.gibranlyra.cstv.ui.navigation.AppNavHost
import com.gibranlyra.cstv.ui.navigation.Screen
import com.gibranlyra.cstv.ui.navigation.ScreenType

@Composable
internal fun CsTvApp(navController: NavHostController = rememberNavController()) {
    val startDestination = Screen.Home
    var currentScreen: Screen by remember { mutableStateOf(startDestination) }
    var toolbarData by remember { mutableStateOf(ToolbarData<Any>()) }

    LaunchedEffect(navController.currentBackStackEntryFlow) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            val screenName = backStackEntry.getRouteWithoutArguments().orEmpty()
            currentScreen = ScreenType.valueOf(screenName).screen
        }
    }

    Scaffold(
        topBar = {
            ScreenTopBar(
                navController = navController,
                screen = currentScreen,
                toolbarData = toolbarData,
            )
        },
        content = { innerPadding ->
            AppNavHost(
                navController = navController,
                onToolbarComposition = { toolbarData = it },
                modifier = Modifier.padding(innerPadding),
                startDestination = startDestination.route
            )
        }
    )
}
