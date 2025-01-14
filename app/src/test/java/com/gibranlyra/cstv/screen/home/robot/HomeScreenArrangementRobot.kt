package com.gibranlyra.cstv.screen.home.robot

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import com.gibranlyra.cstv.domain.model.MatchData
import com.gibranlyra.cstv.screen.home.HomeScreenKtTest
import com.gibranlyra.cstv.ui.screen.home.HomeScreen
import com.gibranlyra.cstv.ui.screen.home.HomeUiState
import com.gibranlyra.cstv.ui.theme.CsTvTheme

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
                onRefreshMatches = onRefreshMatches,
            )
        }
    }

    private fun ComposeContentTestRule.launch(content: @Composable () -> Unit) {
        setContent {
            CsTvTheme(content = content)
        }
    }
}

internal fun HomeScreenKtTest.arrange(block: HomeScreenArrangementRobot.() -> Unit) =
    robotFactory.arrangementRobot.apply(block)
