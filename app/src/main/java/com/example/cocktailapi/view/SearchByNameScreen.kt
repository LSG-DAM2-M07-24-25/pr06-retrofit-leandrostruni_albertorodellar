package com.example.cocktailapi.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cocktailapi.components.CocktailItem
import com.example.cocktailapi.components.CustomButton
import com.example.cocktailapi.ui.theme.DarkGreen
import com.example.cocktailapi.ui.theme.LightGray
import com.example.cocktailapi.ui.theme.White
import com.example.cocktailapi.ui.theme.LightGreen
import com.example.cocktailapi.viewmodel.APIViewModel
import com.example.cocktailapi.viewmodel.CocktailViewModel

@Composable
fun SearchByNameScreen(
    navController: NavController,
    apiViewModel: APIViewModel,
    cocktailViewModel: CocktailViewModel,
    isExpandedScreen: Boolean
) {
    var cocktailName by rememberSaveable { mutableStateOf("") }
    val cocktailData by apiViewModel.cocktailData.observeAsState(initial = null)
    val loading by apiViewModel.loading.observeAsState(initial = false)
    /*
        //Mantener lista cuado se rota pantalla
        LaunchedEffect(Unit) {
            if (apiViewModel.cocktailData.value == null) {
                apiViewModel.clearCocktailData()
            }
        }

     */
    // Limpiar la lista solo cuando se sale de la pantalla
    DisposableEffect(navController) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            if (destination.route != "searchByNameScreen") {
                apiViewModel.clearCocktailData()
            }
        }
        navController.addOnDestinationChangedListener(listener)
        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .background(DarkGreen)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = cocktailName,
            onValueChange = { cocktailName = it },
            label = { Text("Buscar Cocktail", style = TextStyle(color = Color.White)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            textStyle = TextStyle(color = Color.White),
            shape = RoundedCornerShape(32.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = LightGray, // ← Color de fondo cuando está enfocado
                unfocusedContainerColor = LightGray, // ← Color de fondo cuando no está enfocado
                cursorColor = Color.Black, // ← Color del cursor
                focusedIndicatorColor = Color.Transparent, // ← Oculta la línea de abajo al enfocar
                unfocusedIndicatorColor = Color.Transparent // ← Oculta la línea cuando no está enfocado
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomButton(
            text = "Buscar Cocktail",
            onClick = { apiViewModel.searchCocktail(cocktailName) },
            isExpandedScreen,
            backgroundColor = LightGreen,
            textColor = Color.White,
            modifier = Modifier
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (loading) {
            CircularProgressIndicator()
        } else {
            cocktailData?.drinks?.let { drinks ->
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(drinks) { cocktail ->
                        CocktailItem(cocktail, navController, apiViewModel, cocktailViewModel)
                    }
                }
            } ?: Text(
                "No hay resultados.",
                style = MaterialTheme.typography.bodyLarge,
                color = White
            )
        }

    }
}