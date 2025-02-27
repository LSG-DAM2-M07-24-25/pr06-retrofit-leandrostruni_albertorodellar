package com.example.cocktailapi.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.example.cocktailapi.model.Routes
import kotlinx.coroutines.delay

@Composable
fun LaunchScreen(navController: NavController){
    LaunchedEffect(Unit) {
        delay(1000)
        navController.navigate(Routes.MainViewScreen.route)
    }
}