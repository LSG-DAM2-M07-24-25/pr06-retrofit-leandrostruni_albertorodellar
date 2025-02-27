package com.example.cocktailapi.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cocktailapi.components.CocktailItem
import com.example.cocktailapi.viewmodel.APIViewModel
import com.example.cocktailapi.viewmodel.CocktailViewModel

@Composable
fun CocktailByCategoryScreen(
    navController: NavController,
    apiViewModel: APIViewModel,
    cocktailViewModel: CocktailViewModel,

) {
    var isOrdinaryDrinkChecked by remember { mutableStateOf(false) }
    var isCocktailChecked by remember { mutableStateOf(false) }
    val cocktailData by cocktailViewModel.cocktailData.observeAsState()
    val loading by cocktailViewModel.loading.observeAsState(initial = false)

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Filtrar por categoría", style = MaterialTheme.typography.headlineSmall)

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Checkbox(
                checked = isOrdinaryDrinkChecked,
                onCheckedChange = { checked ->
                    isOrdinaryDrinkChecked = checked
                    cocktailViewModel.fetchFilteredCocktails(
                        isOrdinaryDrinkChecked,
                        isCocktailChecked
                    )
                }
            )
            Text("Ordinary Drink", modifier = Modifier.padding(start = 8.dp))
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Checkbox(
                checked = isCocktailChecked,
                onCheckedChange = { checked ->
                    isCocktailChecked = checked
                    cocktailViewModel.fetchFilteredCocktails(
                        isOrdinaryDrinkChecked,
                        isCocktailChecked
                    )
                }
            )
            Text("Cocktail", modifier = Modifier.padding(start = 8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (loading) {
            CircularProgressIndicator()
        } else {
            cocktailData?.drinks?.let { drinks ->
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(drinks) { cocktail ->
                        CocktailItem(cocktail, navController, apiViewModel, cocktailViewModel)
                    }
                }
            } ?: Text("No hay resultados", style = MaterialTheme.typography.bodyLarge)
        }
    }
}