package com.gibranlyra.cstv.ui.ext

import android.app.Activity
import com.gibranlyra.cstv.data.di.factoryprovider.ViewModelFactoryProvider
import dagger.hilt.android.EntryPointAccessors

internal fun Activity.viewModelFactoryProvider(): ViewModelFactoryProvider =
    EntryPointAccessors.fromActivity(this, ViewModelFactoryProvider::class.java)