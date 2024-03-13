package com.gibranlyra.cstv.data.di.module

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.gibranlyra.cstv.data.pagingsource.match.MatchPagingSource
import com.gibranlyra.cstv.domain.model.MatchData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Qualifier
annotation class MatchPager

const val PAGE_SIZE = 20

@Module
@InstallIn(SingletonComponent::class)
internal object PagerModule {
    @MatchPager
    @Provides
    fun provideMatchPager(
        config: PagingConfig,
        pagingSource: MatchPagingSource,
    ): Pager<Int, MatchData> = Pager(config = config, pagingSourceFactory = { pagingSource })

    @Provides
    fun providePagingConfig(): PagingConfig = PagingConfig(pageSize = PAGE_SIZE)
}
