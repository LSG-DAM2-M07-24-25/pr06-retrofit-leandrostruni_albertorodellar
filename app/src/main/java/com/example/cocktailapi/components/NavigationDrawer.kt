package com.example.cocktailapi.components

import android.R
import android.R.attr.thickness
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cocktailapi.model.Routes
import com.example.cocktailapi.ui.theme.DarkGreen


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
            modifier = Modifier.fillMaxWidth()
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


        LazyColumn(modifier = Modifier.padding(16.dp)) {
            item {
                HorizontalDivider(color = Color.White, thickness = 1.dp)
            }
            item {
                NavigationDrawerItem(
                    "Home",
                    Routes.MainViewScreen.route,
                    navController,
                    onItemClick
                )
            }
            item {
                NavigationDrawerItem(
                    "Buscar por Nombre",
                    Routes.SearchByNameScreen.route,
                    navController,
                    onItemClick
                )
            }
            item {
                NavigationDrawerItem(
                    "Cóctel Aleatorio",
                    Routes.SearchRandomScreen.route,
                    navController,
                    onItemClick
                )
            }
            item {
                NavigationDrawerItem(
                    "Buscar por Categoría",
                    Routes.SearchByCategoryScreen.route,
                    navController,
                    onItemClick
                )
            }
            item {
                NavigationDrawerItem(
                    "Favoritos",
                    Routes.FavoritesScreen.route,
                    navController,
                    onItemClick
                )
            }
        }
    }
}

@Composable
fun NavigationDrawerItem(
    label: String,
    route: String,
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
        Text(label)
    }
}
