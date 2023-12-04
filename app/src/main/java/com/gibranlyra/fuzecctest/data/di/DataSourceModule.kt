package com.gibranlyra.fuzecctest.data.di

import com.gibranlyra.fuzecctest.data.match.MatchDataSource
import com.gibranlyra.fuzecctest.data.match.remote.MatchDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class DataSourceModule {
    @Singleton
    @Binds
    abstract fun bindMatchDataSource(impl: MatchDataSourceImpl): MatchDataSource
}