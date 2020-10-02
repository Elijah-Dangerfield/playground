package com.dangerfield.hiltplayground.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.dangerfield.hiltplayground.data.BlogRepository
import com.dangerfield.hiltplayground.models.Blog
import com.dangerfield.hiltplayground.util.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class BlogsViewModel @ViewModelInject constructor(
    @Assisted savedStateHandle: SavedStateHandle,
    private val blogRepository: BlogRepository
) : ViewModel() {

    private val _blogs = MutableLiveData<Resource<List<Blog>>>()

    val blogs: LiveData<Resource<List<Blog>>>
        get() = _blogs

    private val _users = MutableLiveData<Resource<List<String>>>()

    val users: LiveData<Resource<List<String>>>
        get() = _users

    fun fetchUsers() {
        viewModelScope.launch {
            _users.postValue(Resource.Success(listOf("Eli", "Eli", "Eli", "Eli", "Eli", "Eli", "Eli", "Eli")))
        }
    }

    @ExperimentalCoroutinesApi
    fun fetchBlogs() {
        viewModelScope.launch {
            blogRepository.getBlogs()
                .onEach {
                    _blogs.postValue(it)
                }.launchIn(viewModelScope)
        }
    }
}