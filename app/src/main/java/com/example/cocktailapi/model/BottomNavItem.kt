package com.example.cocktailapi.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.composables.icons.lucide.ClipboardList
import com.composables.icons.lucide.Drum
import com.composables.icons.lucide.Heart
import com.composables.icons.lucide.House
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Search

/**
 * Representa los elementos de la barra de navegación inferior de la aplicación.
 *
 * Cada objeto en esta clase sellada define una ruta, una etiqueta y un ícono
 * asociado con una pantalla de la aplicación.
 *
 * @param route Ruta de navegación asociada al elemento de la barra.
 * @param label Etiqueta visible en la barra de navegación.
 * @param icon Ícono representativo del elemento.
 */
sealed class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    object SearchByName : BottomNavItem(Routes.SearchByNameScreen.route, "Buscar", Lucide.Search)
    object SearchRandom : BottomNavItem(Routes.SearchRandomScreen.route, "Aleatorio", Lucide.Drum)
    object SearchByCategory : BottomNavItem(Routes.SearchByCategoryScreen.route, "Categoría", Lucide.ClipboardList)
    object Favorites : BottomNavItem(Routes.FavoritesScreen.route, "Favoritos", Lucide.Heart)
    object Home : BottomNavItem(Routes.MainViewScreen.route, "Home", Lucide.House)
}