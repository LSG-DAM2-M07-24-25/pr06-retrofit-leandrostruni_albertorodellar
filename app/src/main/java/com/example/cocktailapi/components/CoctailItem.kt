package com.example.cocktailapi.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.cocktailapi.model.Drink
import com.example.cocktailapi.model.Routes
import com.example.cocktailapi.ui.theme.DarkerGreen
import com.example.cocktailapi.ui.theme.White
import com.example.cocktailapi.viewmodel.APIViewModel
import com.example.cocktailapi.viewmodel.CocktailViewModel

@Composable
fun CocktailItem(
    drink: Drink,
    navController: NavController,
    apiViewModel: APIViewModel,
    cocktailViewModel: CocktailViewModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                cocktailViewModel.selectCocktail(drink.idDrink)
                navController.navigate(Routes.DetailsScreen.route)
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = DarkerGreen)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            drink.strDrinkThumb?.let {
                Image(
                    painter = rememberAsyncImagePainter(it),
                    contentDescription = drink.strDrink,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                )
            }
            Text(
                "${drink.strDrink}",
                style = MaterialTheme.typography.titleLarge,
                color = White
            )
            drink.strCategory?.let {
                Text(
                    "Categoría: ${drink.strCategory}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )
            }
            drink.strAlcoholic?.let{
                Text(
                    "Tipo: ${drink.strAlcoholic}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )
            }
        }
    }
}