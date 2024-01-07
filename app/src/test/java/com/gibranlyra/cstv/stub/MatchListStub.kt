package com.gibranlyra.cstv.stub

import com.gibranlyra.cstv.data.entity.Match

object MatchListStub {
    operator fun invoke(): MutableList<Match> {
        val matches = mutableListOf<Match>()
        repeat(5) {
            matches.add(Match(id = it, opponents = OpponentListStub()))
        }
        return matches
    }
}


