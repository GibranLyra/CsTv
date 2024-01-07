package com.gibranlyra.cstv.domain.model

import androidx.annotation.StringRes

internal sealed class State<T> {
    data class Loaded<T>(val data: T) : State<T>()

    data class Loading<T> (val data: T? = null): State<T>()

    class Uninitialized<T>: State<T>() {
        override fun equals(other: Any?): Boolean = javaClass == other?.javaClass
        override fun hashCode(): Int = javaClass.hashCode()
    }

    data class Error<T>(@StringRes val message: Int, val data: T? = null): State<T>()
}