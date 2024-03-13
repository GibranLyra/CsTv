package com.gibranlyra.cstv.domain.home

import androidx.paging.PagingData
import com.gibranlyra.cstv.data.match.MatchRepository
import com.gibranlyra.cstv.domain.model.MatchData
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

internal class HomeUseCase
@Inject
constructor(private val matchRepository: MatchRepository) {
    operator fun invoke(): Flow<PagingData<MatchData>> = matchRepository.getMatches()
}
