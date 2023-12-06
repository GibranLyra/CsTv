package com.gibranlyra.fuzecctest.data.di.module

import com.gibranlyra.fuzecctest.data.match.MatchDataSource
import com.gibranlyra.fuzecctest.data.match.remote.MatchDataSourceImpl
import com.gibranlyra.fuzecctest.data.team.TeamDataSource
import com.gibranlyra.fuzecctest.data.team.remote.TeamDataSourceImpl
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

    @Singleton
    @Binds
    abstract fun bindTeamDataSource(impl: TeamDataSourceImpl): TeamDataSource
}