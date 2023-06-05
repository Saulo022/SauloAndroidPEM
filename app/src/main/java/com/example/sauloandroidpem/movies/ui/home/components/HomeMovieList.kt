package com.example.sauloandroidpem.movies.ui.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sauloandroidpem.movies.data.network.response.MovieListResponse
import com.example.sauloandroidpem.movies.data.network.response.MovieResponse

@Composable
fun HomeMovieList(
    title: String,
    movies: List<MovieResponse>,
    modifier: Modifier = Modifier,
    onMovieClick: (MovieResponse) -> Unit
) {
    Column(modifier = modifier) {
        CategoryTitle(title)
        Spacer(modifier = Modifier.height(20.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(movies) {
                HomeMoviePoster(imageUrl = it.poster_path, posterSize = MoviePosterSize.SMALL) {
                    onMovieClick(it)
                }
            }
        }
    }
}