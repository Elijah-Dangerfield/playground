package com.dangerfield.hiltplayground.ui.home

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dangerfield.hiltplayground.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class HomeView(
    private val viewModel: HomeViewModel,
    private val lifecycleOwner: LifecycleOwner,
    private val view: View
) {

    private val blogsRecyclerView : RecyclerView = view.findViewById(R.id.blogsRecyclerView)
    private val adapter = GroupAdapter<GroupieViewHolder>()
    val logt = "Elijah"
    fun log(message: String) = Log.d(logt, message)

    init {
        setupObservers()
        setupView()
        fetchInitialData()
    }

    private fun setupObservers() {
        viewModel.data.observe(lifecycleOwner, Observer {
            log("\nGot new data in view. " +
                    "\nUsers size: ${it.userData.size} \n" +
                    "Blogs size: ${it.blogData.size}")
            adapter.update(it.toSections())
        })

        viewModel.error.observe(lifecycleOwner, Observer {
            handleError(it)
        })
    }

    private fun setupView() {
        blogsRecyclerView.layoutManager = LinearLayoutManager(view.context)
        blogsRecyclerView.adapter = adapter
    }

    private fun fetchInitialData() {
        viewModel.fetchBlogs()
        viewModel.fetchUsers()
    }

    private fun handleError(error: HomeDataError) {
        Toast.makeText(view.context, error.message, Toast.LENGTH_LONG).show()
    }
}