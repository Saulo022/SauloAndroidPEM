package com.example.sauloandroidpem.movies.domain

import com.example.sauloandroidpem.movies.data.MovieRepository
import com.example.sauloandroidpem.movies.ui.model.FavMovieModel
import javax.inject.Inject

class AddFavMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(favMovieModel: FavMovieModel) {
        movieRepository.addFavMovie(favMovieModel)
    }
}