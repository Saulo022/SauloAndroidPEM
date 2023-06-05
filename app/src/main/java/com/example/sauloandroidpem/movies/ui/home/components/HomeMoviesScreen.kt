package com.example.sauloandroidpem.movies.ui.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.sauloandroidpem.movies.data.network.response.MovieResponse
import com.example.sauloandroidpem.movies.ui.home.HomeMovieViewModel

@Composable
fun HomeMoviesScreen(
    homeMovieViewModel: HomeMovieViewModel,
    onMovieClick: (MovieResponse) -> Unit
) {

    Box(modifier = Modifier.fillMaxSize()) {


    }
}