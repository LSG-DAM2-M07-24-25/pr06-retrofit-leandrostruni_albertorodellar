package com.example.cocktailapi.components

import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.composables.icons.lucide.ArrowLeft
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Menu
import com.composables.icons.lucide.Settings
import com.example.cocktailapi.model.Routes
import com.example.cocktailapi.ui.theme.DarkGreen
import com.example.cocktailapi.ui.theme.DarkerGreen
import com.example.cocktailapi.ui.theme.SoftGold

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
                        tint = SoftGold
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
                        tint = SoftGold
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