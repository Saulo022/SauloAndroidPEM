package com.example.sauloandroidpem.movies.data.room

import androidx.room.*
import com.example.sauloandroidpem.movies.data.room.FavMovieEntity
import com.example.sauloandroidpem.movies.ui.model.FavMovieModel
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * from FavMovieEntity WHERE selected = 1")
    fun getFavMovies(): Flow<List<FavMovieEntity>>

    @Query("SELECT * from FavMovieEntity")
    fun getAllMovies(): Flow<List<FavMovieEntity>>
     @Insert
     suspend fun addFavMovie(item: FavMovieEntity)

     @Update
     suspend fun updateFavMovie(item: FavMovieEntity)

    @Delete
     suspend fun deleteFavMovie(item: FavMovieEntity)
}