package com.dangerfield.hiltplayground.di

import android.content.Context
import androidx.room.Room
import com.dangerfield.hiltplayground.db.Dao
import com.dangerfield.hiltplayground.db.DataBase
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
    fun provideDatabase(@ApplicationContext context: Context) : DataBase {
        return Room.databaseBuilder(context, DataBase::class.java, DataBase.DATABASE_NAME)
            .fallbackToDestructiveMigration() // if no migration found, clear database, restart
            .build()
    }

    @Singleton
    @Provides
    fun provideDatabaseDao(dataBase: DataBase): Dao {
        return dataBase.dao()
    }
}