package com.gibranlyra.fuzecctest.data.match

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.gibranlyra.fuzecctest.data.di.MatchPager
import com.gibranlyra.fuzecctest.domain.model.MatchData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

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
}
