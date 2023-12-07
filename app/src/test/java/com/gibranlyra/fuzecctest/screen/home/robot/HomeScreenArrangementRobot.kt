package com.gibranlyra.fuzecctest.screen.home.robot

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import com.gibranlyra.fuzecctest.domain.model.MatchData
import com.gibranlyra.fuzecctest.screen.home.HomeScreenKtTest
import com.gibranlyra.fuzecctest.ui.screen.home.HomeScreen
import com.gibranlyra.fuzecctest.ui.screen.home.HomeUiState
import com.gibranlyra.fuzecctest.ui.theme.FuzeccTheme

internal class HomeScreenArrangementRobot(private val rule: ComposeContentTestRule) {
    fun setContent(
        uiState: HomeUiState,
        onMatchClicked: (MatchData) -> Unit = {},
        onRetryButtonClicked: () -> Unit = {},
        onRefreshMatches: () -> Unit = {},
    ) {
        rule.launch {
            HomeScreen(
                uiState = uiState,
                onMatchClicked = onMatchClicked,
                onRetryButtonClicked = onRetryButtonClicked,
                onRefreshMatches = onRefreshMatches
            )
        }
    }

    private fun ComposeContentTestRule.launch(content: @Composable () -> Unit) {
        setContent {
            FuzeccTheme(content = content)
        }
    }
}

internal fun HomeScreenKtTest.arrange(block: HomeScreenArrangementRobot.() -> Unit) =
    robotFactory.arrangementRobot.apply(block)
