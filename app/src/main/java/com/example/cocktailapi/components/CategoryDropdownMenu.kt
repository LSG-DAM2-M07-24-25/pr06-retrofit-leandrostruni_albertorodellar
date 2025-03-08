package com.example.cocktailapi.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cocktailapi.ui.theme.NavyBlue
import com.example.cocktailapi.ui.theme.SoftGold
import com.example.cocktailapi.ui.theme.WineRed


@Composable
fun CategoryDropdownMenu(
    categories: List<String?>,
    selectedCategories: MutableState<MutableSet<String>>,
    onApplyFilters: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Button(
            onClick = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = WineRed)
        ) {
            Text("Seleccionar Categorías", color = Color.White)
        }

        if (expanded) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(NavyBlue)
                    .padding(8.dp)
            ) {
                items(categories) { category ->
                    Row(
                        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                selectedCategories.value =
                                    selectedCategories.value.toMutableSet().apply {
                                        if (contains(category)) remove(category) else category?.let {
                                            add(
                                                it
                                            )
                                        }
                                    }
                            }
                            .padding(8.dp)
                    ) {
                        Checkbox(
                            checked = selectedCategories.value.contains(category),
                            onCheckedChange = { checked ->
                                selectedCategories.value =
                                    selectedCategories.value.toMutableSet().apply {
                                        if (checked) category?.let { add(it) } else remove(category)
                                    }
                            }
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        if (category != null) {
                            Text(text = category, color = SoftGold)
                        }
                    }
                }
            }
        }

        Button(
            onClick = {
                expanded = false
                onApplyFilters()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = WineRed)
        ) {
            Text("Aplicar Filtros", color = Color.White)
        }
    }
}