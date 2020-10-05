package com.dangerfield.hiltplayground.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBlogs(blogCacheEntity: BlogCacheEntity) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBlogs(blogCacheEntities: List<BlogCacheEntity>)

    @Query("SELECT * FROM blogs")
    suspend fun getBlogs() : List<BlogCacheEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userCacheEntity: UserCacheEntity) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(userCacheEntities: List<UserCacheEntity>)

    @Query("SELECT * FROM users")
    suspend fun getUsers() : List<UserCacheEntity>

}