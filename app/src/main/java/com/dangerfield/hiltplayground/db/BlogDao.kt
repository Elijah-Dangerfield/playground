package com.dangerfield.hiltplayground.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BlogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(blogCacheEntity: BlogCacheEntity) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(blogCacheEntities: List<BlogCacheEntity>)

    @Query("SELECT * FROM blogs")
    suspend fun getBlogs() : List<BlogCacheEntity>

}