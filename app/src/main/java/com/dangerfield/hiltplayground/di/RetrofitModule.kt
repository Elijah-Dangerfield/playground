package com.dangerfield.hiltplayground.di

import com.dangerfield.hiltplayground.data.blog.BlogRetrofit
import com.dangerfield.hiltplayground.data.user.UserRetrofit
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class) //makes this an application wide dependency
object RetrofitModule {

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation()
            .create()
    }


    @Singleton
    @Provides
    fun providesBlogService(gson: Gson): BlogRetrofit {
        return Retrofit.Builder().baseUrl("https://open-api.xyz/placeholder/")
            .addConverterFactory(GsonConverterFactory.create(gson)).build().create(BlogRetrofit::class.java)
    }

    @Singleton
    @Provides
    fun providesUserService(gson: Gson): UserRetrofit {
        return Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create(gson)).build().create(UserRetrofit::class.java)
    }
}