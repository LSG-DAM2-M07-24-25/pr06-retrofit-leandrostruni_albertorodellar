package com.example.cocktailapi.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.navigation.NavHostController
import com.example.cocktailapi.components.NavigationDrawer
import kotlinx.coroutines.launch

@Composable
fun AppCocktailNavigation(
    modifier: Modifier = Modifier,
    apiViewModel: APIViewModel,
    cocktailViewModel: CocktailViewModel,
    windowSizeClass: WindowSizeClass
) {
    val navigationController = rememberNavController()
    val currentRoute = navigationController.currentBackStackEntryAsState().value?.destination?.route
    val isExpandedScreen = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Títulos de las pantallas
    val screenTitles = mapOf(
        Routes.MainViewScreen.route to "Home",
        Routes.SearchByNameScreen.route to "Buscar por Nombre",
        Routes.SearchRandomScreen.route to "Cóctel Aleatorio",
        Routes.SearchByCategoryScreen.route to "Buscar por Categoría",
        Routes.DetailsScreen.route to "Detalles del Cóctel",
        Routes.FavoritesScreen.route to "Favoritos"
    )

    if (isExpandedScreen) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            gesturesEnabled = isExpandedScreen, // Habilitar solo en pantallas grandes
            drawerContent = {
                NavigationDrawer(
                    navController = navigationController,
                    onItemClick = { scope.launch { drawerState.close() } } // Cerrar Drawer al seleccionar
                )
            }
        ) {
            Scaffold(
                topBar = {
                    if (currentRoute != Routes.LaunchScreen.route) {
                        TopAppBar(
                            title = screenTitles[currentRoute] ?: "Cocktail Finder",
                            navController = navigationController,
                            isExpandedScreen = isExpandedScreen,
                            onMenuClick = {
                                scope.launch {
                                    if (drawerState.isClosed) drawerState.open() else drawerState.close()
                                }
                            }
                        )
                    }

                }
            ) { innerPadding ->
                AppContent(
                    navigationController,
                    currentRoute,
                    screenTitles,
                    apiViewModel,
                    cocktailViewModel,
                    Modifier.padding(innerPadding)
                )
            }
        }
    } else {
        // Usar Bottom Navigation Bar en pantallas pequeñas
        Scaffold(
            topBar = {
                if (currentRoute != Routes.LaunchScreen.route) {
                    TopAppBar(
                        title = screenTitles[currentRoute] ?: "Cocktail Finder",
                        navController = navigationController,
                        isExpandedScreen = false,
                        onMenuClick = { navigationController.popBackStack() }
                    )
                }
            },
            bottomBar = {
                if (currentRoute != Routes.LaunchScreen.route) {
                    BottomNavigationBar(navigationController)
                }
            }
        ) { innerPadding ->
            AppContent(
                navigationController,
                currentRoute,
                screenTitles,
                apiViewModel,
                cocktailViewModel,
                Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun AppContent(
    navigationController: NavHostController,
    currentRoute: String?,
    screenTitles: Map<String, String>,
    apiViewModel: APIViewModel,
    cocktailViewModel: CocktailViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navigationController,
        startDestination = Routes.LaunchScreen.route,
        modifier = modifier
    ) {
        composable(Routes.LaunchScreen.route) { LaunchScreen(navigationController) }
        composable(Routes.MainViewScreen.route) { MainViewScreen(navigationController) }
        composable(Routes.SearchByNameScreen.route) {
            SearchByNameScreen(navigationController, apiViewModel, cocktailViewModel)
        }
        composable(Routes.SearchRandomScreen.route) {
            CocktailRandomScreen(navigationController, apiViewModel, cocktailViewModel)
        }
        composable(Routes.SearchByCategoryScreen.route) {
            CocktailByCategoryScreen(navigationController, apiViewModel, cocktailViewModel)
        }
        composable(Routes.DetailsScreen.route) {
            DetailsScreen(navigationController, apiViewModel, cocktailViewModel)
        }
        composable(Routes.FavoritesScreen.route) {
            FavoritesScreen(navigationController, apiViewModel, cocktailViewModel)
        }
    }
}
