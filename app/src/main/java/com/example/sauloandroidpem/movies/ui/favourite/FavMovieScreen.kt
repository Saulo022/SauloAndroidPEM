package com.example.sauloandroidpem.movies.ui.favourite

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.sauloandroidpem.movies.ui.model.FavMovieModel
import com.example.sauloandroidpem.movies.ui.model.MoviesUiState

@Composable
fun FavMoviesScreen(favMoviesViewModel: FavMoviesViewModel) {

    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val uiState by produceState<MoviesUiState>(
        initialValue = MoviesUiState.Loading,
        key1 = lifecycle,
        key2 = favMoviesViewModel
    ) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            favMoviesViewModel.uiState.collect{
                value = it
            }
        }
    }

    when(uiState) {
        is MoviesUiState.Error -> {}
        MoviesUiState.Loading -> { CircularProgressIndicator() }
        is MoviesUiState.Success -> { Text(text = "Hola")}
    }

}