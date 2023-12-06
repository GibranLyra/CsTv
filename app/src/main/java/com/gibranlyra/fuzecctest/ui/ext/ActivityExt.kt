package com.gibranlyra.fuzecctest.ui.ext

import android.app.Activity
import com.gibranlyra.fuzecctest.data.di.factoryprovider.ViewModelFactoryProvider
import dagger.hilt.android.EntryPointAccessors

internal fun Activity.viewModelFactoryProvider(): ViewModelFactoryProvider =
    EntryPointAccessors.fromActivity(this, ViewModelFactoryProvider::class.java)