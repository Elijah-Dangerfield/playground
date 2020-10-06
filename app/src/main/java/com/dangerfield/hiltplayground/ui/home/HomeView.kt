package com.dangerfield.hiltplayground.ui.home

import android.view.View
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dangerfield.hiltplayground.R
import com.dangerfield.hiltplayground.models.CarouselData
import com.dangerfield.hiltplayground.models.HeaderData
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class HomeView(
    private val viewModel: HomeViewModel,
    private val lifecycleOwner: LifecycleOwner,
    private val view: View
) {
    private val blogsRecyclerView : RecyclerView = view.findViewById(R.id.blogsRecyclerView)
    private val blogsAdapter = BlogAdapter()
    private val blogsHeaderAdapter = HeaderAdapter()
    private val userHeaderAdapter = HeaderAdapter()
    private val usersAdapter = CarouselAdapter(UsersAdapter() as IhrAdapter<out RecyclerView.ViewHolder, Any>)

    init {
        setupObservers()
        setupView()
        fetchInitialData()
    }

    private fun setupView() {
        blogsRecyclerView.layoutManager = LinearLayoutManager(view.context)
        blogsRecyclerView.adapter = ConcatAdapter(userHeaderAdapter, usersAdapter, blogsHeaderAdapter, blogsAdapter)
    }

    private fun setupObservers() {
        viewModel.data.observe(lifecycleOwner, Observer {
            blogsAdapter.setItems(it.blogData)
            blogsHeaderAdapter.setItems(listOf(HeaderData("Blogs")))
            userHeaderAdapter.setItems(listOf(HeaderData("Users")))
            usersAdapter.setItems(listOf(CarouselData(it.userData)))
        })

        viewModel.error.observe(lifecycleOwner, Observer {
            handleError(it)
        })
    }

    private fun fetchInitialData() {
        viewModel.fetchBlogs()
        viewModel.fetchUsers()
    }

    private fun handleError(error: HomeDataError) {
        Toast.makeText(view.context, error.message, Toast.LENGTH_LONG)
            .show()
    }

    companion object {
        val userCarouselTag = "USER_CAROUSEL"
    }
}