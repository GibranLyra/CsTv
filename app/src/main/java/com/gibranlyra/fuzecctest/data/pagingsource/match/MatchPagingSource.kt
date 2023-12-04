package com.gibranlyra.fuzecctest.data.pagingsource.match

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gibranlyra.fuzecctest.data.ext.toMatchData
import com.gibranlyra.fuzecctest.data.match.MatchDataSource
import com.gibranlyra.fuzecctest.domain.model.MatchData
import org.threeten.bp.ZoneOffset
import org.threeten.bp.ZonedDateTime
import javax.inject.Inject

private const val STARTING_PAGE =
    2514 // FIXME When the correct sort and filter is done on the api we can revert it to 0
private const val PAGE_SIZE = 20

private const val TAG: String = "MatchPagingSource"

internal class MatchPagingSource @Inject constructor(private val matchDataSource: MatchDataSource) :
    PagingSource<Int, MatchData>() {

    override fun getRefreshKey(state: PagingState<Int, MatchData>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MatchData> {
        return try {
            val pageNumber = params.key ?: STARTING_PAGE

            val response = matchDataSource.getMatches(PAGE_SIZE, pageNumber)
                .filter { match -> isInTheFuture(match.beginAt) }

            LoadResult.Page(
                data = response.map { match -> match.toMatchData() },
                prevKey = null,
                nextKey = if (response.isEmpty()) null else pageNumber.plus(1),
            )

        } catch (e: Exception) {
            Log.e(TAG, "load: ${e.message}", e)
            LoadResult.Error(e)
        }
    }

    private fun isInTheFuture(beginAt: String?): Boolean {
        return if (beginAt.isNullOrEmpty()) {
            false
        } else {
            val currentUtcDateTime = ZonedDateTime.now(ZoneOffset.UTC)
            val givenUtcDateTime = ZonedDateTime.parse(beginAt)

            val isInTheFuture = !givenUtcDateTime.isBefore(currentUtcDateTime)
            println("IsInTheFuture: $isInTheFuture")
            isInTheFuture
        }
    }
}