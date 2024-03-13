package com.gibranlyra.cstv.screen.home.robot

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.gibranlyra.cstv.domain.model.MatchData
import com.gibranlyra.cstv.screen.home.HomeScreenKtTest
import com.gibranlyra.cstv.screen.home.HomeScreenTestUtils
import com.gibranlyra.cstv.ui.component.HOME_CIRCULAR_LOADING_TEST_TAG
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue

internal class HomeScreenAssertionRobot(
    private val rule: ComposeContentTestRule,
    private val testUtils: HomeScreenTestUtils,
) {
    fun textIsDisplayed(text: String) = apply {
        rule.onNodeWithText(text).assertIsDisplayed()
    }

    fun tagIsDisplayed(tag: String) = apply {
        rule.onNodeWithTag(tag).assertIsDisplayed()
    }

    fun loadingIsDisplayed() {
        rule.onNodeWithTag(HOME_CIRCULAR_LOADING_TEST_TAG)
            .assertIsDisplayed()
    }

    fun matchWasClicked(match: MatchData) {
        assertEquals(match, testUtils.matchClicked)
    }

    fun retryButtonWasClicked() {
        assertTrue(testUtils.retryButtonClicked)
    }

    fun matchesRefreshed() {
        assertTrue(testUtils.matchesRefreshed)
    }
}

internal fun HomeScreenKtTest.assert(block: HomeScreenAssertionRobot.() -> Unit) =
    robotFactory.assertionRobot.apply(block)
