package com.example.sauloandroidpem

sealed class Routes(val route:String){
    object MoviesScreen:Routes("Peliculas Populares")
    object MovieDetailScreen:Routes(route = "Detalles/{name}"){
        fun createRoute(name:Int) = "Detalles/$name"
    }

    object FavMoviesScreen:Routes("Mi Lista")
}
