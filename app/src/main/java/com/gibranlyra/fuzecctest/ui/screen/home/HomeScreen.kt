package com.gibranlyra.fuzecctest.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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


@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun HomeScreen(
    uiState: HomeUiState,
    modifier: Modifier = Modifier,
    onToolbarComposition: (ToolbarData<Any>) -> Unit = { },
    onRetryButtonClicked: () -> Unit = {},
    onRefreshMatches: () -> Unit = {},
    onMatchClicked: (MatchData) -> Unit = {},
) {
    onToolbarComposition(ToolbarData(stringResource(id = R.string.home_screen_title)))

    val pullRefreshState = rememberPullRefreshState(
        refreshing = uiState.isMatchRefreshing.isRefreshing,
        onRefresh = { onRefreshMatches() }
    )

    Box(Modifier.pullRefresh(pullRefreshState)) {
        MatchesList(modifier, uiState, onMatchClicked, onRetryButtonClicked)

        PullRefreshIndicator(
            uiState.isMatchRefreshing.isRefreshing,
            pullRefreshState,
            Modifier.align(Alignment.TopCenter)
        )
    }

}

@Composable
private fun MatchesList(
    modifier: Modifier,
    uiState: HomeUiState,
    onMatchClicked: (MatchData) -> Unit,
    onRetryButtonClicked: () -> Unit
) {
    val matches = uiState.matchesPagingState.collectAsLazyPagingItems()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(R.dimen.padding_large)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_large)),
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

private fun LazyListScope.matchListItemHandler(
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
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
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
