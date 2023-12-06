package com.gibranlyra.fuzecctest.domain.matchdetails

import com.gibranlyra.fuzecctest.data.entity.Result
import com.gibranlyra.fuzecctest.data.match.MatchRepository
import com.gibranlyra.fuzecctest.domain.model.MatchData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class MatchDetailsScreenShowMatchUseCase @Inject constructor(private val matchRepository: MatchRepository) {

    operator fun invoke(matchId: Int): Flow<Result<MatchData>> = matchRepository.getMatch(matchId)
}