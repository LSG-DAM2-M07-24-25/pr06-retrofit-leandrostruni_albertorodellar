package com.example.cocktailapi.components

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * Botón personalizado reutilizable en la aplicación.
 *
 * Se adapta automáticamente a distintos tamaños de pantalla y permite personalizar
 * su color de fondo y color de texto.
 *
 * @param text Texto que se mostrará en el botón.
 * @param onClick Acción que se ejecuta al presionar el botón.
 * @param isExpandedScreen Indica si la pantalla es grande, para ajustar el tamaño del botón.
 * @param backgroundColor Color de fondo del botón (por defecto es blanco).
 * @param textColor Color del texto del botón (por defecto es negro).
 * @param modifier Modificadores para ajustar el tamaño y el espaciado del botón.
 */
@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    isExpandedScreen: Boolean,
    backgroundColor: Color = Color.White,
    textColor: Color = Color.Black,
    modifier: Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(if (isExpandedScreen) 0.4f else 0.6f)
            .padding(if (isExpandedScreen) 16.dp else 8.dp)
            .height(IntrinsicSize.Min),
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor)
    ) {
        Text(
            text,
            color = textColor,
            textAlign = TextAlign.Center,
        )
    }
}
