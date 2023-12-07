package com.gibranlyra.fuzecctest.data.pagingsource.match

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gibranlyra.fuzecctest.data.ext.toMatchData
import com.gibranlyra.fuzecctest.data.match.MatchDataSource
import com.gibranlyra.fuzecctest.domain.model.MatchData
import com.gibranlyra.fuzecctest.util.getCurrentDateTime
import javax.inject.Inject

private const val STARTING_PAGE = 1
private const val PAGE_SIZE = 50

private const val TAG: String = "MatchPagingSource"

internal class MatchPagingSource @Inject constructor(private val matchDataSource: MatchDataSource) :
    PagingSource<Int, MatchData>() {

    private var plusDays = 0L

    override fun getRefreshKey(state: PagingState<Int, MatchData>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MatchData> {
        return try {
            val pageNumber = params.key ?: STARTING_PAGE

            val response =
                matchDataSource.getMatches(PAGE_SIZE, pageNumber, getCurrentDateTime(plusDays))
                    .asSequence()
                    .sortedBy { match -> match.status.ordinal }
                    .map { match -> match.toMatchData() }
                    .toList()

            val nextKey = getNextKey(response, pageNumber)

            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = nextKey,
            )

        } catch (e: Exception) {
            Log.e(TAG, "load: ${e.message}", e)
            LoadResult.Error(e)
        }
    }

    private fun getNextKey(response: List<MatchData>, pageNumber: Int): Int {
        if (response.isEmpty()) {
            plusDays++
        }
        return if (response.isEmpty()) STARTING_PAGE else pageNumber.plus(1)
    }
}