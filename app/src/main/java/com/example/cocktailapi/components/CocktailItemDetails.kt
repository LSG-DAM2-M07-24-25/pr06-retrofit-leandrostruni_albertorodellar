package com.example.cocktailapi.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.cocktailapi.model.Drink

@Composable
fun CocktailItemDetails(cocktail: Drink, isExpandedScreen: Boolean) {
    // Función para generar las listas de ingredientes y medidas
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
    // Función para mostrar los ingredientes y medidas
    @Composable
    fun Ingredientes() {
        if (cocktail.strIngredient1 != null) {
            Text("Ingredientes:", style = MaterialTheme.typography.bodyLarge)
            ingredientes.forEachIndexed { index, ingredient ->
                val medida = medidas.getOrNull(index) ?: ""
                Text(" - $medida $ingredient", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }

    if (isExpandedScreen) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            cocktail.strDrinkThumb?.let {
                Image(
                    painter = rememberAsyncImagePainter(it),
                    contentDescription = cocktail.strDrink,
                    modifier = Modifier
                        .weight(1f)
                        .height(250.dp)
                        .padding(bottom = 16.dp),
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    cocktail.strDrink,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text("Categoría: ${cocktail.strCategory}", style = MaterialTheme.typography.bodyLarge)
                Text("Tipo: ${cocktail.strAlcoholic}", style = MaterialTheme.typography.bodyLarge)
                Text("Vaso: ${cocktail.strGlass}", style = MaterialTheme.typography.bodyLarge)

                cocktail.strInstructions?.let {
                    Text("Instrucciones: $it", style = MaterialTheme.typography.bodyMedium)
                }

                Ingredientes()
            }
        }
    } else {
        Column(modifier = Modifier.padding(16.dp)) {
            cocktail.strDrinkThumb?.let {
                Image(
                    painter = rememberAsyncImagePainter(it),
                    contentDescription = cocktail.strDrink,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .padding(bottom = 16.dp),
                )
            }

            Text(
                cocktail.strDrink,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
            )
            Text("Categoría: ${cocktail.strCategory}", style = MaterialTheme.typography.bodyLarge)
            Text("Tipo: ${cocktail.strAlcoholic}", style = MaterialTheme.typography.bodyLarge)
            Text("Vaso: ${cocktail.strGlass}", style = MaterialTheme.typography.bodyLarge)

            cocktail.strInstructions?.let {
                Text("Instrucciones: $it", style = MaterialTheme.typography.bodyMedium)
            }

            Ingredientes()
        }
    }
}
