package com.gibranlyra.cstv.data.match

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.gibranlyra.cstv.data.di.module.MatchPager
import com.gibranlyra.cstv.data.entity.Result
import com.gibranlyra.cstv.domain.model.MatchData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG: String = "MatchRepository"

@Singleton
class MatchRepository @Inject constructor(
    @MatchPager private val pager: Pager<Int, MatchData>
) {

    private val matches = emptyMap<Int, MatchData>().toMutableMap()

    fun getMatches(): Flow<PagingData<MatchData>> {
        return pager.flow
            .map { pagingData ->
                pagingData.map { match ->
                    matches[match.id] = match
                    match
                }
            }
    }

    fun getMatch(matchId: Int): Flow<Result<MatchData>> {
        return flow {
            try {
                emit(Result.Success(matches.getValue(matchId)))
            } catch (e: NoSuchElementException) {
                Log.e(TAG, "getMatch: Match $matchId not found.", e)
                emit(Result.Error("Match $matchId not found."))
            }
        }
    }
}
