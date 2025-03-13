package com.example.cocktailapi.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Search
import com.example.cocktailapi.ui.theme.LightGray
import com.example.cocktailapi.ui.theme.White
import com.example.cocktailapi.viewmodel.CocktailViewModel

@kotlin.OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CocktailSearchBar(cocktailViewModel: CocktailViewModel) {
    val searchedText by cocktailViewModel.searchedText.observeAsState("")
    val searchHistory by cocktailViewModel.searchHistory.observeAsState(emptyList())

    SearchBar(
        query = searchedText,
        onQueryChange = { cocktailViewModel.onSearchTextChange(it) },
        onSearch = { cocktailViewModel.addToHistory(it) },
        active = false,
        onActiveChange = { },
        leadingIcon = { Icon(Lucide.Search, contentDescription = "Search",tint = Color.White,) },
        trailingIcon = {
            if (searchHistory.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "Clear",
                    tint = Color.Red,
                    modifier = Modifier.clickable { cocktailViewModel.clearHistory() }
                )
            }
        },
        placeholder = { Text("Busca un cocktail...", color = White) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(16.dp)),
                colors = SearchBarDefaults.colors(containerColor = LightGray)

    ) {}
    searchHistory.lastOrNull()?.let {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .clickable {
                    cocktailViewModel.onSearchTextChange(it)
                },
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)

        ) {
            Text(
                text = it,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}