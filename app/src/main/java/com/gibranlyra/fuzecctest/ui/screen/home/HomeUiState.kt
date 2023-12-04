package com.gibranlyra.fuzecctest.ui.screen.home

import androidx.paging.PagingData
import com.gibranlyra.fuzecctest.domain.model.MatchData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal data class HomeUiState(
    val matchesPagingState: Flow<PagingData<MatchData>> = flowOf(),
    val navigateToMatchDetailScreen: HomeEvents.NavigateToMatchDetailsScreen? = null
)

internal sealed interface HomeEvents {
    data class NavigateToMatchDetailsScreen(val matchId: Int) : HomeEvents
}
