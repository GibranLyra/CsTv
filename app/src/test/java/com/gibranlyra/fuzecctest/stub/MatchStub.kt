package com.gibranlyra.fuzecctest.stub

import com.gibranlyra.fuzecctest.data.entity.Match

internal object MatchStub {
    operator fun invoke(id: Int): Match {
        return Match(id = id)
    }
}