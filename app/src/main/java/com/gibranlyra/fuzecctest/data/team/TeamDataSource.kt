package com.gibranlyra.fuzecctest.data.team

import com.gibranlyra.fuzecctest.data.entity.Team

interface TeamDataSource {

    suspend fun getTeams(team1Id: Int, team2Id: Int): List<Team>
}