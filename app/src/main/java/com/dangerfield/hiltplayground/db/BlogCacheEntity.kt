package com.dangerfield.hiltplayground.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "blogs")
data class BlogCacheEntity (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "body")
    var body: String,

    @ColumnInfo(name = "category")
    var category: String,

    @ColumnInfo(name = "image")
    var image: String,

    @ColumnInfo(name = "title")
    var title: String
){

}