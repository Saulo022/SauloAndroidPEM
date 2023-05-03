package com.example.sauloandroidpem.movies.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sauloandroidpem.movies.data.network.response.MovieResponse
import com.example.sauloandroidpem.movies.domain.MoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val moviesUseCase: MoviesUseCase):ViewModel() {

    private val _movieList = MutableLiveData<MutableList<MovieResponse>>()
    val movieList : LiveData<MutableList<MovieResponse>> = _movieList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getMovies(){
        viewModelScope.launch {
            _isLoading.value = true
            val result = moviesUseCase("2ed8e60203b71ae90dfb88f9d3cd5101")
            _movieList.value = result
            Log.i("Saulo", result[0].title)
        }
        _isLoading.value = false
    }
}