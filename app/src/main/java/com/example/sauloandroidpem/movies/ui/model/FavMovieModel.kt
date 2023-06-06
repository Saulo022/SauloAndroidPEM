package com.example.sauloandroidpem.movies.ui.model

data class FavMovieModel(
    val id: Int,
    val title: String,
    val vote_average: Double,
    val backdrop_path: String,
    var selected: Boolean = false
)
