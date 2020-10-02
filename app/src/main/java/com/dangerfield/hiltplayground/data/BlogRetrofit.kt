package com.dangerfield.hiltplayground.data

import retrofit2.http.GET

interface BlogRetrofit {

    @GET("blogs")
    suspend fun getBlogs() : List<BlogNetworkEntity>
}