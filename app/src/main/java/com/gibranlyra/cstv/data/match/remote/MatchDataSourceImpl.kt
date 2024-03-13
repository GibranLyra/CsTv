package com.gibranlyra.cstv.data.match.remote

import com.gibranlyra.cstv.data.entity.Match
import com.gibranlyra.cstv.data.match.MatchDataSource
import javax.inject.Inject

internal class MatchDataSourceImpl
    @Inject
    constructor(private val matchApi: MatchApi) :
    MatchDataSource {
        override suspend fun getMatches(
            pageSize: Int,
            pageNumber: Int,
            filter: String,
        ): List<Match> {
            return matchApi.getMatches(pageSize = pageSize, pageNumber = pageNumber, filter)
        }
    }
