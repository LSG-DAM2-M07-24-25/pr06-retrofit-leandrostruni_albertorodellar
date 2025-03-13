package com.example.cocktailapi.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cocktailapi.R
import com.example.cocktailapi.components.CustomButton
import com.example.cocktailapi.model.Routes
import com.example.cocktailapi.ui.theme.DarkGreen
import com.example.cocktailapi.ui.theme.White
import com.example.cocktailapi.ui.theme.LightGreen
import com.example.cocktailapi.ui.theme.SoftGold

/**
 * Pantalla principal de la aplicación con opciones de búsqueda y exploración.
 *
 * @param navController Controlador de navegación.
 * @param isExpandedScreen Indica si la pantalla es grande para ajustar el diseño.
 */
@Composable
fun MainViewScreen(
    navController: NavController,
    isExpandedScreen: Boolean
) {

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGreen)
            .padding(16.dp),
    ) {
        val maxWidthDp = this.maxWidth
        if (isExpandedScreen) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically // Esto centra los elementos verticalmente en la Row

            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_app),
                        contentDescription = "Cocktail Glass",
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(8.dp)
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Organizar los botones en dos columnas
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        CustomButton(
                            text = "Buscar por nombre",
                            onClick = { navController.navigate(Routes.SearchByNameScreen.route) },
                            isExpandedScreen = isExpandedScreen,
                            backgroundColor = LightGreen,
                            textColor = White,
                            modifier = Modifier.weight(1f)
                        )
                        CustomButton(
                            text = "Random Cocktail",
                            onClick = { navController.navigate(Routes.SearchRandomScreen.route) },
                            isExpandedScreen,
                            backgroundColor = SoftGold,
                            textColor = Color.Black,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        CustomButton(
                            text = "Buscar por categoría",
                            onClick = { navController.navigate(Routes.SearchByCategoryScreen.route) },
                            isExpandedScreen,
                            backgroundColor = LightGreen,
                            textColor = White,
                            modifier = Modifier.weight(1f)
                        )
                        CustomButton(
                            text = "Favoritos",
                            onClick = { navController.navigate(Routes.FavoritesScreen.route) },
                            isExpandedScreen,
                            backgroundColor = SoftGold,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        } else {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(DarkGreen)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Image(
                        painter = painterResource(id = R.drawable.logo_app),
                        contentDescription = "Cocktail Glass",
                        modifier = Modifier
                            .fillMaxWidth(if (maxWidthDp < 600.dp) 0.8f else 0.5f)
                            .padding(8.dp)
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(24.dp))
                }

                item {
                    // Botón Buscar por Nombre
                    CustomButton(
                        text = "Buscar por nombre",
                        onClick = { navController.navigate(Routes.SearchByNameScreen.route) },
                        isExpandedScreen = isExpandedScreen,
                        backgroundColor = LightGreen,
                        textColor = White,
                        modifier = Modifier.fillMaxWidth(if (maxWidthDp < 600.dp) 0.8f else 0.5f)
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(12.dp))
                }

                item {
                    // Botón Cóctel Aleatorio
                    CustomButton(
                        text = "Random Cocktail",
                        onClick = { navController.navigate(Routes.SearchRandomScreen.route) },
                        isExpandedScreen,
                        backgroundColor = SoftGold,
                        textColor = Color.Black,
                        modifier = Modifier.fillMaxWidth(if (maxWidthDp < 600.dp) 0.8f else 0.5f)
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(12.dp))
                }

                item {
                    // Botón Buscar por Categoría
                    CustomButton(
                        text = "Buscar por categoría",
                        onClick = { navController.navigate(Routes.SearchByCategoryScreen.route) },
                        isExpandedScreen,
                        backgroundColor = LightGreen,
                        textColor = White,
                        modifier = Modifier.fillMaxWidth(if (maxWidthDp < 600.dp) 0.8f else 0.5f)
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(12.dp))
                }

                item {
                    // Botón Favoritos
                    CustomButton(
                        text = "Favoritos",
                        onClick = { navController.navigate(Routes.FavoritesScreen.route) },
                        isExpandedScreen,
                        backgroundColor = SoftGold,
                        modifier = Modifier.fillMaxWidth(if (maxWidthDp < 600.dp) 0.8f else 0.5f)
                    )
                }
            }
        }
    }
}