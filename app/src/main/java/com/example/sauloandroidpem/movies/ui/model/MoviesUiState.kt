package com.example.sauloandroidpem.movies.ui.model

sealed interface MoviesUiState {
    object Loading: MoviesUiState
    data class Error (val throwable: Throwable): MoviesUiState
    data class Success (val movies: List<FavMovieModel>): MoviesUiState
}