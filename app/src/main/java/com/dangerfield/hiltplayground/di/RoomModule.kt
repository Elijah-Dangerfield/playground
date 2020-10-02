package com.dangerfield.hiltplayground.di

import android.content.Context
import androidx.room.Room
import com.dangerfield.hiltplayground.db.BlogDao
import com.dangerfield.hiltplayground.db.BlogDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun providesBlogDatabase(@ApplicationContext context: Context) : BlogDatabase {
        return Room.databaseBuilder(context, BlogDatabase::class.java, BlogDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration() // if no migration found, clear database, restart
            .build()
    }

    @Singleton
    @Provides
    fun providesBlogDatabaseDao(blogDatabase: BlogDatabase): BlogDao {
        return blogDatabase.blogDao()
    }
}