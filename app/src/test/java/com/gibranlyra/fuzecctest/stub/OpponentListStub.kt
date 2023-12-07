package com.gibranlyra.fuzecctest.stub

import com.gibranlyra.fuzecctest.data.entity.Opponent
import com.gibranlyra.fuzecctest.data.entity.Team

object OpponentListStub {
    operator fun invoke(): MutableList<Opponent> {
        val opponents = mutableListOf<Opponent>()
        repeat(5) {
            opponents.add(Opponent(Team(id = it, name = "TeamName $it")))
        }
        return opponents
    }
}