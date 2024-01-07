package com.gibranlyra.cstv.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.gibranlyra.cstv.R
import com.gibranlyra.cstv.ui.theme.CsTvTheme

internal const val RETRY_BUTTON_TEST_TAG = "RETRY_BUTTON_TEST_TAG"

@Composable
internal fun <T> RetryButton(
    modifier: Modifier = Modifier,
    data: T? = null,
    message: String,
    onClick: (T?) -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CsTvText(
            text = message,
            textAlign = TextAlign.Center
        )

        Button(modifier = Modifier.testTag(RETRY_BUTTON_TEST_TAG), onClick = { onClick(data) }) {
            CsTvText(
                text = stringResource(id = R.string.retry),
                style = CsTvTextStyle.BASE_SUBTLE
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RetryButtonPreview() {
    CsTvTheme {
        RetryButton<Nothing>(message = "A really long error message saying what was happened.")
    }
}
