package com.gibranlyra.fuzecctest.domain.home

import com.gibranlyra.fuzecctest.data.entity.Match
import com.gibranlyra.fuzecctest.data.entity.Result
import com.gibranlyra.fuzecctest.data.match.MatchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class HomeUseCase @Inject constructor(private val matchRepository: MatchRepository) {

    operator fun invoke(): Flow<Result<List<Match>>> = matchRepository.getMatches()
}