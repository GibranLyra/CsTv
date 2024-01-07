package com.gibranlyra.cstv.domain.matchdetails

import android.util.Log
import com.gibranlyra.cstv.data.entity.Result
import com.gibranlyra.cstv.data.team.TeamRepository
import com.gibranlyra.cstv.domain.model.MatchDetailsTeamsData
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
                            Result.Success(
                                MatchDetailsTeamsData(
                                    team1 = teamDataResult.data.find { teamData -> teamData.teamId == team1Id }
                                        ?: throw IllegalStateException("TeamDataResult doesn't contains $team1Id"),
                                    team2 = teamDataResult.data.find { teamData -> teamData.teamId == team2Id }
                                        ?: throw IllegalStateException("TeamDataResult doesn't contains $team2Id"),
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
