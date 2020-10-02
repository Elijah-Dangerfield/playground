package com.dangerfield.hiltplayground.data

import java.lang.Exception

sealed class ApiResponse<T> {
    class Success<T>(val data: T) : ApiResponse<T>()
    class Error<T>(val e: Exception) : ApiResponse<T>()
    class Empty<T>() : ApiResponse<T>()
}