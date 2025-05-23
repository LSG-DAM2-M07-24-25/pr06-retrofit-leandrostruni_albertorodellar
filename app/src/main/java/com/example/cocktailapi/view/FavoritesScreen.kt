package com.example.cocktailapi.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cocktailapi.components.CocktailItem
import com.example.cocktailapi.components.CocktailSearchBar
import com.example.cocktailapi.ui.theme.DarkGreen
import com.example.cocktailapi.ui.theme.White
import com.example.cocktailapi.viewmodel.APIViewModel
import com.example.cocktailapi.viewmodel.CocktailViewModel

/**
 * Pantalla de favoritos donde se muestran los cócteles marcados como favoritos por el usuario.
 *
 * @param navController Controlador de navegación.
 * @param apiViewModel ViewModel que maneja las llamadas a la API.
 * @param cocktailViewModel ViewModel que maneja la lógica de los cócteles.
 * @param isExpandedScreen Indica si la pantalla es grande para ajustar el diseño.
 */
@Composable
fun FavoritesScreen(
    navController: NavController,
    apiViewModel: APIViewModel,
    cocktailViewModel: CocktailViewModel,
    isExpandedScreen: Boolean
) {
    // Llamar a getFavorites() cuando la pantalla se monta
    LaunchedEffect(Unit) {
        cocktailViewModel.getFavorites()
        cocktailViewModel.clearHistory()
    }

    val favorites by cocktailViewModel.favorites.observeAsState(emptyList())
    val searchedCocktails by cocktailViewModel.searchedCocktails.observeAsState(emptyList())

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .background(DarkGreen)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (favorites.isNotEmpty()) {

            CocktailSearchBar(cocktailViewModel)
            Spacer(Modifier.height(8.dp))
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                if (isExpandedScreen) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(4),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        items(searchedCocktails.size) { index ->
                            val cocktail = searchedCocktails[index]
                            CocktailItem(cocktail, navController, cocktailViewModel)
                        }
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        items(searchedCocktails) { cocktail ->
                            CocktailItem(cocktail, navController, cocktailViewModel)
                        }

                        if (searchedCocktails.isEmpty()) {
                            item {
                                Spacer(modifier = Modifier.padding(16.dp))
                                Text(
                                    "No se encontraron resultados",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = White
                                )
                            }
                        }
                    }
                }
            }
        } else {
            Text(
                "No hay cocktails en favoritos", style = MaterialTheme.typography.bodyLarge,
                color = White
            )
        }
    }
}