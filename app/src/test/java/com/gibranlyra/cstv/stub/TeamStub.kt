package com.gibranlyra.cstv.stub

import com.gibranlyra.cstv.data.entity.Team

internal object TeamStub {

    operator fun invoke(teamId: Int): Team {
        return Team(id = teamId, name = "TeamName $teamId")
    }
}