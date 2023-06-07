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

    val allMovies: Flow<List<FavMovieModel>> = movieDao.getAllMovies().map { items ->
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
        movieDao.addFavMovie(favMovieModel.toData())
    }

    suspend fun updateFavMovie(favMovieModel: FavMovieModel) {
        movieDao.updateFavMovie(
            favMovieModel.toData()
        )
    }

    suspend fun deleteFavMovie(favMovieModel: FavMovieModel) {
        movieDao.deleteFavMovie(
            favMovieModel.toData()
        )
    }
}

fun FavMovieModel.toData(): FavMovieEntity {
    return FavMovieEntity(this.id, this.title, this.vote_average, this.backdrop_path, this.selected)
}