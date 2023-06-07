package com.example.sauloandroidpem.movies.domain

import com.example.sauloandroidpem.movies.data.MovieRepository
import com.example.sauloandroidpem.movies.ui.model.FavMovieModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    operator fun invoke(): Flow<List<FavMovieModel>> {
        return movieRepository.allMovies
    }
}