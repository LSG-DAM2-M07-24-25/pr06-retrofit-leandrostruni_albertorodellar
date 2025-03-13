package com.example.cocktailapi.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cocktailapi.components.CocktailItem
import com.example.cocktailapi.components.CustomButton
import com.example.cocktailapi.ui.theme.DarkGreen
import com.example.cocktailapi.ui.theme.LightGreen
import com.example.cocktailapi.ui.theme.White
import com.example.cocktailapi.viewmodel.APIViewModel
import com.example.cocktailapi.viewmodel.CocktailViewModel

/**
 * Pantalla que muestra un cóctel aleatorio.
 *
 * El usuario puede presionar un botón para obtener un nuevo cóctel aleatorio.
 *
 * @param navController Controlador de navegación.
 * @param apiViewModel ViewModel para gestionar las solicitudes a la API.
 * @param cocktailViewModel ViewModel que maneja la lógica de los cócteles.
 * @param isExpandedScreen Indica si la pantalla es grande para ajustar la UI.
 */
@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun CocktailRandomScreen(
    navController: NavController,
    apiViewModel: APIViewModel,
    cocktailViewModel: CocktailViewModel,
    isExpandedScreen: Boolean
) {
    val cocktailData by apiViewModel.cocktailData.observeAsState(initial = null)
    val loading by apiViewModel.loading.observeAsState(initial = false)

    /*
    if (apiViewModel.cocktailData.value == null) {
        apiViewModel.clearCocktailData()
    }
     */
    // Limpiar la lista solo cuando se sale de la pantalla
    DisposableEffect(navController) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            if (destination.route != "searchRandomScreen") {
                apiViewModel.clearCocktailData()
            }
        }
        navController.addOnDestinationChangedListener(listener)
        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGreen)
            .padding(16.dp)
    ) {
        val maxWidthDp = maxWidth
        val columns = when {
            maxWidthDp < 400.dp -> 1
            maxWidthDp < 600.dp -> 2
            maxWidthDp < 900.dp -> 3
            else -> 4
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()
                .background(DarkGreen)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomButton(
                text = "Cocktail Aleatorio",
                onClick = { apiViewModel.fetchRandomCocktail() },
                isExpandedScreen,
                backgroundColor = LightGreen,
                textColor = Color.White,
                modifier = Modifier
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (loading) {
                CircularProgressIndicator()
            } else {
                cocktailData?.drinks?.let { drinks ->
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        items(drinks) { cocktail ->
                            CocktailItem(cocktail, navController, apiViewModel, cocktailViewModel)
                        }
                    }
                } ?: Text(
                    "No hay resultados",
                    style = MaterialTheme.typography.bodyLarge,
                    color = White,
                    fontSize = if (isExpandedScreen) 22.sp else 18.sp
                )
            }
        }
    }
}