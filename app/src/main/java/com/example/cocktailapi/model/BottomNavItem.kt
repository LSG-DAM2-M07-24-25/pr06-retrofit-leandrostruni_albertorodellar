package com.example.cocktailapi.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.composables.icons.lucide.ClipboardList
import com.composables.icons.lucide.Drum
import com.composables.icons.lucide.Heart
import com.composables.icons.lucide.House
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Search

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