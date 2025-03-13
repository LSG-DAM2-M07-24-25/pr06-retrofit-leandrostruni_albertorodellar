package com.example.cocktailapi.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
    }

    val favorites by cocktailViewModel.favorites.observeAsState(emptyList())
    val filteredFavorites by cocktailViewModel.filteredFavorites.observeAsState(emptyList())

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

            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                if (isExpandedScreen) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(4),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        items(filteredFavorites.size) { index ->
                            val cocktail = filteredFavorites[index]
                            val drink = cocktail.toDrink()
                            CocktailItem(drink, navController, apiViewModel, cocktailViewModel)
                        }
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(filteredFavorites) { cocktail ->
                            val drink = cocktail.toDrink()
                            CocktailItem(drink, navController, apiViewModel, cocktailViewModel)
                        }

                        if (filteredFavorites.isEmpty()) {
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