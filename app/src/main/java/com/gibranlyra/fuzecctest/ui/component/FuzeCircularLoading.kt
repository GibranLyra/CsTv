package com.gibranlyra.fuzecctest.ui.component

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag

internal const val SAMPLE_CIRCULAR_LOADING_TEST_TAG = "SAMPLE_CIRCULAR_LOADING_TEST_TAG"

@Composable
fun FuzeCircularLoading(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.surfaceVariant,
    trackColor: Color = MaterialTheme.colorScheme.secondary,
) {
    CircularProgressIndicator(
        modifier = modifier.testTag(SAMPLE_CIRCULAR_LOADING_TEST_TAG),
        color = color,
        trackColor = trackColor,
    )
}