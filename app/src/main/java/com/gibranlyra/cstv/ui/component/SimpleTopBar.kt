package com.gibranlyra.cstv.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.gibranlyra.cstv.R
import com.gibranlyra.cstv.ui.theme.CsTvTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTopBar(
    title: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier,
        title = {
            CsTvText(
                modifier = Modifier,
                text = title,
                style = CsTvTextStyle.LARGE,
                styleOverride = TextStyle(color = MaterialTheme.colorScheme.onPrimary),
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(Color.Transparent),
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(
                    modifier = Modifier,
                    onClick = navigateUp,
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button),
                        tint = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun SimpleTopBarPreview() {
    CsTvTheme {
        SimpleTopBar(
            title = "Simple TopBar",
            canNavigateBack = true,
            navigateUp = {},
        )
    }
}
