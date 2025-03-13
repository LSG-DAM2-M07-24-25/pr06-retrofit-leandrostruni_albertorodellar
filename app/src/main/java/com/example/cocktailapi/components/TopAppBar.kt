package com.example.cocktailapi.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import com.composables.icons.lucide.ArrowLeft
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Menu
import com.composables.icons.lucide.Settings
import com.example.cocktailapi.model.Routes
import com.example.cocktailapi.ui.theme.DarkerGreen
import com.example.cocktailapi.ui.theme.White

/**
 * Barra superior de la aplicación.
 *
 * Muestra el título de la pantalla actual y permite la navegación hacia atrás
 * o la apertura del menú lateral si está en una pantalla grande.
 *
 * @param title Título de la pantalla actual.
 * @param navController Controlador de navegación para gestionar el cambio de pantallas.
 * @param isExpandedScreen Indica si la pantalla es grande, para ajustar el comportamiento del botón de navegación.
 * @param onMenuClick Acción que se ejecuta cuando el usuario presiona el botón del menú lateral.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    title: String,
    navController: NavController,
    isExpandedScreen: Boolean = false,
    onMenuClick: (() -> Unit)? = null
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    CenterAlignedTopAppBar(
        //modifier = Modifier.height(72.dp),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = DarkerGreen,
            titleContentColor = Color.White,
        ),
        title = {
            Text(
                title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            if (isExpandedScreen) {
                IconButton(onClick = { onMenuClick?.invoke() }) {
                    Icon(
                        imageVector = Lucide.Menu,
                        contentDescription = "Menú",
                        tint = White
                    )
                }
            } else {
                IconButton(onClick = {
                    val currentRoute = navController.currentBackStackEntry?.destination?.route
                    when (currentRoute) {
                        Routes.DetailsScreen.route -> {
                            navController.popBackStack()
                        }

                        Routes.MainViewScreen.route -> {
                            // Si estamos en MainViewScreen, implementar salir de la App
                        }

                        else -> {
                            if (navController.previousBackStackEntry != null) {
                                navController.popBackStack()
                            } else {
                                navController.navigate(Routes.MainViewScreen.route) {
                                    popUpTo(Routes.MainViewScreen.route) { inclusive = true }
                                }
                            }
                        }
                    }
            }) {
                Icon(
                    imageVector = Lucide.ArrowLeft,
                    contentDescription = "Volver",
                    tint = White
                )
                }
            }
        },
        actions = {
            IconButton(onClick = { /* Acción de settings, cambiar idioma */ }) {
                Icon(
                    imageVector = Lucide.Settings,
                    contentDescription = "Settings"
                )
            }
        },
        scrollBehavior = scrollBehavior,
    )
}