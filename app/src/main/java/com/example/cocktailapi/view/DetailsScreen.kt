package com.example.cocktailapi.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cocktailapi.components.CocktailItemDetails
import com.example.cocktailapi.ui.theme.DarkGreen
import com.example.cocktailapi.ui.theme.DarkerGreen
import com.example.cocktailapi.ui.theme.White
import com.example.cocktailapi.viewmodel.APIViewModel
import com.example.cocktailapi.viewmodel.CocktailViewModel

/**
 * Pantalla de detalles de un cóctel.
 *
 * Muestra la información detallada de un cóctel seleccionado, incluyendo ingredientes,
 * instrucciones y la opción de agregarlo a favoritos.
 *
 * @param navController Controlador de navegación.
 * @param apiViewModel ViewModel para gestionar las solicitudes a la API.
 * @param cocktailViewModel ViewModel que maneja la lógica de los cócteles.
 * @param isExpandedScreen Indica si la pantalla es grande para ajustar la UI.
 */
@Composable
fun DetailsScreen(
    navController: NavController,
    apiViewModel: APIViewModel,
    cocktailViewModel: CocktailViewModel,
    isExpandedScreen: Boolean
) {
    val scrollState = rememberScrollState()
    val selectedCocktailId by cocktailViewModel.selectedCocktailId.observeAsState()
    var isFavorite by remember { mutableStateOf(false) }
    var isLoadingFavorite by remember { mutableStateOf(false) }

    val cocktailData by apiViewModel.cocktailData.observeAsState()
    val selectedCocktail = cocktailData?.drinks?.find { it.idDrink == selectedCocktailId }

    // Limpiar la lista solo cuando se sale de la pantalla
    DisposableEffect(navController) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            if (destination.route != "detailsScreen") {
                apiViewModel.clearCocktailData()
            }
        }
        navController.addOnDestinationChangedListener(listener)
        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }

    LaunchedEffect(selectedCocktailId) {
        selectedCocktailId?.let { id ->
            apiViewModel.getCocktailById(id)
            isFavorite = cocktailViewModel.isFavorite(id)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGreen)
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        selectedCocktail?.let { drink ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(16.dp)),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White, contentColor = Color.Black)
            )
            {
                IconButton(
                    onClick = {
                        isLoadingFavorite = true
                        val drinkToUpdate = drink.toDrinkEntity(isFavorite = !isFavorite)
                        if (!isFavorite) {
                            cocktailViewModel.addFavorite(drinkToUpdate.toDrink())  // Añadimos a favoritos
                            isLoadingFavorite = false
                        } else {
                            cocktailViewModel.removeFavorite(drinkToUpdate.toDrink())  // Eliminamos de favoritos
                            isLoadingFavorite = false
                        }
                        isFavorite = !isFavorite
                    },
                    enabled = !isLoadingFavorite,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.End)
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (isFavorite) Color.Red else Color.Gray,
                        modifier = Modifier.size(48.dp)
                    )
                }
                CocktailItemDetails(drink, isExpandedScreen)
            }
        } ?: Text(
            "Cargando datos del cóctel...",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White
        )

        if (isExpandedScreen) {
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.popBackStack() }) {
                Text("Volver")
            }
        }
    }
}