package com.dangerfield.hiltplayground.ui.home

import com.dangerfield.hiltplayground.models.BlogData
import com.dangerfield.hiltplayground.models.HeaderData
import com.dangerfield.hiltplayground.models.UserData
import com.dangerfield.hiltplayground.ui.items.BlogItem
import com.dangerfield.hiltplayground.ui.items.CarouselItem
import com.dangerfield.hiltplayground.ui.items.HeaderItem
import com.dangerfield.hiltplayground.ui.items.UserItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder

data class HomeData(
    val blogData: List<BlogData> = listOf(),
    val users: List<UserData> = listOf()
) {

    fun toSections() = listOf(createUsers(), createBlogs())

    private fun createUsers(): Section {
        val section = Section(HeaderItem(HeaderData("Users")))
        val userAdapter = GroupAdapter<GroupieViewHolder>()
        userAdapter.update(users.map { UserItem(it) })
        section.add(CarouselItem(userAdapter))
        return section
    }

    private fun createBlogs(): Section {
        val section = Section(HeaderItem(HeaderData("Blogs")))
        section.update(this.blogData.map { BlogItem(it) })
        return section
    }
}