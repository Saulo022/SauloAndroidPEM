package com.example.sauloandroidpem.movies.ui.detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.moviecomposeapp.detail.presentation.components.DetailDescriptor
import com.example.sauloandroidpem.movies.data.network.response.MovieResponse


@Composable
fun MovieDetailScreen(
    name: Int,
    movieDetailViewModel: MovieDetailViewModel
) {
    movieDetailViewModel.getMovies()
    val movie = movieDetailViewModel.getMovieById(name)

    if (movie != null) {
        InfoMovie(movie = movie)
    }

    val checkedState = remember { mutableStateOf(false) }

    HeartCheckBox(checked = checkedState.value, onCheckedChange = { checkedState.value = it })

}

@Composable
fun InfoMovie(movie: MovieResponse) {
    val imageLoader =
        rememberAsyncImagePainter(model = "https://image.tmdb.org/t/p/w780/${movie.backdrop_path}")

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
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

@Composable
fun HeartCheckBox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary
) {
    Box(
        modifier = modifier
            .padding(start = 320.dp, top = 20.dp)
            .clip(CircleShape)
            .clickable { onCheckedChange(!checked) }
            .background(color = if (checked) color else Color.Transparent)
            .border(width = 3.dp, color = color, shape = CircleShape)
            .size(50.dp),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = if (checked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
            modifier = Modifier.size(35.dp),
            contentDescription = "Heart",
            tint = Color.Red
        )
    }
}