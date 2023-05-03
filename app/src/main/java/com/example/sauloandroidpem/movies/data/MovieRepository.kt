package com.example.sauloandroidpem.movies.data

import com.example.sauloandroidpem.movies.data.network.MovieService
import com.example.sauloandroidpem.movies.data.network.response.MovieResponse
import javax.inject.Inject

class MovieRepository @Inject constructor(private val api: MovieService){
    suspend fun getMovies(apiKey: String):MutableList<MovieResponse>{
        return api.getMovies(apiKey)
    }
}