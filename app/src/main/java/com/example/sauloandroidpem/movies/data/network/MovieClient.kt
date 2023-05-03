package com.example.sauloandroidpem.movies.data.network

import com.example.sauloandroidpem.movies.data.network.response.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieClient {
    @GET("movie/popular")
    suspend fun getPopularMoviesAll(
        @Query("api_key") apiKey: String
    ) : Response<MovieListResponse>
}