package com.example.sauloandroidpem.movies.ui

import android.annotation.SuppressLint
import android.util.Size
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.sauloandroidpem.movies.data.network.response.MovieResponse

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MoviesScreen(movieViewModel: MovieViewModel) {

    Box(modifier = Modifier.fillMaxSize()) {

        val isLoading: Boolean by movieViewModel.isLoading.observeAsState(initial = false)
        if (isLoading) {
            Box(
                Modifier
                    .fillMaxSize()
                    .align(Alignment.TopCenter)
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        } else {
            movieViewModel.getMovies()
            val movieList by movieViewModel.movieList.observeAsState()
            LazyColumn(content = {
                items(movieList ?: emptyList()) { movie ->
                    MovieCard(movie = movie)
                }
            })
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoilApi::class)
@Composable
fun MovieCard(movie: MovieResponse) {
    var isExpanded by remember { mutableStateOf(false) }
    val painter =
        rememberImagePainter(data = "https://image.tmdb.org/t/p/w780/${movie.poster_path}")
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = movie.title,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(8.dp)
            )
            Image(
                painter = painter,
                contentDescription = "Poster de las peliculas",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .padding(8.dp),
            )
            Text(
                text = movie.overview,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .heightIn(min = 64.dp, max = if (isExpanded) 256.dp else 64.dp),
                overflow = TextOverflow.Ellipsis,
                maxLines = if (isExpanded) 8 else 2
            )
            Button(
                onClick = { isExpanded = !isExpanded },
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.End)
            ) {
                Text(text = if (isExpanded) "Ver menos" else "Ver más")
            }
        }
    }
}


@Composable
fun MyTopAppBar() {
    SmallTopAppBar(title = { Text(text = "Movies") })
}

