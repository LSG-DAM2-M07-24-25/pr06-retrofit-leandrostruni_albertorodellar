package com.example.marsroverapi.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.example.marsroverapi.model.Routes
import kotlinx.coroutines.delay

@Composable
fun LaunchScreen(navController: NavController){
    LaunchedEffect(Unit) {
        delay(3000)
        navController.navigate(Routes.MainViewScreen.route)
    }
}