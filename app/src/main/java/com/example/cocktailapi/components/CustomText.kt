package com.example.cocktailapi.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Composable para mostrar texto con tamaño y espaciado adaptativos.
 *
 * Ajusta dinámicamente el tamaño de la fuente y el espaciado según el tamaño de la pantalla.
 *
 * @param text Texto a mostrar.
 * @param isExpandedScreen Indica si la pantalla es grande, para ajustar el tamaño de la fuente y el padding.
 */
@Composable
fun CustomText(
    text: String,
    isExpandedScreen: Boolean
) {
    Text(
        text,
        fontSize = if (isExpandedScreen) 24.sp else 18.sp,
        modifier = Modifier.padding(
            if (isExpandedScreen) 16.dp else 8.dp
        )
    )
}
