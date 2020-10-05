package com.dangerfield.hiltplayground.ui.home

sealed class HomeDataError(val message: String) {
    class UsersError(message: String) : HomeDataError(message)
    class BlogsError(message: String) : HomeDataError(message)
}