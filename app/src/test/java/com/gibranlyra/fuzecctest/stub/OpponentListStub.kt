package com.gibranlyra.fuzecctest.stub

import com.gibranlyra.fuzecctest.data.entity.Opponent

object OpponentListStub {
    operator fun invoke(): MutableList<Opponent> {
        val opponents = mutableListOf<Opponent>()
        repeat(5) {
            opponents.add(Opponent(TeamStub(it)))
        }
        return opponents
    }
}