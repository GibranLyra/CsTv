package com.gibranlyra.cstv.screen.home.robot

import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import com.gibranlyra.cstv.data.ext.toMatchData
import com.gibranlyra.cstv.domain.model.MatchData
import com.gibranlyra.cstv.stub.MatchListStub
import com.gibranlyra.cstv.ui.screen.home.HomeUiState
import kotlinx.coroutines.flow.flowOf
import java.io.IOException

internal class HomeScreenTestData {
    fun getLoadingState(): HomeUiState = HomeUiState()

    fun getLoadedState(match: List<MatchData>): HomeUiState =
        HomeUiState(flowOf(PagingData.from(data = match)))

    fun getErrorState(): HomeUiState = HomeUiState(
        flowOf(
            PagingData.from(
                data = listOf(),
                sourceLoadStates = getErrorLoadState()
            )
        )
    )

    fun getMatches() = MatchListStub().map { it.toMatchData() }

    private fun getErrorLoadState() = LoadStates(
        refresh = LoadState.Error(IOException("Error")),
        append = LoadState.NotLoading(true),
        prepend = LoadState.NotLoading(true),
    )
}
