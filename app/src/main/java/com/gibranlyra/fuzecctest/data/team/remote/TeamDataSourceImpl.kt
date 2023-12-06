package com.gibranlyra.fuzecctest.data.team.remote

import com.gibranlyra.fuzecctest.data.team.TeamDataSource
import javax.inject.Inject

internal class TeamDataSourceImpl @Inject constructor(private val teamApi: TeamApi) :
    TeamDataSource {
    override suspend fun getTeams(team1Id: Int, team2Id: Int) =
        teamApi.getTeams(team1Id = "${team1Id},${team2Id}")
}
