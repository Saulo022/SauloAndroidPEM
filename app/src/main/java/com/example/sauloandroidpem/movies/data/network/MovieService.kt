package com.example.sauloandroidpem.movies.data.network

import com.example.sauloandroidpem.movies.data.network.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject

class MovieService @Inject constructor(private val movieClient: MovieClient){

    suspend fun getMovies(apiKey: String):MutableList<MovieResponse>{
        return withContext(Dispatchers.IO){
            val response = movieClient.getPopularMoviesAll(apiKey)
            response.body()?.results!!
        }
    }
}