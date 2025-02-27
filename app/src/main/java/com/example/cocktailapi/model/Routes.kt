package com.example.cocktailapi.model

sealed class Routes(val route: String ) {
    object LaunchScreen: Routes("launchScreen")
    object MainViewScreen: Routes("mainViewScreen")
    object SearchByNameScreen : Routes("searchByNameScreen")
    object SearchRandomScreen : Routes("searchRandomScreen")
    object SearchByCategoryScreen : Routes("searchByCategory")
    object DetailsScreen : Routes("detailsScreen")
    object FavoritesScreen: Routes("favoritesScreen")
}