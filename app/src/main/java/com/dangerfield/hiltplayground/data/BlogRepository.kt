package com.dangerfield.hiltplayground.data

import com.dangerfield.hiltplayground.db.BlogDao
import com.dangerfield.hiltplayground.db.CacheMapper
import com.dangerfield.hiltplayground.models.Blog
import com.dangerfield.hiltplayground.util.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BlogRepository @Inject constructor(
    private val blogDao: BlogDao,
    private val blogRetrofit: BlogRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) {

    @ExperimentalCoroutinesApi
    fun getBlogs(): Flow<Resource<List<Blog>>> {
        return object : NetworkBoundResource<List<Blog>, List<BlogNetworkEntity>>() {
            override suspend fun saveNetworkResult(item: List<BlogNetworkEntity>) =
                cacheBlogs(networkMapper.mapFromEntityList(item))

            override fun shouldFetch(data: List<Blog>?) = data?.isEmpty() ?: true

            override fun loadFromDb() = getLocalBlogs()

            override suspend fun fetchFromNetwork()
                    = getRemoteBlogs()
        }.asFlow()
    }

    private suspend fun getRemoteBlogs(): ApiResponse<List<BlogNetworkEntity>> =
        try {
            val networkResponse = blogRetrofit.getBlogs()
            ApiResponse.Success(networkResponse)
        } catch (e: Exception) {
            ApiResponse.Error(e)
        }

    private fun getLocalBlogs() = flow {
        val result = blogDao.getBlogs().let { cacheMapper.mapFromEntityList(it) }
        emit(result)
    }

    private suspend fun cacheBlogs(blogs: List<Blog>) {
        val cachableBlogs = blogs.map { cacheMapper.mapToEntity(it) }
        blogDao.insert(cachableBlogs)
    }
}