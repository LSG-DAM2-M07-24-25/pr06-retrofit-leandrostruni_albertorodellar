package com.example.cocktailapi.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.cocktailapi.ui.theme.SoftGold
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
    val filteredFavorites by cocktailViewModel.filteredFavorites.observeAsState(emptyList())
    val searchHistory by cocktailViewModel.searchHistory.observeAsState(emptyList())

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
            searchHistory.lastOrNull()?.let {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable {
                            cocktailViewModel.onSearchTextChange(it)
                        },
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)

                ) {
                        Text(
                            text = it,
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.bodyMedium
                        )
                }
            }
            Box(
                modifier = Modifier.weight(1f)
            ) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(filteredFavorites) { cocktail ->
                        val drink = cocktail.toDrink()
                        CocktailItem(drink, navController, apiViewModel, cocktailViewModel)
                    }
                }
            }
        } else {
            Text(
                "No hay cocktails en favoritos", style = MaterialTheme.typography.bodyLarge,
                color = SoftGold
            )
        }
    }
}