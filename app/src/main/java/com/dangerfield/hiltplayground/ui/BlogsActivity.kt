package com.dangerfield.hiltplayground.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.dangerfield.hiltplayground.R
import com.dangerfield.hiltplayground.models.Blog
import com.dangerfield.hiltplayground.util.goneUnless
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import com.dangerfield.hiltplayground.util.Resource.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import kotlinx.coroutines.ExperimentalCoroutinesApi

//marks this as a client of hilt
@AndroidEntryPoint
@ExperimentalCoroutinesApi
class BlogsActivity : AppCompatActivity(), BlogClickHandler, UserClickHandler {

    private val viewModel: BlogsViewModel by viewModels()

    private val blogsAdapter = CarouselAdapter.Vertical("Blogs", BlogsAdapter(this))
    private val usersAdapter = CarouselAdapter.Horizontal("Users", UsersAdapter(this))


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchInitialData()
        setupObservers()
        setupView()
    }

    private fun setupView() {
        blogsRecyclerView.layoutManager = LinearLayoutManager(this)
        blogsRecyclerView.adapter = ConcatAdapter(usersAdapter, blogsAdapter)
    }

    private fun setupObservers() {
        viewModel.blogs.observe(this, {
            when (it) {
                is Success -> showBlogsData(it.data)
                is Error -> handleBlogsFetchError(it)
            }
        })

        viewModel.users.observe(this, {
            when (it) {
                is Success -> showUserData(it.data)
                is Error -> handleUserDataFetchError(it)
            }
        })
    }

    private fun showUserData(data: List<String>) {
        usersAdapter.update(data)
    }

    private fun showBlogsData(blogs: List<Blog>) {
        blogsAdapter.update(blogs)
    }

    private fun fetchInitialData() {
        viewModel.fetchBlogs()
        viewModel.fetchUsers()
    }

    private fun handleBlogsFetchError(resource: Error<List<Blog>>) {
        resource.data?.let { showBlogsData(it) }
        Log.d("Elijah", "Error loading blogs: ${resource.exception.message}")
        Toast.makeText(this, "Sorry, there was an error loading the blogs", Toast.LENGTH_LONG)
            .show()
    }

    private fun handleUserDataFetchError(resource: Error<List<String>>) {
        resource.data?.let { showUserData(it) }
        Log.d("Elijah", "Error loading users: ${resource.exception.message}")
        Toast.makeText(this, "Sorry, there was an error loading the users", Toast.LENGTH_LONG)
            .show()
    }


    override fun onBlogClicked(blog: Blog) {
    }

    override fun onUserClicked(user: String) {

    }
}