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
import androidx.navigation.compose.rememberNavController
import com.example.cocktailapi.model.Routes
import com.example.cocktailapi.ui.theme.MarsRoverAPITheme
import com.example.cocktailapi.view.LaunchScreen
import com.example.cocktailapi.view.MainViewScreen
import com.example.cocktailapi.viewmodel.APIViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val apiViewModel: APIViewModel by viewModels<APIViewModel>()
        enableEdgeToEdge()
        setContent {
            MarsRoverAPITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppMarsRover(
                        modifier = Modifier.padding(innerPadding),
                        apiViewModel = apiViewModel
                    )
                }
            }
        }
    }
}

//Mover a otro package
@Composable
fun AppMarsRover(
    modifier: Modifier = Modifier,
    apiViewModel: APIViewModel
) {

    val navigationController = rememberNavController()
    NavHost(
        navController = navigationController,
        startDestination = Routes.LaunchScreen.route
    ) {
        composable(Routes.LaunchScreen.route) { LaunchScreen(navigationController) }
        composable(Routes.MainViewScreen.route) { MainViewScreen(navigationController, apiViewModel)
        }
    }

}

