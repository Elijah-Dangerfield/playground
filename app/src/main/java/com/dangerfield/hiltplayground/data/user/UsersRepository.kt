package com.dangerfield.hiltplayground.data.user

import com.dangerfield.hiltplayground.data.ApiResponse
import com.dangerfield.hiltplayground.data.NetworkBoundResource
import com.dangerfield.hiltplayground.data.blog.BlogNetworkEntity
import com.dangerfield.hiltplayground.data.blog.BlogNetworkMapper
import com.dangerfield.hiltplayground.data.blog.BlogRetrofit
import com.dangerfield.hiltplayground.db.BlogCacheMapper
import com.dangerfield.hiltplayground.db.Dao
import com.dangerfield.hiltplayground.db.UserCacheMapper
import com.dangerfield.hiltplayground.models.BlogData
import com.dangerfield.hiltplayground.models.UserData
import com.dangerfield.hiltplayground.util.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val dao: Dao,
    private val userRetrofit: UserRetrofit,
    private val userCacheMapper: UserCacheMapper,
    private val userNetworkMapper: UserNetworkMapper
) {

    @ExperimentalCoroutinesApi
    fun getUsers(): Flow<Resource<List<UserData>>> {
        return object : NetworkBoundResource<List<UserData>, List<UserNetworkEntity>>() {
            override suspend fun saveNetworkResult(item: List<UserNetworkEntity>) =
                cacheUsers(userNetworkMapper.mapFromEntityList(item))

            override fun shouldFetch(data: List<UserData>?) = data?.isEmpty() ?: true

            override fun loadFromDb() = getLocalUsers()

            override suspend fun fetchFromNetwork() = getRemoteUsers()
        }.asFlow()
    }

    private suspend fun getRemoteUsers(): ApiResponse<List<UserNetworkEntity>> =
        try {
            val networkResponse = userRetrofit.getUsers()
            ApiResponse.Success(
                networkResponse
            )
        } catch (e: Exception) {
            ApiResponse.Error(e)
        }

    private fun getLocalUsers() = flow {
        val result = dao.getUsers().let { userCacheMapper.mapFromEntityList(it) }
        emit(result)
    }

    private suspend fun cacheUsers(blogData: List<UserData>) {
        val cachableBlogs = blogData.map { userCacheMapper.mapToEntity(it) }
        dao.insertUsers(cachableBlogs)
    }
}