package com.example.sauloandroidpem.core.di.network

import com.example.sauloandroidpem.movies.data.network.MovieClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

   @Singleton
   @Provides
   fun provideMovieClinet(retrofit: Retrofit):MovieClient{
       return retrofit.create(MovieClient::class.java)
   }
}