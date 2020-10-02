package com.dangerfield.hiltplayground.data

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.dangerfield.hiltplayground.util.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

// ResultType: Type for the Resource data.
// RequestType: Type for the API response.
abstract class NetworkBoundResource<ResultType, RequestType> {
    val logt = "Elijah"
    fun log(message: String) = Log.d(logt, message)

    @ExperimentalCoroutinesApi
    fun asFlow() = flow {
        log("Starting network bound resource, enter loading with null")
        emit(Resource.Loading(null))

        val dbValue = loadFromDb().first()
        if (shouldFetch(dbValue)) {
            log("we needed to fetch, showing loading with db value")
            emit(Resource.Loading(dbValue))
            delay(3000)
            when (val apiResponse = fetchFromNetwork()) {
                is ApiResponse.Success -> {
                    log("got api success response, saving and posting")
                    saveNetworkResult(processResponse(apiResponse))
                    emitAll(loadFromDb().map { Resource.Success(it) })
                }
                is ApiResponse.Error -> {
                    log("got api error response, processing and posting with db")
                    onFetchFailed()
                    emitAll(loadFromDb().map { Resource.Error(apiResponse.e, it) })
                }

                is ApiResponse.Empty -> {
                    log("got api empty response, processing and posting with db")
                    emitAll(loadFromDb().map { Resource.Error(EmptyResponseError() , it) })
                }
            }
        } else {
            log("we did NOT need to fetch, showing db")
            emitAll(loadFromDb().map { Resource.Success(it) })
        }
    }

    protected open fun onFetchFailed() {
        // Implement in sub-classes to handle errors
    }

    @WorkerThread
    protected open fun processResponse(response: ApiResponse.Success<RequestType>) = response.data

    @WorkerThread
    protected abstract suspend fun saveNetworkResult(item: RequestType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): Flow<ResultType>

    @MainThread
    protected abstract suspend fun fetchFromNetwork(): ApiResponse<RequestType>
}