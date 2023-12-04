package com.gibranlyra.fuzecctest.domain.model

internal data class ToolbarData<out T>(
    val title: String = "",
    val data: T? = null
)