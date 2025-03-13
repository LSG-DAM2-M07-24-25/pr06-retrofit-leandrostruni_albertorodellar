package com.example.cocktailapi.model

/**
 * Clase sellada que define las rutas de navegación dentro de la aplicación.
 *
 * Cada objeto representa una pantalla dentro de la app y su ruta correspondiente.
 *
 * @property route Cadena de texto que define la ruta de navegación.
 */
sealed class Routes(val route: String ) {
    object LaunchScreen: Routes("launchScreen")
    object MainViewScreen: Routes("mainViewScreen")
    object SearchByNameScreen : Routes("searchByNameScreen")
    object SearchRandomScreen : Routes("searchRandomScreen")
    object SearchByCategoryScreen : Routes("searchByCategory")
    object DetailsScreen : Routes("detailsScreen")
    object FavoritesScreen: Routes("favoritesScreen")
}