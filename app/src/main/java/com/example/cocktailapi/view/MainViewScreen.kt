package com.example.cocktailapi.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cocktailapi.R
import com.example.cocktailapi.model.Routes
import com.example.cocktailapi.ui.theme.DarkGreen
import com.example.cocktailapi.ui.theme.White
import com.example.cocktailapi.ui.theme.LightGreen


@Composable
fun MainViewScreen(
    navController: NavController
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGreen)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Image(
                painter = painterResource(id = R.drawable.logo_app),
                contentDescription = "Cocktail Glass",
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(8.dp)
            )
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }

        item {
            // Botón Buscar por Nombre
            Button(
                onClick = { navController.navigate(Routes.SearchByNameScreen.route) },
                modifier = Modifier.fillMaxWidth(0.8f),
                colors = ButtonDefaults.buttonColors(containerColor = LightGreen)
            ) {
                Text("Buscar por nombre", color = Color.White)
            }
        }

        item {
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            // Botón Cóctel Aleatorio
            Button(
                onClick = { navController.navigate(Routes.SearchRandomScreen.route) },
                modifier = Modifier.fillMaxWidth(0.8f),
                colors = ButtonDefaults.buttonColors(containerColor = White)
            ) {
                Text("Random Cocktail", color = Color.Black)
            }
        }

        item {
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            // Botón Buscar por Categoría
            Button(
                onClick = { navController.navigate(Routes.SearchByCategoryScreen.route) },
                modifier = Modifier.fillMaxWidth(0.8f),
                colors = ButtonDefaults.buttonColors(containerColor = LightGreen)
            ) {
                Text("Buscar por categoría", color = Color.White)
            }
        }

        item {
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            // Botón Favoritos
            Button(
                onClick = { navController.navigate(Routes.FavoritesScreen.route) },
                modifier = Modifier.fillMaxWidth(0.8f),
                colors = ButtonDefaults.buttonColors(containerColor = White)
            ) {
                Text("Favoritos", color = Color.Black)
            }
        }
    }
}
