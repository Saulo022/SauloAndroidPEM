package com.example.sauloandroidpem.movies.data.network.response

import androidx.room.PrimaryKey

data class MovieResponse (
    val adult: Boolean,
    val backdrop_path: String,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    var video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)
