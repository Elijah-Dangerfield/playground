package com.dangerfield.hiltplayground.util

sealed class Resource<out R> {
    data class Success<out T>(val data: T): Resource<T>()
    data class Loading<out T>(val data: T? = null) : Resource<T>()
    data class Error<out T>(val exception: Exception, val data: T? = null): Resource<T>()
}