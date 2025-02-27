package com.example.cocktailapi.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDropdownMenu(
    categories: List<String?>,
    selectedCategories: MutableState<MutableSet<String>>,
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = "Seleccionar Categorías",
            onValueChange = {},
            readOnly = true,
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            trailingIcon = {
                Icon(Icons.Default.ArrowDropDown, contentDescription = "Expandir")
            }
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            categories.forEach { category ->
                DropdownMenuItem(
                    text = {
                        if (category != null) {
                            Text(category)
                        }
                    },
                    onClick = {
                        if (selectedCategories.value.contains(category)) {
                            selectedCategories.value.remove(category)
                        } else {
                            if (category != null) {
                                selectedCategories.value.add(category)
                            }
                        }
                    }
                )
            }
        }
    }
}