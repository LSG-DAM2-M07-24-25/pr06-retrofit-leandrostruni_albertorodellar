package com.example.marsroverapi.model

sealed class Routes(val route: String ) {
    object LaunchScreen: Routes("launchScreen")
    object MainViewScreen: Routes("mainViewScreen")
}