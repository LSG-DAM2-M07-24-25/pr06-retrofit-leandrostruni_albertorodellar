package com.example.cocktailapi.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
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
import androidx.navigation.NavController
import com.example.cocktailapi.components.CategoryDropdownMenu
import com.example.cocktailapi.components.CocktailItem
import com.example.cocktailapi.components.CocktailSearchBar
import com.example.cocktailapi.ui.theme.DarkGreen
import com.example.cocktailapi.ui.theme.White
import com.example.cocktailapi.viewmodel.APIViewModel
import com.example.cocktailapi.viewmodel.CocktailViewModel

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

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .background(DarkGreen)
            .padding(16.dp),
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

        Spacer(modifier = Modifier.height(16.dp))

        if (loading) {
            CircularProgressIndicator()
        } else {
            CocktailSearchBar(cocktailViewModel)
            cocktailData?.drinks?.let {
                if (isExpandedScreen) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(5),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        items(searchedCocktails.size) { index ->
                            CocktailItem(
                                searchedCocktails[index],
                                navController,
                                apiViewModel,
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
                            CocktailItem(cocktail, navController, apiViewModel, cocktailViewModel)
                        }
                    }
                }
                if (searchedCocktails.isEmpty()) {
                    Spacer(modifier = Modifier.padding(16.dp))
                    Text(
                        "No se encontraron resultados",
                        style = MaterialTheme.typography.bodyLarge,
                        color = White
                    )
                }
            } ?: Text(
                "No hay resultados",
                style = MaterialTheme.typography.bodyLarge,
                color = White
            )
        }

    }
}

