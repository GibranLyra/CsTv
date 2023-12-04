package com.gibranlyra.fuzecctest.data.match.remote

import com.gibranlyra.fuzecctest.data.entity.Match
import com.gibranlyra.fuzecctest.data.match.MatchDataSource
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject

internal class MatchDataSourceImpl @Inject constructor(private val matchApi: MatchApi) :
    MatchDataSource {
    override suspend fun getMatches(pageSize: Int, pageNumber: Int): List<Match> {
//        val filter = getCurrentUtcDateTime()
        return matchApi.getMatches(pageSize = pageSize, pageNumber = pageNumber, "")
    }

    private fun getCurrentUtcDateTime(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        return dateFormat.format(Date())
    }
}