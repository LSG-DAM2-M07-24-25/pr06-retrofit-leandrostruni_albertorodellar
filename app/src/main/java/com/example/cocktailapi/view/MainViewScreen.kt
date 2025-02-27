package com.example.cocktailapi.view

import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cocktailapi.viewmodel.APIViewModel
import com.example.cocktailapi.viewmodel.CocktailViewModel


@Composable
fun MainViewScreen(
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("Home", style = MaterialTheme.typography.headlineSmall)
    }

}