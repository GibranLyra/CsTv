package com.gibranlyra.fuzecctest.ui.screen.matchdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.gibranlyra.fuzecctest.R
import com.gibranlyra.fuzecctest.data.entity.Result
import com.gibranlyra.fuzecctest.domain.matchdetails.MatchDetailsScreenShowMatchUseCase
import com.gibranlyra.fuzecctest.domain.matchdetails.MatchDetailsScreenTeamUseCase
import com.gibranlyra.fuzecctest.domain.model.MatchData
import com.gibranlyra.fuzecctest.domain.model.MatchDetailsTeamsData
import com.gibranlyra.fuzecctest.domain.model.State
import com.gibranlyra.fuzecctest.util.DispatcherProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class MatchDetailsViewModel @AssistedInject constructor(
    @Assisted matchId: Int,
    private val matchDetailsScreenShowMatchUseCase: MatchDetailsScreenShowMatchUseCase,
    private val matchDetailsScreenTeamUseCase: MatchDetailsScreenTeamUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow(MatchDetailsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadScreen(matchId)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun loadScreen(matchId: Int) {
        viewModelScope.launch(dispatcherProvider.default()) {
            val matchFlow = matchDetailsScreenShowMatchUseCase(matchId)

            matchFlow.flatMapConcat { matchValue ->
                when (matchValue) {
                    is Result.Error -> flowOf(Result.Error(matchValue.errorMessage))
                        .map { teamValue -> matchValue to teamValue }

                    Result.Loading -> flowOf(Result.Loading)
                        .map { teamValue -> matchValue to teamValue }

                    is Result.Success -> matchDetailsScreenTeamUseCase(
                        matchValue.data.team1Id,
                        matchValue.data.team2Id
                    )
                        .map { teamValue -> matchValue to teamValue }
                }
            }.collectLatest { (matchValue, teamValue) ->
                when (matchValue) {
                    is Result.Error -> updateMatchStateToError()
                    Result.Loading -> updateMatchStateToLoading()
                    is Result.Success -> updateMatchStateToLoaded(matchValue.data)
                }

                when (teamValue) {
                    is Result.Error -> updateTeamsStateToError()
                    Result.Loading -> updateTeamsStateToLoading()
                    is Result.Success -> updateTeamsStateToLoaded(teamValue.data)
                }
            }
        }
    }

    private fun updateMatchStateToLoading() {
        _uiState.update { currentState ->
            currentState.copy(matchState = State.Loading())
        }
    }

    private fun updateMatchStateToLoaded(match: MatchData) {
        _uiState.update { currentState ->
            currentState.copy(matchState = State.Loaded(match))
        }
    }

    private fun updateMatchStateToError() {
        _uiState.update { currentState ->
            currentState.copy(matchState = State.Error(R.string.match_detail_error_team_cannot_be_loaded))
        }
    }

    private fun updateTeamsStateToLoading() {
        _uiState.update { currentState ->
            currentState.copy(teamsState = State.Loading())
        }
    }

    private fun updateTeamsStateToLoaded(teamsData: MatchDetailsTeamsData) {
        _uiState.update { currentState ->
            currentState.copy(teamsState = State.Loaded(teamsData))
        }
    }

    private fun updateTeamsStateToError() {
        _uiState.update { currentState ->
            currentState.copy(matchState = State.Error(R.string.match_detail_error_team_cannot_be_loaded))
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted matchId: Int): MatchDetailsViewModel
    }

    companion object {
        fun provideFactory(factory: Factory, matchId: Int): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return factory.create(matchId) as T
                }
            }
    }
}