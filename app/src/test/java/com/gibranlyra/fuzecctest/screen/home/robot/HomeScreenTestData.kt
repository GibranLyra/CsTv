package com.gibranlyra.fuzecctest.screen.home.robot

import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import com.gibranlyra.fuzecctest.data.ext.toMatchData
import com.gibranlyra.fuzecctest.domain.model.MatchData
import com.gibranlyra.fuzecctest.stub.MatchListStub
import com.gibranlyra.fuzecctest.ui.screen.home.HomeUiState
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
