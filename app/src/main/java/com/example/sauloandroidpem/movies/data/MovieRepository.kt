package com.example.sauloandroidpem.movies.data

import com.example.sauloandroidpem.movies.data.network.MovieService
import com.example.sauloandroidpem.movies.data.network.response.MovieResponse
import com.example.sauloandroidpem.movies.data.room.FavMovieEntity
import com.example.sauloandroidpem.movies.data.room.MovieDao
import com.example.sauloandroidpem.movies.ui.model.FavMovieModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MovieRepository @Inject constructor(
    private val api: MovieService,
    private val movieDao: MovieDao
) {
    suspend fun getMovies(apiKey: String): MutableList<MovieResponse> {
        return api.getMovies(apiKey)
    }

    val favMovies: Flow<List<FavMovieModel>> = movieDao.getFavMovies().map { items ->
        items.map {
            FavMovieModel(
                it.id,
                it.title,
                it.vote_average,
                it.backdrop_path,
                it.selected
            )
        }
    }

    suspend fun addFavMovie(favMovieModel: FavMovieModel) {
        movieDao.addFavMovie(
            FavMovieEntity(
                favMovieModel.id,
                favMovieModel.title,
                favMovieModel.vote_average,
                favMovieModel.backdrop_path,
                favMovieModel.selected
            )
        )
    }
}