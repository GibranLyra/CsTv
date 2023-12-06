package com.gibranlyra.fuzecctest.domain.matchdetails

import android.util.Log
import com.gibranlyra.fuzecctest.data.entity.Result
import com.gibranlyra.fuzecctest.data.team.TeamRepository
import com.gibranlyra.fuzecctest.domain.model.MatchDetailsTeamsData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val TAG = "MatchDetailsScreenTeamUseCase"

internal class MatchDetailsScreenTeamUseCase @Inject constructor(private val teamRepository: TeamRepository) {
    operator fun invoke(team1Id: Int, team2Id: Int): Flow<Result<MatchDetailsTeamsData>> =
        teamRepository.getTeams(team1Id, team2Id)
            .map { teamDataResult ->
                when (teamDataResult) {
                    is Result.Error -> Result.Error(teamDataResult.errorMessage)
                    Result.Loading -> Result.Loading
                    is Result.Success -> {
                        try {
                            check(teamDataResult.data.size == 2) { "TeamDataResult should have two teams." }
                            Result.Success(
                                MatchDetailsTeamsData(
                                    teamDataResult.data.first(),
                                    teamDataResult.data[1]
                                )
                            )
                        } catch (e: IllegalStateException) {
                            Log.e(TAG, "invoke: ${e.message}", e)
                            Result.Error(e.message.orEmpty())
                        }
                    }
                }
            }
}
