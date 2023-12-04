package com.gibranlyra.fuzecctest.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.gibranlyra.fuzecctest.R
import com.gibranlyra.fuzecctest.ui.ext.addTransparentBackground
import com.gibranlyra.fuzecctest.ui.theme.FuzeccTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTopBar(
    title: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    transparentActions: Boolean = false,
) {
    TopAppBar(
        title = {
            FuzeText(
                modifier = Modifier
                    .then(
                        if (transparentActions) Modifier
                            .addTransparentBackground(CircleShape)
                            .padding(dimensionResource(id = R.dimen.padding_medium))
                        else Modifier
                    ),
                text = title,
                style = FuzeTextStyle.LARGE_SUBTLE,
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(Color.Transparent),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(
                    modifier = Modifier
                        .then(
                            if (transparentActions) Modifier
                                .padding(dimensionResource(id = R.dimen.padding_small))
                                .addTransparentBackground(CircleShape)
                            else Modifier
                        ),
                    onClick = navigateUp
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SimpleTopBarPreview() {
    FuzeccTheme {
        SimpleTopBar(
            title = "Simple TopBar",
            canNavigateBack = true,
            navigateUp = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SimpleTopBarTransparentPreview() {
    FuzeccTheme {
        SimpleTopBar(
            title = "Simple TopBar Transparent",
            canNavigateBack = true,
            navigateUp = {},
            transparentActions = true,
        )
    }
}