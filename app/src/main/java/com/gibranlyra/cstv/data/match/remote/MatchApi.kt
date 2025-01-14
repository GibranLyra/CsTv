package com.gibranlyra.cstv.data.match.remote

import com.gibranlyra.cstv.data.entity.Match
import retrofit2.http.GET
import retrofit2.http.Query

internal interface MatchApi {
    @GET("/csgo/matches")
    suspend fun getMatches(
        @Query("page[size]") pageSize: Int,
        @Query("page[number]") pageNumber: Int,
        @Query("filter[begin_at]") beginAt: String,
        @Query("filter[status]") status: String = "running,not_started,finished",
        @Query("sort") sort: String = "begin_at",
    ): List<Match>
}
