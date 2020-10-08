package com.dangerfield.hiltplayground.ui.home

import com.dangerfield.hiltplayground.models.BlogData
import com.dangerfield.hiltplayground.models.CarouselData
import com.dangerfield.hiltplayground.models.HeaderData
import com.dangerfield.hiltplayground.models.UserData

data class HomeData(val blogData: List<BlogData> = listOf(), val userData: List<UserData> = listOf()) {

    //TODO maybe have an interface for all screen data to conform to?
    fun toDataList(): List<Any> {
        return createUsers()+ createBlogs()
    }

    private fun createUsers(): List<Any> {
        return listOf(HeaderData("Users")) + CarouselData(
            this.userData
        )
    }

    private fun createBlogs(): List<Any> {
        return listOf(HeaderData("Blogs")) + this.blogData
    }
}