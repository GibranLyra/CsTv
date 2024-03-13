package com.gibranlyra.cstv.data.team.remote

import com.gibranlyra.cstv.data.entity.Team
import retrofit2.http.GET
import retrofit2.http.Query

internal interface TeamApi {
    @GET("/csgo/teams")
    suspend fun getTeams(@Query("filter[id]") team1Id: String): List<Team>
}
