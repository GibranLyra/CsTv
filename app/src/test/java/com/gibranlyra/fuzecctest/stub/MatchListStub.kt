package com.gibranlyra.fuzecctest.stub

import com.gibranlyra.fuzecctest.data.entity.Match

object MatchListStub {
    operator fun invoke(): MutableList<Match> {
        val matches = mutableListOf<Match>()
        repeat(5) {
            matches.add(Match(id = it, opponents = OpponentListStub()))
        }
        return matches
    }
}


