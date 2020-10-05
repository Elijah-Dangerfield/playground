package com.dangerfield.hiltplayground.data.user

import retrofit2.http.GET

interface UserRetrofit {

    @GET("users")
    suspend fun getUsers() : List<UserNetworkEntity>
}