package com.example.cocktailapi.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cocktailapi.ui.theme.White
import com.example.cocktailapi.ui.theme.LightGreen
import com.example.cocktailapi.ui.theme.DarkGreen

/**
 * Menú desplegable para seleccionar categorías de cócteles.
 *
 * @param categories Lista de nombres de categorías disponibles.
 * @param selectedCategories Estado mutable que contiene las categorías seleccionadas por el usuario.
 * @param onApplyFilters Función que se ejecuta cuando el usuario aplica los filtros seleccionados.
 * @param isExpandedScreen Indica si la pantalla es grande, para ajustar el diseño.
 */
@Composable
fun CategoryDropdownMenu(
    categories: List<String?>,
    selectedCategories: MutableState<MutableSet<String>>,
    onApplyFilters: () -> Unit,
    isExpandedScreen: Boolean
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        CustomButton(
            text = "Seleccionar Categorías",
            onClick = { expanded = !expanded },
            isExpandedScreen,
            backgroundColor = LightGreen,
            textColor = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        // Lista de categorías (visible solo cuando expanded es true)
        if (expanded) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(White, shape = RoundedCornerShape(16.dp))
                    .border(2.dp, DarkGreen, shape = RoundedCornerShape(16.dp))
                    .padding(8.dp)
            ) {
                items(categories) { category ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
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
                        // Checkbox para seleccionar o deseleccionar la categoría
                        Checkbox(
                            checked = selectedCategories.value.contains(category),
                            onCheckedChange = { checked ->
                                selectedCategories.value =
                                    selectedCategories.value.toMutableSet().apply {
                                        if (checked) category?.let { add(it) } else remove(category)
                                    }
                            },
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color.Black, // Color del check marcado
                                uncheckedColor = Color.Black, // Color del check desmarcado
                                checkmarkColor = Color.White // Color de la marca de verificación
                            )
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        if (category != null) {
                            Text(text = category, color = Color.Black)
                        }
                    }
                }
            }
        }
        // Fila de botones: Aplicar Filtros y Limpiar Filtros
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CustomButton(
                text = "Aplicar Filtros",
                onClick = {
                    expanded = false
                    onApplyFilters()
                },
                isExpandedScreen,
                backgroundColor = LightGreen,
                textColor = Color.White,
                modifier = Modifier.weight(1f)
            )

            CustomButton(
                text = "Limpiar Filtros",
                onClick = {
                    selectedCategories.value = mutableSetOf()
                    onApplyFilters()
                },
                isExpandedScreen,
                backgroundColor = LightGreen,
                textColor = Color.White,
                modifier = Modifier.weight(1f)
            )
        }

    }
}