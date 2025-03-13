package com.example.cocktailapi.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
