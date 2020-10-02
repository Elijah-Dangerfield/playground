package com.dangerfield.hiltplayground.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.dangerfield.hiltplayground.R
import com.dangerfield.hiltplayground.models.Blog
import com.dangerfield.hiltplayground.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_blogs.*

@WithFragmentBindings
@AndroidEntryPoint
class BlogsFragment : Fragment(R.layout.fragment_blogs), BlogClickHandler, UserClickHandler {

    private val viewModel: BlogsViewModel by viewModels()

    private val blogsAdapter = CarouselAdapter.Vertical("Blogs", BlogsAdapter(this))
    private val usersAdapter = CarouselAdapter.Horizontal("Users", UsersAdapter(this))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchInitialData()
        setupObservers()
        setupView()
    }

    private fun setupView() {
        blogsRecyclerView.layoutManager = LinearLayoutManager(context)
        blogsRecyclerView.adapter = ConcatAdapter(usersAdapter, blogsAdapter)
    }

    private fun setupObservers() {
        viewModel.blogs.observe(this) {
            when (it) {
                is Resource.Success -> showBlogsData(it.data)
                is Resource.Error -> handleBlogsFetchError(it)
            }
        }

        viewModel.users.observe(this) {
            when (it) {
                is Resource.Success -> showUserData(it.data)
                is Resource.Error -> handleUserDataFetchError(it)
            }
        }
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

    private fun handleBlogsFetchError(resource: Resource.Error<List<Blog>>) {
        resource.data?.let { showBlogsData(it) }
        Log.d("Elijah", "Error loading blogs: ${resource.exception.message}")
        Toast.makeText(context, "Sorry, there was an error loading the blogs", Toast.LENGTH_LONG)
            .show()
    }

    private fun handleUserDataFetchError(resource: Resource.Error<List<String>>) {
        resource.data?.let { showUserData(it) }
        Log.d("Elijah", "Error loading users: ${resource.exception.message}")
        Toast.makeText(context, "Sorry, there was an error loading the users", Toast.LENGTH_LONG)
            .show()
    }


    override fun onBlogClicked(blog: Blog) {
    }

    override fun onUserClicked(user: String) {

    }
}