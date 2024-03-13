package com.gibranlyra.cstv.data.team

import com.gibranlyra.cstv.data.entity.Team

interface TeamDataSource {
    suspend fun getTeams(team1Id: Int, team2Id: Int): List<Team>
}
