package com.example.marsroverapi.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.marsroverapi.model.Drink
import com.example.marsroverapi.viewmodel.APIViewModel

@Composable
fun DebugViewScreen(apiViewModel: APIViewModel) {
    val cocktailData by apiViewModel.cocktailData.observeAsState()
    val loading by apiViewModel.loading.observeAsState(initial = true)
    var cocktailName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TextField(
            value = cocktailName,
            onValueChange = { cocktailName = it },
            label = { Text("Buscar Cocktail") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { apiViewModel.searchCocktail(cocktailName) },
            modifier = Modifier.fillMaxWidth()
        )
        {
            Text("Buscar Cocktail")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { apiViewModel.fetchRandomCocktail() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cocktail Aleatorio")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (loading) {
            CircularProgressIndicator()
        } else {
            cocktailData?.drinks?.let { drinks ->
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(drinks) { cocktail ->
                        CocktailItem(cocktail)
                    }
                }
            } ?: Text("No hay datos disponibles")
        }
    }
}

@Composable
fun CocktailItem(cocktail: Drink) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Nombre: ${cocktail.strDrink}", style = MaterialTheme.typography.titleLarge)
            Text("Categoría: ${cocktail.strCategory}", style = MaterialTheme.typography.bodyLarge)
            Text("Tipo: ${cocktail.strAlcoholic}", style = MaterialTheme.typography.bodyLarge)
            Text("Vaso: ${cocktail.strGlass}", style = MaterialTheme.typography.bodyLarge)
            Text("Instrucciones: ${cocktail.strInstructions}", style = MaterialTheme.typography.bodyMedium)

            // Ingredientes y medidas
            val ingredientes = listOfNotNull(
                cocktail.strIngredient1, cocktail.strIngredient2, cocktail.strIngredient3,
                cocktail.strIngredient4, cocktail.strIngredient5, cocktail.strIngredient6,
                cocktail.strIngredient7, cocktail.strIngredient8, cocktail.strIngredient9,
                cocktail.strIngredient10, cocktail.strIngredient11, cocktail.strIngredient12,
                cocktail.strIngredient13, cocktail.strIngredient14, cocktail.strIngredient15
            )

            val medidas = listOfNotNull(
                cocktail.strMeasure1, cocktail.strMeasure2, cocktail.strMeasure3,
                cocktail.strMeasure4, cocktail.strMeasure5, cocktail.strMeasure6,
                cocktail.strMeasure7, cocktail.strMeasure8, cocktail.strMeasure9,
                cocktail.strMeasure10, cocktail.strMeasure11, cocktail.strMeasure12,
                cocktail.strMeasure13, cocktail.strMeasure14, cocktail.strMeasure15
            )

            Text("Ingredientes:", style = MaterialTheme.typography.bodyLarge)
            ingredientes.forEachIndexed { index, ingredient ->
                val medida = medidas.getOrNull(index) ?: ""
                Text(" - $medida $ingredient", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}