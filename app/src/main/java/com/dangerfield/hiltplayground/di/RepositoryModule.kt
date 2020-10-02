package com.dangerfield.hiltplayground.di

import com.dangerfield.hiltplayground.data.BlogRepository
import com.dangerfield.hiltplayground.data.BlogRetrofit
import com.dangerfield.hiltplayground.data.NetworkMapper
import com.dangerfield.hiltplayground.db.BlogDao
import com.dangerfield.hiltplayground.db.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesBlogsRepository(
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper,
        blogDao: BlogDao,
        blogRetrofit: BlogRetrofit
    ): BlogRepository {
        return BlogRepository(blogDao, blogRetrofit, cacheMapper, networkMapper)
    }
}