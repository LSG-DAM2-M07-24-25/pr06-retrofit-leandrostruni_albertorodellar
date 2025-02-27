package com.example.cocktailapi.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cocktailapi.components.CocktailItemDetails
import com.example.cocktailapi.viewmodel.APIViewModel
import com.example.cocktailapi.viewmodel.CocktailViewModel

@Composable
fun DetailsScreen(
    navController: NavController,
    apiViewModel: APIViewModel,
    cocktailViewModel: CocktailViewModel
){
    val selectedCocktailId by cocktailViewModel.selectedCocktailId.observeAsState()
    val selectedCocktail = apiViewModel.cocktailData.value?.drinks?.find { it.idDrink == selectedCocktailId }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        selectedCocktail?.let { drink ->
            CocktailItemDetails(drink)
        } ?: Text("Cargando datos del cóctel...", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text("Volver")
        }
    }
}