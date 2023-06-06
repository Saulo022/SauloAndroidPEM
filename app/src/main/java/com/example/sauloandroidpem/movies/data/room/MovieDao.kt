package com.example.sauloandroidpem.movies.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.sauloandroidpem.movies.data.room.FavMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * from FavMovieEntity")
    fun getFavMovies(): Flow<List<FavMovieEntity>>

     @Insert
     suspend fun addFavMovie(item: FavMovieEntity)

}