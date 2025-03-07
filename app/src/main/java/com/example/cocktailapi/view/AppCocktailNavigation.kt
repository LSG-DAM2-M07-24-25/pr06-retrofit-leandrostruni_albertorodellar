package com.example.cocktailapi.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cocktailapi.components.BottomNavigationBar
import com.example.cocktailapi.components.TopAppBar
import com.example.cocktailapi.model.Routes
import com.example.cocktailapi.viewmodel.APIViewModel
import com.example.cocktailapi.viewmodel.CocktailViewModel

@Composable
fun AppCocktailNavigation(
    modifier: Modifier = Modifier,
    apiViewModel: APIViewModel,
    cocktailViewModel: CocktailViewModel
) {

    val navigationController = rememberNavController()
    val currentRoute = navigationController.currentBackStackEntryAsState().value?.destination?.route

    val screenTitles = mapOf(
        Routes.MainViewScreen.route to "Home",
        Routes.SearchByNameScreen.route to "Buscar por Nombre",
        Routes.SearchRandomScreen.route to "Cóctel Aleatorio",
        Routes.SearchByCategoryScreen.route to "Buscar por Categoría",
        Routes.DetailsScreen.route to "Detalles del Cóctel",
        Routes.FavoritesScreen.route to "Favoritos"
    )

    Scaffold(
        topBar = {
            if (currentRoute != Routes.LaunchScreen.route) {
                TopAppBar(
                    title = screenTitles[currentRoute] ?: "Cocktail Finder",
                    onBackPressed = { navigationController.popBackStack() },
                    navController = navigationController
                )
            }
        },
        bottomBar = {
            if (currentRoute != Routes.LaunchScreen.route) {
                BottomNavigationBar(navigationController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navigationController,
            startDestination = Routes.LaunchScreen.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.LaunchScreen.route) { LaunchScreen(navigationController) }
            composable(Routes.MainViewScreen.route) { MainViewScreen(navigationController) }
            composable(Routes.SearchByNameScreen.route) {
                SearchByNameScreen(
                    navigationController,
                    apiViewModel,
                    cocktailViewModel
                )
            }
            composable(Routes.SearchRandomScreen.route) {
                CocktailRandomScreen(
                    navigationController,
                    apiViewModel,
                    cocktailViewModel
                )
            }
            composable(Routes.SearchByCategoryScreen.route) {
                CocktailByCategoryScreen(
                    navigationController,
                    apiViewModel,
                    cocktailViewModel
                )
            }
            composable(Routes.DetailsScreen.route) {
                DetailsScreen(
                    navigationController,
                    apiViewModel,
                    cocktailViewModel
                )
            }
            composable(Routes.FavoritesScreen.route) {
                FavoritesScreen(navigationController,
                    apiViewModel,
                    cocktailViewModel)
            }
        }
    }
}