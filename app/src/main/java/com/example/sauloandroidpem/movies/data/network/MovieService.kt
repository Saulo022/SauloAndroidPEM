package com.example.sauloandroidpem.movies.data.network

import com.example.sauloandroidpem.core.network.RetrofitHelper
import com.example.sauloandroidpem.movies.data.network.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getMovies(apiKey: String):MutableList<MovieResponse>{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(MovieClient::class.java).getPopularMoviesAll(apiKey)
            response.body()?.results!!
        }
    }
}