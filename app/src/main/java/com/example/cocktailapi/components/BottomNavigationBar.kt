package com.example.cocktailapi.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.cocktailapi.model.BottomNavItem
import com.example.cocktailapi.model.Routes
import com.example.cocktailapi.ui.theme.DarkerGreen

/**
 * Barra de navegación inferior de la aplicación.
 *
 * Muestra los elementos de navegación definidos en [BottomNavItem].
 * Se oculta automáticamente en pantallas grandes (cuando [isExpandedScreen] es `true`).
 *
 * @param navController Controlador de navegación que gestiona la navegación entre pantallas.
 * @param isExpandedScreen Indica si la pantalla es grande. Si es `true`, la barra de navegación no se muestra.
 */
@Composable
fun BottomNavigationBar(
    navController: NavController,
    isExpandedScreen: Boolean = false
) {

    // Si la pantalla es grande, se oculta la barra de navegación inferior.
    if (isExpandedScreen) return

    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.SearchByName,
        BottomNavItem.SearchRandom,
        BottomNavItem.SearchByCategory,
        BottomNavItem.Favorites
    )

    NavigationBar(
        tonalElevation = 8.dp,
        containerColor = DarkerGreen
    ) {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry.value?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label,color = Color.White) },
                selected = currentRoute == item.route,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Black,
                    unselectedIconColor = Color.White,
                    indicatorColor = Color.White
                ),
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(Routes.MainViewScreen.route) {
                                inclusive = false
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}