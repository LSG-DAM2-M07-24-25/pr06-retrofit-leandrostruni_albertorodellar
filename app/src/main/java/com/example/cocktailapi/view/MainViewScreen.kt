package com.example.cocktailapi.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.cocktailapi.viewmodel.APIViewModel
import com.example.cocktailapi.viewmodel.CocktailViewModel


@Composable
fun MainViewScreen(
    navController: NavController,
    apiViewModel: APIViewModel,
    cocktailViewModel: CocktailViewModel
) {
    DebugViewScreen(apiViewModel,cocktailViewModel)
}