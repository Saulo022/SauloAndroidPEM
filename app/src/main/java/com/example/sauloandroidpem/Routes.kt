package com.example.sauloandroidpem

sealed class Routes(val route:String){
    object MoviesScreen:Routes("pantalla1")
    object MovieDetailScreen:Routes("pantalla2/{name}"){
        fun createRoute(name:Int) = "pantalla2/$name"
    }
}
