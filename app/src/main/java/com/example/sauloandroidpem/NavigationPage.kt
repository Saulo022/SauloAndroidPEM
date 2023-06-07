package com.example.sauloandroidpem

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import com.example.sauloandroidpem.movies.ui.HomeMovieScreen
import com.example.sauloandroidpem.movies.ui.detail.MovieDetailScreen
import com.example.sauloandroidpem.movies.ui.detail.MovieDetailViewModel
import com.example.sauloandroidpem.movies.ui.favourite.FavMoviesScreen
import com.example.sauloandroidpem.movies.ui.favourite.FavMoviesViewModel
import com.example.sauloandroidpem.movies.ui.home.HomeMovieViewModel
import com.example.sauloandroidpem.movies.ui.model.FavMovieModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationPage(
    homeMovieViewModel: HomeMovieViewModel,
    movieDetailViewModel: MovieDetailViewModel,
    favMoviesViewModel: FavMoviesViewModel
) {

    val tabItems = listOf("Films", "Favs")

    var selectedItem = remember { mutableStateOf(0) }

    var navController = rememberNavController()

    val navBackStackEntry = navController.currentBackStackEntryAsState()

    val parentRouteName = navBackStackEntry.value?.destination?.parent?.route

    var routeName = navBackStackEntry.value?.destination?.route

    Scaffold(topBar = {
        if (routeName == "Detalles/{name}") {
            routeName = "Detalles"
        }
        TopAppBar(title = { Text(text = "$routeName") })
    }, bottomBar = {
        NavigationBar() {
            tabItems.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = parentRouteName == item,
                    onClick = {
                        selectedItem.value = index
                        navController.navigate(item, navOptions {

                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }

                            launchSingleTop = true
                            restoreState = true

                        })
                    },
                    icon = {

                        when (item) {
                            "Films" -> Icon(
                                imageVector = Icons.Default.Movie,
                                contentDescription = "Films"
                            )
                            "Favs" -> Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "Favs"
                            )
                        }
                    },
                    label = { Text(text = item) })
            }
        }

    }) {
        Box(modifier = Modifier.padding(it)) {

            NavHost(navController = navController, startDestination = "Films") {

                navigation(startDestination = Routes.MoviesScreen.route, route = "Films") {
                    composable(Routes.MoviesScreen.route) {
                        HomeMovieScreen(navController = navController, homeMovieViewModel)
                    }
                    composable(
                        route = Routes.MovieDetailScreen.route,
                        arguments = listOf(navArgument("name") {
                            type = NavType.IntType
                        })
                    ) {
                        //Log.d("Args", it.arguments?.getString(SEARCH_OPTION_SCREEN_ARGUMENT_KEY).toString())
                        MovieDetailScreen(it.arguments?.getInt("name") ?: 0, movieDetailViewModel)
                    }
                }

                navigation(startDestination = Routes.FavMoviesScreen.route, route = "Favs") {
                    composable(Routes.FavMoviesScreen.route) {
                        FavMoviesScreen(favMoviesViewModel = favMoviesViewModel)
                    }
                }
            }
        }
    }
}