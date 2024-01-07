package com.gibranlyra.cstv.ui.component

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag

internal const val HOME_CIRCULAR_LOADING_TEST_TAG = "HOME_CIRCULAR_LOADING_TEST_TAG"

@Composable
fun CsTvCircularLoading(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.surfaceVariant,
    trackColor: Color = MaterialTheme.colorScheme.secondary,
) {
    CircularProgressIndicator(
        modifier = modifier.testTag(HOME_CIRCULAR_LOADING_TEST_TAG),
        color = color,
        trackColor = trackColor,
    )
}