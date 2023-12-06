package com.gibranlyra.fuzecctest.data.di.module

import com.gibranlyra.fuzecctest.data.match.remote.MatchApi
import com.gibranlyra.fuzecctest.data.team.remote.TeamApi
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
