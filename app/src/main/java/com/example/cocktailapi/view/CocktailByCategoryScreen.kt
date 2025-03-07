package com.example.cocktailapi.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cocktailapi.components.CategoryDropdownMenu
import com.example.cocktailapi.components.CocktailItem
import com.example.cocktailapi.ui.theme.NavyBlue
import com.example.cocktailapi.ui.theme.SoftGold
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

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .background(NavyBlue)
            .padding(16.dp)
    ) {

        Text(
            "Filtrar por categoría",
            style = MaterialTheme.typography.headlineSmall,
            color = SoftGold
        )

        CategoryDropdownMenu(
            categories = categories,
            selectedCategories = selectedCategories,
            onApplyFilters = {
                if (selectedCategories.value.isNotEmpty()) {
                    cocktailViewModel.fetchFilteredCocktails(selectedCategories.value.toList())
                } else {
                    cocktailViewModel.clearCocktails()
                }
            }
        )

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
            } ?: Text(
                "No hay resultados",
                style = MaterialTheme.typography.bodyLarge,
                color = SoftGold
            )
        }

    }
}

