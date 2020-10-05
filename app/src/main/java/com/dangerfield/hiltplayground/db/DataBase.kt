package com.dangerfield.hiltplayground.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BlogCacheEntity::class, UserCacheEntity::class], version = 2)
abstract class DataBase: RoomDatabase() {
    abstract fun dao() : Dao

    companion object {
        const val DATABASE_NAME = "elis_really_cool_database"
    }
}

