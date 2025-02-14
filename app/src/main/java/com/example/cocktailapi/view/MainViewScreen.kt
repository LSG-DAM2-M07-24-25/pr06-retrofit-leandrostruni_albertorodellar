package com.example.cocktailapi.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.cocktailapi.viewmodel.APIViewModel


@Composable
fun MainViewScreen(navController: NavController, apiViewModel: APIViewModel){
    DebugViewScreen(apiViewModel)
}