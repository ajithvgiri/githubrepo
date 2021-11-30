package com.ajithvgiri.githubrepo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajithvgiri.githubrepo.api.Repositories
import com.ajithvgiri.githubrepo.api.Repository
import com.ajithvgiri.githubrepo.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _repositories = MutableLiveData<List<Repositories>?>()
    val repositories: LiveData<List<Repositories>?> = _repositories

    private val _isLoading = MutableLiveData<Boolean>().apply {
        value = true
    }
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    init {
        loadRepositories()
    }

    fun loadRepositories() {
        viewModelScope.launch {
            repository.getOrganizationRepositories().collect { response ->
                when (response.status) {
                    Status.LOADING -> {
                        _isLoading.postValue(true)
                    }
                    Status.SUCCESS -> {
                        _isLoading.postValue(false)
                        if (response.data?.isNotEmpty() == true) {
                            _repositories.postValue(response.data)
                        } else {
                            _error.postValue("Empty List")
                        }
                    }
                    Status.ERROR -> {
                        _isLoading.postValue(false)
                        _error.postValue(response.message)
                    }
                }
            }
        }
    }
}