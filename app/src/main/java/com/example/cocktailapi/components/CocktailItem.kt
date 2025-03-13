package com.example.cocktailapi.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import com.example.cocktailapi.ui.theme.DarkGray
import com.example.cocktailapi.ui.theme.DarkerGreen
import com.example.cocktailapi.ui.theme.White
import com.example.cocktailapi.viewmodel.CocktailViewModel

@Composable
fun CocktailItem(
    drink: Drink,
    navController: NavController,
    cocktailViewModel: CocktailViewModel,
    isExpandedScreen: Boolean = false
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                cocktailViewModel.selectCocktail(drink.idDrink)
                navController.navigate(Routes.DetailsScreen.route)
            }
            .border(2.dp, DarkGray, shape = MaterialTheme.shapes.medium),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = DarkerGreen)
    )
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f)
        ) {
            if (isExpandedScreen) {
                // Row con dos columnas, una la imagen y la otra el texto
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    drink.strDrinkThumb?.let {
                        Image(
                            painter = rememberAsyncImagePainter(it),
                            contentDescription = drink.strDrink,
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .height(150.dp)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            drink.strDrink,
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
                        drink.strAlcoholic?.let {
                            Text(
                                "Tipo: ${drink.strAlcoholic}",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.White
                            )
                        }
                        drink.strGlass?.let {
                            Text(
                                "Vaso: ${drink.strGlass}",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.White
                            )
                        }
                    }
                }
            } else {
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
                        drink.strDrink,
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
                    drink.strAlcoholic?.let {
                        Text(
                            "Tipo: ${drink.strAlcoholic}",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White
                        )
                    }
                }
            }

        }
    }
}