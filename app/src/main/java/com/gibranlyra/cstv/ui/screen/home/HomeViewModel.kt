package com.gibranlyra.cstv.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gibranlyra.cstv.domain.home.HomeUseCase
import com.gibranlyra.cstv.domain.model.MatchData
import com.gibranlyra.cstv.util.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase,
    private val dispatcher: DispatcherProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        getMatches()
    }

    fun getMatches() {
        val matchesState = homeUseCase().cachedIn(viewModelScope)
        updateMatchesPagingDataState(matchesState)
    }

    private fun updateMatchesPagingDataState(matchesState: Flow<PagingData<MatchData>>) {
        _uiState.update { currentState -> currentState.copy(matchesPagingState = matchesState) }
    }

    fun navigateToMatchDetailsScreen(matchId: Int, team1Id: Int, team2Id: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                navigateToMatchDetailScreen = HomeEvents.NavigateToMatchDetailsScreen(
                    matchId,
                    team1Id,
                    team2Id
                )
            )
        }
    }

    fun navigatedMatchDetailsScreen() {
        _uiState.update { currentState ->
            currentState.copy(navigateToMatchDetailScreen = null)
        }
    }
}
