package com.gibranlyra.cstv.domain.model

internal data class ToolbarData<out T>(
    val title: String = "",
    val data: T? = null,
)
