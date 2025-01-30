package com.example.marsroverapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.marsroverapi.model.Routes
import com.example.marsroverapi.ui.theme.MarsRoverAPITheme
import com.example.marsroverapi.view.LaunchScreen
import com.example.marsroverapi.view.MainViewScreen
import com.example.marsroverapi.viewmodel.APIViewModel

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

