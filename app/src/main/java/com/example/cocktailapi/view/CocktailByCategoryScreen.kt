package com.example.cocktailapi.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cocktailapi.components.CategoryDropdownMenu
import com.example.cocktailapi.components.CocktailItem
import com.example.cocktailapi.components.CocktailSearchBar
import com.example.cocktailapi.ui.theme.DarkGreen
import com.example.cocktailapi.ui.theme.White
import com.example.cocktailapi.viewmodel.APIViewModel
import com.example.cocktailapi.viewmodel.CocktailViewModel

/**
 * Pantalla para filtrar cócteles por categoría.
 *
 * Permite seleccionar una o más categorías y muestra los resultados en una cuadrícula adaptativa.
 *
 * @param navController Controlador de navegación.
 * @param apiViewModel ViewModel para gestionar las solicitudes a la API.
 * @param cocktailViewModel ViewModel que maneja la lógica de los cócteles.
 * @param isExpandedScreen Indica si la pantalla es grande para ajustar la UI.
 */
@SuppressLint("MutableCollectionMutableState")
@Composable
fun CocktailByCategoryScreen(
    navController: NavController,
    apiViewModel: APIViewModel,
    cocktailViewModel: CocktailViewModel,
    isExpandedScreen: Boolean

) {
    val selectedCategories = rememberSaveable() { mutableStateOf(mutableSetOf<String>()) }
    val cocktailData by cocktailViewModel.cocktailData.observeAsState(initial = null)
    val loading by cocktailViewModel.loading.observeAsState(initial = false)
    val categories by cocktailViewModel.categories.observeAsState(emptyList())
    val searchedCocktails by cocktailViewModel.searchedCocktails.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        apiViewModel.clearCocktailData()
        cocktailViewModel.fetchCategories()
        cocktailViewModel.clearHistory()
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGreen)
            .padding(16.dp)
    ) {
        val maxWidthDp = this.maxWidth
        val columns = when {
            maxWidthDp < 400.dp -> 1 // Móviles pequeños
            maxWidthDp < 600.dp -> 2 // Teléfonos grandes
            maxWidthDp < 900.dp -> 3 // Tablets pequeñas
            else -> 4 // Tablets grandes y pantallas extendidas
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()
                .background(DarkGreen)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CategoryDropdownMenu(
                categories = categories,
                selectedCategories = selectedCategories,
                onApplyFilters = {
                    if (selectedCategories.value.isNotEmpty()) {
                        cocktailViewModel.fetchFilteredCocktails(selectedCategories.value.toList())
                    } else {
                        cocktailViewModel.clearCocktails()
                    }
                },
                isExpandedScreen
            )
            CocktailSearchBar(cocktailViewModel)

            Spacer(modifier = Modifier.height(16.dp))

            if (loading) {
                CircularProgressIndicator()
            } else {
                cocktailData?.drinks?.let { drinks ->
                    if (isExpandedScreen) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(columns),
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(16.dp)
                        ) {
                            items(drinks.size) { index ->
                                CocktailItem(
                                    drinks[index],
                                    navController,
                                    cocktailViewModel
                                )
                            }
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(16.dp)
                        ) {
                            items(searchedCocktails) { cocktail ->
                                CocktailItem(
                                    cocktail,
                                    navController,
                                    cocktailViewModel
                                )
                            }
                            if (searchedCocktails.isEmpty()) {
                                item {
                                    Spacer(modifier = Modifier.padding(16.dp))
                                    Text(
                                        "No se encontraron resultados",
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = White
                                    )
                                }
                            }
                        }
                    }
                } ?: Text(
                    "No hay resultados",
                    style = MaterialTheme.typography.bodyLarge,
                    color = White,
                    fontSize = if (isExpandedScreen) 22.sp else 18.sp

                )
            }
        }
    }
}

