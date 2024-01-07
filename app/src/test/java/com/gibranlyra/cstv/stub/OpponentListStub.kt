package com.gibranlyra.cstv.stub

import com.gibranlyra.cstv.data.entity.Opponent

object OpponentListStub {
    operator fun invoke(): MutableList<Opponent> {
        val opponents = mutableListOf<Opponent>()
        repeat(5) {
            opponents.add(Opponent(TeamStub(it)))
        }
        return opponents
    }
}