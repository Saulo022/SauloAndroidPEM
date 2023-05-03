package com.example.sauloandroidpem.movies.domain

import com.example.sauloandroidpem.movies.data.MovieRepository
import com.example.sauloandroidpem.movies.data.network.response.MovieResponse

class MoviesUseCase {
    private val repository = MovieRepository()

    suspend operator fun invoke(apiKey: String):MutableList<MovieResponse>{
        return repository.getMovies(apiKey)
    }
}