package com.example.cocktailapi.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import com.example.cocktailapi.ui.theme.NavyBlue
import com.example.cocktailapi.ui.theme.SoftGold
import okhttp3.Route

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.SearchByName,
        BottomNavItem.SearchRandom,
        BottomNavItem.SearchByCategory,
        BottomNavItem.Favorites
    )

    NavigationBar(
        tonalElevation = 8.dp,
        containerColor = NavyBlue
    ) {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry.value?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label,color = SoftGold) },
                selected = currentRoute == item.route,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = SoftGold,
                    unselectedIconColor = Color.Gray
                ),
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(Routes.MainViewScreen.route) { inclusive = false }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}