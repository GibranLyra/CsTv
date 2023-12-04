package com.gibranlyra.fuzecctest.data.match.remote

import com.gibranlyra.fuzecctest.data.entity.Match
import retrofit2.http.GET

internal interface MatchApi {

    @GET("/matches")
    suspend fun getMatches(): List<Match>
}