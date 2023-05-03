package com.example.sauloandroidpem.movies.domain

import com.example.sauloandroidpem.movies.data.MovieRepository
import com.example.sauloandroidpem.movies.data.network.response.MovieResponse
import javax.inject.Inject

class MoviesUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke(apiKey: String):MutableList<MovieResponse>{
        return repository.getMovies(apiKey)
    }
}