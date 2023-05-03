package com.example.sauloandroidpem.movies.data.network.response

data class MovieListResponse(
    val page: Int,
    val results: MutableList<MovieResponse>,
    val total_pages: Int,
    val total_results: Int
)
