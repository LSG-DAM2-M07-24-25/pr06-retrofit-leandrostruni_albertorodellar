package com.example.cocktailapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.cocktailapi.ui.theme.CocktailAPITheme
import com.example.cocktailapi.view.AppCocktailNavigation
import com.example.cocktailapi.view.CocktailByCategoryScreen
import com.example.cocktailapi.view.CocktailRandomScreen
import com.example.cocktailapi.view.DetailsScreen
import com.example.cocktailapi.view.FavoritesScreen
import com.example.cocktailapi.view.LaunchScreen
import com.example.cocktailapi.view.MainViewScreen
import com.example.cocktailapi.view.SearchByNameScreen
import com.example.cocktailapi.viewmodel.APIViewModel
import com.example.cocktailapi.viewmodel.CocktailViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val apiViewModel: APIViewModel by viewModels<APIViewModel>()
        val cocktailViewModel: CocktailViewModel by viewModels<CocktailViewModel>()
        enableEdgeToEdge()
        setContent {
            CocktailAPITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppCocktailNavigation(
                        modifier = Modifier.padding(innerPadding),
                        apiViewModel = apiViewModel,
                        cocktailViewModel = cocktailViewModel,
                    )
                }
            }
        }
    }
}



