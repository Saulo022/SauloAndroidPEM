package com.example.sauloandroidpem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sauloandroidpem.movies.ui.home.HomeMovieViewModel
import com.example.sauloandroidpem.movies.ui.MoviesScreen
import com.example.sauloandroidpem.movies.ui.detail.MovieDetailScreen
import com.example.sauloandroidpem.movies.ui.detail.MovieDetailViewModel
import com.example.sauloandroidpem.ui.theme.SauloAndroidPEMTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val movieViewModel: HomeMovieViewModel by viewModels()
    private val movieDetailViewModel: MovieDetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SauloAndroidPEMTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navigationController = rememberNavController()
                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.MoviesScreen.route
                    ) {
                        composable(Routes.MoviesScreen.route) {
                            MoviesScreen(navigationController, movieViewModel)
                        }
                        composable(Routes.MovieDetailScreen.route, arguments = listOf(navArgument("name"){type = NavType.IntType})) { backStackEntry ->
                            MovieDetailScreen(
                                navigationController,
                                backStackEntry.arguments?.getInt("name") ?: 0,
                                movieDetailViewModel
                            )
                        }
                    }
                }
            }
        }
    }
}
