package com.dangerfield.hiltplayground.data.blog

import com.dangerfield.hiltplayground.data.ApiResponse
import com.dangerfield.hiltplayground.data.NetworkBoundResource
import com.dangerfield.hiltplayground.db.Dao
import com.dangerfield.hiltplayground.db.BlogCacheMapper
import com.dangerfield.hiltplayground.models.BlogData
import com.dangerfield.hiltplayground.util.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BlogRepository @Inject constructor(
    private val dao: Dao,
    private val blogRetrofit: BlogRetrofit,
    private val blogCacheMapper: BlogCacheMapper,
    private val blogNetworkMapper: BlogNetworkMapper
) {

    @ExperimentalCoroutinesApi
    fun getBlogs(): Flow<Resource<List<BlogData>>> {
        return object : NetworkBoundResource<List<BlogData>, List<BlogNetworkEntity>>() {
            override suspend fun saveNetworkResult(item: List<BlogNetworkEntity>) =
                cacheBlogs(blogNetworkMapper.mapFromEntityList(item))

            override fun shouldFetch(data: List<BlogData>?) = data?.isEmpty() ?: true

            override fun loadFromDb() = getLocalBlogs()

            override suspend fun fetchFromNetwork() = getRemoteBlogs()
        }.asFlow()
    }

    private suspend fun getRemoteBlogs(): ApiResponse<List<BlogNetworkEntity>> =
        try {
            val networkResponse = blogRetrofit.getBlogs()
            ApiResponse.Success(
                networkResponse
            )
        } catch (e: Exception) {
            ApiResponse.Error(e)
        }

    private fun getLocalBlogs() = flow {
        val result = dao.getBlogs().let { blogCacheMapper.mapFromEntityList(it) }
        emit(result)
    }

    private suspend fun cacheBlogs(blogData: List<BlogData>) {
        val cachableBlogs = blogData.map { blogCacheMapper.mapToEntity(it) }
        dao.insertBlogs(cachableBlogs)
    }
}