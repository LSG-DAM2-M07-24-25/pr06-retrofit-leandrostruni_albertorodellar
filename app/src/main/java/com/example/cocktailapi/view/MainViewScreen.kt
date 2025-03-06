package com.example.cocktailapi.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cocktailapi.R
import com.example.cocktailapi.model.Routes
import com.example.cocktailapi.ui.theme.NavyBlue
import com.example.cocktailapi.ui.theme.SoftGold
import com.example.cocktailapi.ui.theme.WineRed
import com.example.cocktailapi.viewmodel.APIViewModel
import com.example.cocktailapi.viewmodel.CocktailViewModel


@Composable
fun MainViewScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .background(NavyBlue)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        
        Image(
            painter = painterResource(id = R.drawable.logo_app),
            contentDescription = "Cocktail Glass",
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(8.dp)
        )


        Spacer(modifier = Modifier.height(24.dp))

        // Botón Buscar por Nombre
        Button(
            onClick = { navController.navigate(Routes.SearchByNameScreen.route) },
            modifier = Modifier.fillMaxWidth(0.8f),
            colors = ButtonDefaults.buttonColors(containerColor = WineRed)
        ) {
            Text("Search by Name 🍸", color = Color.White)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Botón Cóctel Aleatorio
        Button(
            onClick = { navController.navigate(Routes.SearchRandomScreen.route) },
            modifier = Modifier.fillMaxWidth(0.8f),
            colors = ButtonDefaults.buttonColors(containerColor = SoftGold)
        ) {
            Text("Random Cocktail 🎲", color = NavyBlue)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Botón Buscar por Categoría
        Button(
            onClick = { navController.navigate(Routes.SearchByCategoryScreen.route) },
            modifier = Modifier.fillMaxWidth(0.8f),
            colors = ButtonDefaults.buttonColors(containerColor = WineRed)
        ) {
            Text("Search by Category 📂", color = Color.White)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Botón Favoritos
        Button(
            onClick = { navController.navigate(Routes.FavoritesScreen.route) },
            modifier = Modifier.fillMaxWidth(0.8f),
            colors = ButtonDefaults.buttonColors(containerColor = SoftGold)
        ) {
            Text("Favorites ❤️", color = NavyBlue)
        }
    }
}
