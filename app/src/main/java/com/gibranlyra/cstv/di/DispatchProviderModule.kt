package com.gibranlyra.cstv.di

import com.gibranlyra.cstv.util.DefaultDispatcherProvider
import com.gibranlyra.cstv.util.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class DispatchProviderModule {

    @Provides
    @Singleton
    fun provideDefaultDispatcherProvider(): DispatcherProvider = DefaultDispatcherProvider()
}
