package com.example.sauloandroidpem.movies.ui.home

import com.example.sauloandroidpem.movies.data.network.response.MovieResponse

data class HomeMovieState(
    val popularMovies: List<MovieResponse> = emptyList()
)