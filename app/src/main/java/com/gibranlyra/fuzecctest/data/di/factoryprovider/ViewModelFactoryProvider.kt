package com.gibranlyra.fuzecctest.data.di.factoryprovider

import com.gibranlyra.fuzecctest.ui.screen.matchdetails.MatchDetailsViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@EntryPoint
@InstallIn(ActivityComponent::class)
internal interface ViewModelFactoryProvider {
    fun characterDetailViewModelFactory(): MatchDetailsViewModel.Factory
}
