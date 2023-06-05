package com.example.sauloandroidpem.movies.ui.detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.moviecomposeapp.detail.presentation.components.DetailDescriptor



@Composable
fun MovieDetailScreen(
    navigationController: NavHostController,
    name: Int,
    movieDetailViewModel: MovieDetailViewModel
) {
    movieDetailViewModel.getMovies()
    val movie = movieDetailViewModel.getMovieById(name)

    val imageLoader =
        rememberAsyncImagePainter(model = "https://image.tmdb.org/t/p/w780/${movie?.backdrop_path}")

    Box(Modifier
        .fillMaxSize().background(Color.Black)
         ) {
        if (movie != null) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = imageLoader,
                    contentDescription = "background",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = movie.title,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 40.sp,
                    lineHeight = 1.0.em
                )
                Spacer(modifier = Modifier.height(20.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    DetailDescriptor(
                        text = movie.release_date,
                        backgroundColor = Color.LightGray
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    DetailDescriptor(
                        text = movie.original_language,
                        backgroundColor = Color.LightGray
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    DetailDescriptor(
                        text = movie.vote_average.toString(),
                        backgroundColor = Color.Yellow,
                        image = Icons.Default.Star
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(0.4.dp, color = Color.White, RoundedCornerShape(12.dp))
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Ver Trailer", color = Color.White)
                }

                Spacer(modifier = Modifier.height(20.dp))

                Column(horizontalAlignment = Alignment.Start) {
                    Text(
                        text = "MOVIE DESCRIPTION",
                        color = Color.White,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = movie.overview,
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 2.5.em
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                }
            }
        }
    }
}

