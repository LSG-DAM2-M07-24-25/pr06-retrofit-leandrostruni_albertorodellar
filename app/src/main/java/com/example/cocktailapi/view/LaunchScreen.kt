package com.example.cocktailapi.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.cocktailapi.model.Routes
import kotlinx.coroutines.delay
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cocktailapi.R
import com.example.cocktailapi.ui.theme.DarkGreen
import com.example.cocktailapi.ui.theme.SoftGold
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun LaunchScreen(navController: NavController){
    var alpha by remember { mutableFloatStateOf(0f) }
    val systemUiController = rememberSystemUiController()

    //Aplicar background tambien en la barra de estado
    SideEffect {
        systemUiController.setStatusBarColor(DarkGreen)
    }

    LaunchedEffect(Unit) {
        alpha = 1f
        delay(3000)
        navController.navigate(Routes.MainViewScreen.route)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGreen),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_app),
                contentDescription = "Cocktail App Logo",
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .alpha(alpha)
            )
        }
    }
}