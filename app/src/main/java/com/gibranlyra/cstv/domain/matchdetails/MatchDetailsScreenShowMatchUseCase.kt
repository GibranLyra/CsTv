package com.gibranlyra.cstv.domain.matchdetails

import com.gibranlyra.cstv.data.entity.Result
import com.gibranlyra.cstv.data.match.MatchRepository
import com.gibranlyra.cstv.domain.model.MatchData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class MatchDetailsScreenShowMatchUseCase
    @Inject
    constructor(private val matchRepository: MatchRepository) {
        operator fun invoke(matchId: Int): Flow<Result<MatchData>> = matchRepository.getMatch(matchId)
    }
