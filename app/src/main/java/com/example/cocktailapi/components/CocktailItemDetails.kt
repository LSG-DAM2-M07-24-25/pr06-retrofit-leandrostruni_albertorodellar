package com.example.cocktailapi.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.cocktailapi.model.Drink

@Composable
fun CocktailItemDetails(cocktail: Drink) {
    Column(modifier = Modifier.padding(16.dp)) {
        cocktail.strDrinkThumb?.let {
            Image(
                painter = rememberAsyncImagePainter(it),
                contentDescription = cocktail.strDrink,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(bottom = 16.dp)
            )
        }

        Text("Nombre: ${cocktail.strDrink}", style = MaterialTheme.typography.titleLarge)
        Text("Categoría: ${cocktail.strCategory}", style = MaterialTheme.typography.bodyLarge)
        Text("Tipo: ${cocktail.strAlcoholic}", style = MaterialTheme.typography.bodyLarge)
        Text("Vaso: ${cocktail.strGlass}", style = MaterialTheme.typography.bodyLarge)

        cocktail.strInstructions?.let {
            Text("Instrucciones: $it", style = MaterialTheme.typography.bodyMedium)
        }

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

        cocktail.strIngredient1?.let {
            Text("Ingredientes:", style = MaterialTheme.typography.bodyLarge)
            ingredientes.forEachIndexed { index, ingredient ->
                val medida = medidas.getOrNull(index) ?: ""
                Text(" - $medida $ingredient", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}