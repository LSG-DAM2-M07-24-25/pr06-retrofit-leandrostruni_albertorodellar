package com.example.cocktailapi.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    isExpandedScreen: Boolean,
    backgroundColor: Color = Color.White,
    textColor: Color = Color.Black
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(if (isExpandedScreen) 0.5f else 0.8f)
            .padding(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor)
    ) {
        Text(
            text,
            color = textColor
        )
    }
}
