package com.dangerfield.hiltplayground.models

import com.google.gson.annotations.SerializedName

/*
Domain model: central to the project, never changes. Used to show thingys
 */
data class Blog(
    val body: String,
    val category: String,
    val image: String,
    val id: Int,
    val title: String
)