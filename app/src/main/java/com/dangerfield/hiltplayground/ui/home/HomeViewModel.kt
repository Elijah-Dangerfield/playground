package com.dangerfield.hiltplayground.ui.home

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.dangerfield.hiltplayground.data.blog.BlogRepository
import com.dangerfield.hiltplayground.data.user.UsersRepository
import com.dangerfield.hiltplayground.models.UserData
import com.dangerfield.hiltplayground.util.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val blogRepository: BlogRepository,
    private val usersRepository: UsersRepository
) : ViewModel() {

    val logt = "Elijah"
    fun log(message: String) = Log.d(logt, message)

    private val currentData: HomeData
        get() = _data.value ?: HomeData()

    private val _data = MutableLiveData<HomeData>()

    val data: LiveData<HomeData>
        get() = _data

    private val _error = MutableLiveData<HomeDataError>()

    val error: LiveData<HomeDataError>
        get() = _error

    @ExperimentalCoroutinesApi
    fun fetchUsers() {
        viewModelScope.launch {
            usersRepository.getUsers()
                .onEach {
                    when (it) {
                        is Resource.Success -> {
                            log("Posting users success in view model")
                            log("Users[${it.data.size}] Blogs[${currentData.blogData.size}] ")
                            _data.postValue(currentData.copy(users = it.data))
                        }
                        is Resource.Loading -> {
                            it.data?.let { data ->
                                log("Posting users loading in view model")
                                log("Users[${data.size}] Blogs[${currentData.blogData.size}] ")
                                _data.postValue(currentData.copy(users = data))
                            }
                        }
                        is Resource.Error -> {
                            it.data?.let { data ->
                                log("Posting users error in view model")
                                log("Users[${data.size}] Blogs[${currentData.blogData.size}] ")
                                _data.postValue(currentData.copy(users = data))
                            }
                            _error.postValue(
                                HomeDataError.BlogsError(
                                    it.exception.localizedMessage ?: "Unknown error"
                                )
                            )
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

    @ExperimentalCoroutinesApi
    fun fetchBlogs() {
        viewModelScope.launch {
            blogRepository.getBlogs()
                .onEach {
                    when (it) {
                        is Resource.Success -> {
                            log("Posting blogs success in view model")
                            log("Users[${currentData.users.size}] Blogs[${it.data.size}] ")
                            _data.postValue(currentData.copy(blogData = it.data))
                        }
                        is Resource.Loading -> {
                            it.data?.let { data ->
                                log("Posting blogs loading in view model")
                                log("Users[${currentData.users.size}] Blogs[${data.size}] ")
                                _data.postValue(currentData.copy(blogData = data))
                            }
                        }
                        is Resource.Error -> {
                            it.data?.let { data ->
                                log("Posting blogs error in view model")
                                log("Users[${currentData.users.size}] Blogs[${data.size}] ")
                                _data.postValue(currentData.copy(blogData = data))
                            }
                            _error.postValue(
                                HomeDataError.BlogsError(
                                    it.exception.localizedMessage ?: "Unknown error"
                                )
                            )
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }
}