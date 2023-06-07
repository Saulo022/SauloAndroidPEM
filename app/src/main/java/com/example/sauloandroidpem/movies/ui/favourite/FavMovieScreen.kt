package com.example.sauloandroidpem.movies.ui.favourite

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
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
            favMoviesViewModel.uiState.collect {
                value = it
            }
        }
    }

    when (uiState) {
        is MoviesUiState.Error -> {}
        MoviesUiState.Loading -> {
            CircularProgressIndicator()
        }
        is MoviesUiState.Success -> {
            FavMoviesList(
                favMovies = (uiState as MoviesUiState.Success).movies,
                favMoviesViewModel = favMoviesViewModel
            )
        }
    }

}

@Composable
fun FavMoviesList(favMovies: List<FavMovieModel>, favMoviesViewModel: FavMoviesViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Películas Favoritas",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2), modifier = Modifier
                .fillMaxSize()
                .padding(start = 24.dp, end = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(favMovies, key = { it.id }) { movie ->
                ItemFavMovies(
                    favMovie = movie,
                    favMoviesViewModel = favMoviesViewModel,
                    MoviePosterSize.BIG
                )
            }
        }
    }
}

@Composable
fun ItemFavMovies(
    favMovie: FavMovieModel,
    favMoviesViewModel: FavMoviesViewModel,
    posterSize: MoviePosterSize,
) {
    val painter =
        rememberAsyncImagePainter(model = "https://image.tmdb.org/t/p/w780/${favMovie.backdrop_path}")

    val height = if (posterSize == MoviePosterSize.SMALL) 180 else 205
    val width = if (posterSize == MoviePosterSize.SMALL) 138 else 156

    Column(/*Modifier.pointerInput(Unit) {
        detectTapGestures(onLongPress = {
            favMoviesViewModel.onItemUpdated((uiState as MoviesUiState.Success).movies[position])
        })
    }*/) {
        Image(
            painter = painter,
            contentDescription = "poster",
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .size(width = (width).dp, height = (height).dp),
            contentScale = ContentScale.Crop
        )

        Text(text = favMovie.title, style = MaterialTheme.typography.titleSmall)
    }
}

enum class MoviePosterSize() {
    SMALL,
    BIG
}