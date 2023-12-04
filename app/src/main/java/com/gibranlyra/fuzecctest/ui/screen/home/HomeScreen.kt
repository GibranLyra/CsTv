package com.gibranlyra.fuzecctest.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.gibranlyra.fuzecctest.R
import com.gibranlyra.fuzecctest.domain.model.MatchData
import com.gibranlyra.fuzecctest.domain.model.ToolbarData
import com.gibranlyra.fuzecctest.ui.component.FuzeCircularLoading
import com.gibranlyra.fuzecctest.ui.component.MatchItem
import com.gibranlyra.fuzecctest.ui.component.RetryButton
import com.gibranlyra.fuzecctest.ui.component.stubMatch
import com.gibranlyra.fuzecctest.ui.theme.FuzeccTheme
import kotlinx.coroutines.flow.flowOf
import java.io.IOException


@Composable
internal fun HomeScreen(
    uiState: HomeUiState,
    modifier: Modifier = Modifier,
    onToolbarComposition: (ToolbarData<Any>) -> Unit = { },
    onRetryButtonClicked: () -> Unit = {},
    onMatchClicked: (MatchData) -> Unit = {},
) {
    onToolbarComposition(ToolbarData(stringResource(id = R.string.home_screen_title)))

    val matches = uiState.matchesPagingState.collectAsLazyPagingItems()

    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(R.dimen.padding_large)),
        columns = GridCells.Adaptive(minSize = dimensionResource(id = R.dimen.large_match_image_width)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_large)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_large)),
        contentPadding = PaddingValues(vertical = dimensionResource(R.dimen.padding_large)),
    ) {
        items(
            count = matches.itemCount,
            key = matches.itemKey { it.id },
        ) { index ->
            matches[index]?.let { match -> MatchItem(match = match, onClick = onMatchClicked) }
        }

        matchListItemHandler(
            loadState = matches.loadState.refresh,
            onRetryButtonClicked = onRetryButtonClicked
        )
        matchListItemHandler(
            loadState = matches.loadState.append,
            onRetryButtonClicked = onRetryButtonClicked
        )
    }
}

private fun LazyGridScope.matchListItemHandler(
    loadState: LoadState,
    onRetryButtonClicked: () -> Unit
) {
    when (loadState) {
        is LoadState.Error -> item { HomeErrorView(onRetryButtonClicked = onRetryButtonClicked) }
        is LoadState.Loading -> item { HomeLoadingView() }
        is LoadState.NotLoading -> Unit
    }
}

@Composable
private fun HomeLoadingView(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        FuzeCircularLoading()
    }
}

@Composable
fun HomeErrorView(modifier: Modifier = Modifier, onRetryButtonClicked: () -> Unit) {
    Column(modifier = modifier) {
        RetryButton<Nothing>(
            message = stringResource(id = R.string.retry),
            onClick = { onRetryButtonClicked.invoke() })
    }
}

@Preview(showBackground = true)
@Composable
internal fun HomePreview() {
    FuzeccTheme {
        val successPagingData = PagingData.from(
            data = stubMatches(), sourceLoadStates = LoadStates(
                refresh = LoadState.NotLoading(true),
                append = LoadState.NotLoading(true),
                prepend = LoadState.NotLoading(true),
            )
        )
        HomeScreen(
            HomeUiState(flowOf(successPagingData)),
        )
    }
}

@Preview(showBackground = true)
@Composable
internal fun HomeLoadingPreview() {
    FuzeccTheme {
        HomeScreen(HomeUiState())
    }
}

@Preview(showBackground = true)
@Composable
internal fun HomeErrorPreview() {
    FuzeccTheme {
        val errorPagingData = PagingData.from(
            data = listOf<MatchData>(), sourceLoadStates = LoadStates(
                refresh = LoadState.Error(IOException("Error")),
                append = LoadState.NotLoading(true),
                prepend = LoadState.NotLoading(true),
            )
        )
        HomeScreen(
            HomeUiState(flowOf(errorPagingData))
        )
    }
}

private fun stubMatches(): MutableList<MatchData> {
    val matches = mutableListOf<MatchData>()
    repeat(8) { matches.add(stubMatch(it)) }
    return matches
}
