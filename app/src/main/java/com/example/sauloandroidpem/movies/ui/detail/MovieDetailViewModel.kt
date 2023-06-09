package com.example.sauloandroidpem.movies.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sauloandroidpem.movies.data.network.response.MovieResponse
import com.example.sauloandroidpem.movies.domain.*
import com.example.sauloandroidpem.movies.ui.model.FavMovieModel
import com.example.sauloandroidpem.movies.ui.model.MoviesUiState
import com.example.sauloandroidpem.movies.ui.model.MoviesUiState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val moviesUseCase: MoviesUseCase,
    private val addFavMoviesUseCase: AddFavMoviesUseCase,
    private val updateFavMoviesUseCase: UpdateFavMoviesUseCase,
    getAllMoviesUseCase: GetAllMoviesUseCase
) : ViewModel() {

    val uiState: StateFlow<MoviesUiState> = getAllMoviesUseCase().map(::Success)
        .catch { MoviesUiState.Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), MoviesUiState.Loading)


    private val _movieList = MutableLiveData<MutableList<MovieResponse>>()
    val movieList: LiveData<MutableList<MovieResponse>> = _movieList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getMovies() {
        viewModelScope.launch {
            _isLoading.value = true
            val result = moviesUseCase("2ed8e60203b71ae90dfb88f9d3cd5101")
            _movieList.value = result
            Log.i("Saulo", result[0].title)
        }
        _isLoading.value = false
    }

    fun getMovieById(id: Int): MovieResponse? {
        val currentMovies = movieList.value
        return currentMovies?.find { it.id == id }
    }

    fun addFavMovie(movie: MovieResponse) {
        viewModelScope.launch {
            addFavMoviesUseCase(
                FavMovieModel(
                    id = movie.id,
                    title = movie.title,
                    vote_average = movie.vote_average,
                    backdrop_path = movie.backdrop_path
                )
            )
        }
    }

    fun onCheckBoxSelected(favMovieModel: FavMovieModel) {
        viewModelScope.launch {
            updateFavMoviesUseCase(favMovieModel.copy(selected = !favMovieModel.selected))
        }
    }

    fun getListPos(id: Int): Int {
        val movies = (uiState as MoviesUiState.Success).movies
        val position = movies.indexOfFirst { it.id == id}
        return position
    }
}