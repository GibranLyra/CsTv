package com.gibranlyra.cstv.data.match

import com.gibranlyra.cstv.data.entity.Match

interface MatchDataSource {
    suspend fun getMatches(pageSize: Int, pageNumber: Int, filter: String): List<Match>
}
