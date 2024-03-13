package com.gibranlyra.cstv.data.di.module

import com.gibranlyra.cstv.data.match.MatchDataSource
import com.gibranlyra.cstv.data.match.remote.MatchDataSourceImpl
import com.gibranlyra.cstv.data.team.TeamDataSource
import com.gibranlyra.cstv.data.team.remote.TeamDataSourceImpl
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
