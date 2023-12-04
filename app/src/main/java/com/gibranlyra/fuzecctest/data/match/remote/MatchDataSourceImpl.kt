package com.gibranlyra.pandaservice.match.remote

import com.gibranlyra.fuzecctest.data.entity.Match
import com.gibranlyra.fuzecctest.data.match.MatchDataSource
import com.gibranlyra.fuzecctest.data.match.remote.MatchApi
import javax.inject.Inject

internal class MatchDataSourceImpl @Inject constructor(private val matchApi: MatchApi):
    MatchDataSource {
    override suspend fun getMatches(): List<Match> = matchApi.getMatches()
}