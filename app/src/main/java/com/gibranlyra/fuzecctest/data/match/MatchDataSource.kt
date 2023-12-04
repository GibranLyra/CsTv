package com.gibranlyra.fuzecctest.data.match

import com.gibranlyra.fuzecctest.data.entity.Match

interface MatchDataSource {

    suspend fun getMatches(pageSize: Int, pageNumber: Int): List<Match>
}