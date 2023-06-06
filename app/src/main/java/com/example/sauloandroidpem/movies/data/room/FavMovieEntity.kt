package com.example.sauloandroidpem.movies.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavMovieEntity (
    @PrimaryKey
    val id: Int,
    val title: String,
    val vote_average: Double,
    val backdrop_path: String,
    var selected: Boolean
)