package com.example.cocktailapi.view

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
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
import com.example.cocktailapi.R
import com.example.cocktailapi.ui.theme.DarkGreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.mutableFloatStateOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Pantalla de inicio con animación de logo antes de navegar a la pantalla principal.
 *
 * @param navController Controlador de navegación.
 */
@Composable
fun LaunchScreen(navController: NavController){
    var alpha by remember { mutableFloatStateOf(0f) }
    val systemUiController = rememberSystemUiController()

    //Aplicar background también en la barra de estado
    SideEffect {
        systemUiController.setStatusBarColor(DarkGreen)
    }

    // Usamos animateFloatAsState para animar el valor de alpha
    val animatedAlpha by animateFloatAsState(
        targetValue = alpha,
        animationSpec = tween(durationMillis = 1500) // Duracion de la animacion
    )

    LaunchedEffect(Unit) {
        alpha = 1f
        delay(2500)
        alpha = 0f
        delay(1000)
        withContext(Dispatchers.Main) {
            navController.navigate(Routes.MainViewScreen.route)
        }
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
                    .fillMaxWidth(0.8f)
                    .alpha(animatedAlpha) // Aplicamos la animacion al alpha
            )
        }
    }
}
