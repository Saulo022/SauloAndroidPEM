package com.example.sauloandroidpem.movies.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sauloandroidpem.movies.data.room.FavMovieEntity
import com.example.sauloandroidpem.movies.data.room.MovieDao

@Database(entities = [FavMovieEntity::class], version = 1)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}