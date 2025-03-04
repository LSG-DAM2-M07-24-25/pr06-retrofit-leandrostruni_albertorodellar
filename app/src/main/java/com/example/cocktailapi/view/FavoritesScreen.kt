package com.example.cocktailapi.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cocktailapi.components.CocktailItem
import com.example.cocktailapi.viewmodel.APIViewModel
import com.example.cocktailapi.viewmodel.CocktailViewModel

@Composable
fun FavoritesScreen(
    navController: NavController,
    apiViewModel: APIViewModel,
    cocktailViewModel: CocktailViewModel
) {
    // Llamar a getFavorites() cuando la pantalla se monta
    LaunchedEffect(Unit) {
        cocktailViewModel.getFavorites()
    }

    val favorites by cocktailViewModel.favorites.observeAsState(emptyList())

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("Cocktails Favoritos", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        if (favorites.isNotEmpty()) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(favorites) { cocktail ->
                    val drink = cocktail.toDrink()
                    CocktailItem(drink, navController, apiViewModel, cocktailViewModel)
                }
            }
        } else {
            Text("No hay favoritos", style = MaterialTheme.typography.bodyLarge)
        }
    }
}