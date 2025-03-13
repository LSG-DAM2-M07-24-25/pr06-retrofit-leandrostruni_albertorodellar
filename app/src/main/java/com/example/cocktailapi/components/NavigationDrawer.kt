package com.example.cocktailapi.components

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.composables.icons.lucide.ClipboardList
import com.composables.icons.lucide.Drum
import com.composables.icons.lucide.Heart
import com.composables.icons.lucide.House
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Search
import com.example.cocktailapi.model.Routes
import com.example.cocktailapi.ui.theme.DarkGreen

/**
 * Menú lateral de navegación de la aplicación.
 *
 * Este menú proporciona acceso rápido a las principales pantallas de la aplicación.
 *
 * @param navController Controlador de navegación para manejar el cambio de pantallas.
 * @param onItemClick Acción a ejecutar cuando el usuario selecciona un elemento del menú.
 */
@Composable
fun NavigationDrawer(
    navController: NavController,
    onItemClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .width(280.dp)
            .fillMaxHeight()
            .background(DarkGreen)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                "Cocktail Finder",
                modifier = Modifier.padding(16.dp),
                color = Color.White,
                style = MaterialTheme.typography.titleLarge
            )
        }

        // Lista de elementos del menú de navegación
        LazyColumn(modifier = Modifier.padding(16.dp)) {
            item {
                HorizontalDivider(color = Color.White, thickness = 1.dp)
            }
            item {
                NavigationDrawerItem(
                    "Home",
                    Routes.MainViewScreen.route,
                    icon = Lucide.House,
                    navController,
                    onItemClick
                )
            }
            item {
                NavigationDrawerItem(
                    "Buscar por Nombre",
                    Routes.SearchByNameScreen.route,
                    icon = Lucide.Search,
                    navController,
                    onItemClick
                )
            }
            item {
                NavigationDrawerItem(
                    "Cóctel Aleatorio",
                    Routes.SearchRandomScreen.route,
                    icon = Lucide.Drum,
                    navController,
                    onItemClick
                )
            }
            item {
                NavigationDrawerItem(
                    "Buscar por Categoría",
                    Routes.SearchByCategoryScreen.route,
                    icon = Lucide.ClipboardList,
                    navController,
                    onItemClick
                )
            }
            item {
                NavigationDrawerItem(
                    "Favoritos",
                    Routes.FavoritesScreen.route,
                    icon = Lucide.Heart,
                    navController,
                    onItemClick
                )
            }
        }
    }
}

/**
 * Elemento de la lista del Drawer.
 *
 * Representa una opción dentro del menú de navegación lateral.
 *
 * @param label Texto del elemento de navegación.
 * @param route Ruta de navegación a la que redirige este elemento.
 * @param icon Icono representativo del elemento.
 * @param navController Controlador de navegación.
 * @param onClick Acción a ejecutar cuando se selecciona el elemento.
 */
@Composable
fun NavigationDrawerItem(
    label: String,
    route: String,
    icon: ImageVector,
    navController: NavController,
    onClick: () -> Unit
) {
    TextButton(
        onClick = {
            navController.navigate(route) {
                popUpTo(Routes.MainViewScreen.route) { inclusive = false }
                launchSingleTop = true
            }
            onClick()
        },
        modifier = Modifier.padding(8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = Color.White,
                modifier = Modifier.padding(end = 16.dp)
            )
            Text(label)
        }
    }
}