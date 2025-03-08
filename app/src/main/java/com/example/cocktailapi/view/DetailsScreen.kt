package com.example.cocktailapi.view

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cocktailapi.components.CocktailItemDetails
import com.example.cocktailapi.model.Drink
import com.example.cocktailapi.model.DrinkEntity
import com.example.cocktailapi.viewmodel.APIViewModel
import com.example.cocktailapi.viewmodel.CocktailViewModel

@Composable
fun DetailsScreen(
    navController: NavController,
    apiViewModel: APIViewModel,
    cocktailViewModel: CocktailViewModel
){
    val selectedCocktailId by cocktailViewModel.selectedCocktailId.observeAsState()
    
    LaunchedEffect(selectedCocktailId) {
        selectedCocktailId?.let { id ->
            apiViewModel.getCocktailById(id)
        }
    }

    val selectedCocktail = apiViewModel.cocktailData.value?.drinks?.find { it.idDrink == selectedCocktailId }
    
    // Comprobamos si el cóctel es favorito
    selectedCocktail?.let { cocktailViewModel.isFavorite(it.idDrink) }
    val isFavorite: Boolean by cocktailViewModel.isFavorite.observeAsState(false)
    Log.d("DetailsScreen", "isFavorite inicial: $isFavorite")
    val isFavoriteState by rememberUpdatedState(isFavorite)

    var isLoadingFavorite by remember { mutableStateOf(false) }
    LaunchedEffect(selectedCocktail) {
        selectedCocktail?.let {
            cocktailViewModel.isFavorite(it.idDrink)
        }
    }
    val scrollState = rememberScrollState()
  
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        selectedCocktail?.let { drink ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                IconButton(
                    onClick = {
                        isLoadingFavorite = true
                        val drinkToUpdate = drink.toDrinkEntity(isFavorite = !isFavorite)
                        Log.d("DetailsScreen", "drinkToUpdate: $drinkToUpdate")
                        if (!isFavorite) {
                            cocktailViewModel.addFavorite(drinkToUpdate.toDrink())  // Añadimos a favoritos
                            isLoadingFavorite = false
                        } else {
                            cocktailViewModel.removeFavorite(drinkToUpdate.toDrink())  // Eliminamos de favoritos
                            isLoadingFavorite = false
                        }
                        Log.d("DetailsScreen", "isFavorite final: $isFavorite")
                        cocktailViewModel.isFavorite(drinkToUpdate.idDrink)
                    },
                    enabled = !isLoadingFavorite,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.End)
                ) {
                    Icon(
                        imageVector = if (isFavoriteState) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (isFavorite) Color.Red else Color.Gray,
                        modifier = Modifier.size(48.dp)
                    )
                }
                CocktailItemDetails(drink)
            }
        } ?: Text("Cargando datos del cóctel...", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text("Volver")
        }
    }
}