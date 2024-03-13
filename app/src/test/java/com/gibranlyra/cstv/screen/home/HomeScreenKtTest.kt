package com.gibranlyra.cstv.screen.home

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import com.gibranlyra.cstv.screen.home.robot.HomeScreenActionRobot
import com.gibranlyra.cstv.screen.home.robot.HomeScreenArrangementRobot
import com.gibranlyra.cstv.screen.home.robot.HomeScreenAssertionRobot
import com.gibranlyra.cstv.screen.home.robot.HomeScreenTestData
import com.gibranlyra.cstv.screen.home.robot.act
import com.gibranlyra.cstv.screen.home.robot.arrange
import com.gibranlyra.cstv.screen.home.robot.assert
import com.gibranlyra.cstv.ui.component.RETRY_BUTTON_TEST_TAG
import com.gibranlyra.cstv.ui.screen.home.MATCH_TEST_TAG
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(
    instrumentedPackages = ["androidx.loader.content"],
    qualifiers = "xlarge",
)
internal class HomeScreenKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val homeScreenTestUtils = HomeScreenTestUtils()
    val robotFactory = RobotFactory(composeTestRule, homeScreenTestUtils)
    private val homeScreenData = HomeScreenTestData()

    @Test
    fun `HomeScreen should show loading when state is Loading`() {
        arrange {
            setContent(uiState = homeScreenData.getLoadingState())
        }

        assert {
            loadingIsDisplayed()
        }
    }

    @Test
    fun `HomeScreen should show matches when state is Loaded`() {
        val matches = homeScreenData.getMatches()

        arrange {
            setContent(uiState = homeScreenData.getLoadedState(matches))
        }
        assert {
            tagIsDisplayed("$MATCH_TEST_TAG ${matches.first().id}")
        }
    }

    @Test
    fun `when Match is clicked should invoke onMatchClick`() {
        val matches = homeScreenData.getMatches()
        val homeState = HomeScreenTestData().getLoadedState(matches)
        val selectedMatch = matches.first()

        arrange {
            setContent(
                uiState = homeState,
                onMatchClicked = homeScreenTestUtils.onMatchClicked(),
            )
        }

        act {
            performClickOnTag("$MATCH_TEST_TAG ${selectedMatch.id}")
        }

        assert {
            matchWasClicked(selectedMatch)
        }
    }

    @Test
    fun `when HomeScreen has Error should show ErrorView`() {
        val homeState = HomeScreenTestData().getErrorState()

        arrange {
            setContent(uiState = homeState)
        }

        assert {
            textIsDisplayed("Error loading home information.")
        }
    }

    @Test
    fun `when HomeScreen hasError and user clicks retryButton should invoke onRetryClick`() {
        val homeState = HomeScreenTestData().getErrorState()

        arrange {
            setContent(
                uiState = homeState,
                onRetryButtonClicked = homeScreenTestUtils.onRetryButtonClicked(),
            )
        }

        act {
            performClickOnTag(RETRY_BUTTON_TEST_TAG)
        }

        assert {
            retryButtonWasClicked()
        }
    }

    @Test
    fun `when HomeScreen sends pullToRefresh action should invoke onRefreshMatches`() {
        val homeState = HomeScreenTestData().getLoadedState(listOf())

        arrange {
            setContent(
                uiState = homeState,
                onRefreshMatches = homeScreenTestUtils.onRefreshMatches(),
            )
        }

        act {
            performSwipeToRefresh()
        }

        assert {
            matchesRefreshed()
        }
    }
}

internal class RobotFactory(
    rule: ComposeContentTestRule,
    homeScreenTestUtils: HomeScreenTestUtils,
) {
    val arrangementRobot by lazy { HomeScreenArrangementRobot(rule = rule) }
    val assertionRobot by lazy {
        HomeScreenAssertionRobot(rule = rule, testUtils = homeScreenTestUtils)
    }
    val actionRobot by lazy { HomeScreenActionRobot(rule = rule) }
}
