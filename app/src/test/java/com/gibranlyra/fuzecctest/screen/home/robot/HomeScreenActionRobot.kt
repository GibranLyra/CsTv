package com.gibranlyra.fuzecctest.screen.home.robot

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeDown
import com.gibranlyra.fuzecctest.screen.home.HomeScreenKtTest
import com.gibranlyra.fuzecctest.ui.screen.home.REFRESH_MATCHES_TEST_TAG

internal class HomeScreenActionRobot(private val rule: ComposeTestRule) {

    fun performClickOn(text: String) {
        rule.onNodeWithText(text)
            .performClick()
    }

    fun performClickOnTag(testTag: String) {
        rule.onNodeWithTag(testTag)
            .performClick()
    }

    fun performSwipeToRefresh() {
        rule.onNodeWithTag(REFRESH_MATCHES_TEST_TAG)
            .performTouchInput { swipeDown() }
    }
}

internal fun HomeScreenKtTest.act(block: HomeScreenActionRobot.() -> Unit) =
    robotFactory.actionRobot.apply(block)
