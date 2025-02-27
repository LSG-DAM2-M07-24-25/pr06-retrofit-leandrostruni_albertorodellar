package com.example.cocktailapi.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cocktailapi.components.CocktailItem
import com.example.cocktailapi.viewmodel.APIViewModel
import com.example.cocktailapi.viewmodel.CocktailViewModel

@SuppressLint("MutableCollectionMutableState")
@Composable
fun CocktailByCategoryScreen(
    navController: NavController,
    apiViewModel: APIViewModel,
    cocktailViewModel: CocktailViewModel,

    ) {
    val selectedCategories = rememberSaveable() { mutableStateOf(mutableSetOf<String>()) }
    val cocktailData by cocktailViewModel.cocktailData.observeAsState(initial = null)
    val loading by cocktailViewModel.loading.observeAsState(initial = false)
    val categories by cocktailViewModel.categories.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        apiViewModel.clearCocktailData()
        cocktailViewModel.fetchCategories()
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Filtrar por categoría", style = MaterialTheme.typography.headlineSmall)

        LazyColumn {
            items(categories) { category ->
                category?.let {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp, horizontal = 8.dp)
                    ) {
                        Checkbox(
                            checked = selectedCategories.value.contains(it),
                            onCheckedChange = { checked ->
                                selectedCategories.value =
                                    selectedCategories.value.toMutableSet().apply {
                                        if (checked) add(it) else remove(it)
                                    }
                            }
                        )
                        Text(
                            text = it,
                            modifier = Modifier.padding(start = 8.dp), // Espaciado del texto
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (selectedCategories.value.isNotEmpty()) {
                    cocktailViewModel.fetchFilteredCocktails(selectedCategories.value.toList())
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Buscar Cócteles")
        }

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

