package com.gibranlyra.fuzecctest.stub

import com.gibranlyra.fuzecctest.data.entity.Team

internal object TeamStub {

    operator fun invoke(teamId: Int): Team {
        return Team(id = teamId, name = "TeamName $teamId")
    }
}