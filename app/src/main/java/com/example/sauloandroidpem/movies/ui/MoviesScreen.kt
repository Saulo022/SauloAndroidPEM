package com.example.sauloandroidpem.movies.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MoviesScreen(movieViewModel: MovieViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {

        val isLoading: Boolean by movieViewModel.isLoading.observeAsState(initial = false)
        if(isLoading){
            Box(
                Modifier
                    .fillMaxSize()
                    .align(Alignment.TopCenter)) {
                CircularProgressIndicator(color = Color.White)
            }
        }
         else {
            Button(onClick = { movieViewModel.getMovies()}, modifier = Modifier.align(Alignment.Center)) {
                Text(text = "Click ME")
            }
        }
    }
}

