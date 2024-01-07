package com.gibranlyra.cstv.data.team

import com.gibranlyra.cstv.data.entity.Result
import com.gibranlyra.cstv.data.entity.Team
import com.gibranlyra.cstv.data.ext.toTeamData
import com.gibranlyra.cstv.domain.model.TeamData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TeamRepository @Inject constructor(private val teamDataSource: TeamDataSource) {

    private val teams = emptyMap<Int, Team>().toMutableMap()

    fun getTeams(team1Id: Int, team2Id: Int): Flow<Result<List<TeamData>>> =
        flow {
            try {
                teams.getOrElse(team1Id) {
                    val teamDataList = teamDataSource.getTeams(team1Id = team1Id, team2Id = team2Id)
                        .map { team ->
                            teams[team.id] = team
                            team.toTeamData()
                        }
                    emit(Result.Success(teamDataList))
                }
            } catch (e: HttpException) {
                emit(
                    Result.Error(e.localizedMessage ?: "Unexpected error")
                )
            } catch (e: IOException) {
                emit(Result.Error("No internet connection"))
            }
        }
}
