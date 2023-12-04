package com.gibranlyra.fuzecctest.data.match.remote

import com.gibranlyra.fuzecctest.data.entity.Match
import retrofit2.http.GET
import retrofit2.http.Query

internal interface MatchApi {

    @GET("/csgo/matches")
    suspend fun getMatches(
        @Query("page[size]") pageSize: Int,
        @Query("page[number]") pageNumber: Int,
        @Query("filter[begin_at]") beginAt: String,
        @Query("sort") sort: String = "begin_at",
    ): List<Match>
}