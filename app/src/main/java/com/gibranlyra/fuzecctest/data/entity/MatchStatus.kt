package com.gibranlyra.fuzecctest.data.entity

import kotlinx.serialization.SerialName

enum class MatchStatus {
    @SerialName("running")
    RUNNING,

    @SerialName("finished")
    FINISHED,

    @SerialName("not_started")
    NOT_STARTED
}
