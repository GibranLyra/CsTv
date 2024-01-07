package com.gibranlyra.cstv.data.di.module

import com.gibranlyra.cstv.data.match.remote.MatchApi
import com.gibranlyra.cstv.data.team.remote.TeamApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class ApiServiceModule {

    @Provides
    @Singleton
    fun provideMatchApiService(retrofit: Retrofit): MatchApi =
        retrofit.create(MatchApi::class.java)

    @Provides
    @Singleton
    fun provideTeamApiService(retrofit: Retrofit): TeamApi =
        retrofit.create(TeamApi::class.java)
}
