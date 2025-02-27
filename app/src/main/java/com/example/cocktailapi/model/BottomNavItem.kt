package com.example.cocktailapi.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    object SearchByName : BottomNavItem(Routes.SearchByNameScreen.route, "Buscar", Icons.Filled.Search)
    object SearchRandom : BottomNavItem(Routes.SearchRandomScreen.route, "Aleatorio", Icons.Filled.Info)
    object SearchByCategory : BottomNavItem(Routes.SearchByCategoryScreen.route, "Categoría", Icons.Filled.Build)
}