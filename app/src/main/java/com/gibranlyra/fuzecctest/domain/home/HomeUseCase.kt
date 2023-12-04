package com.gibranlyra.fuzecctest.domain.home

import androidx.paging.PagingData
import com.gibranlyra.fuzecctest.data.match.MatchRepository
import com.gibranlyra.fuzecctest.domain.model.MatchData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class HomeUseCase @Inject constructor(private val matchRepository: MatchRepository) {

    operator fun invoke(): Flow<PagingData<MatchData>> = matchRepository.getMatches()
}