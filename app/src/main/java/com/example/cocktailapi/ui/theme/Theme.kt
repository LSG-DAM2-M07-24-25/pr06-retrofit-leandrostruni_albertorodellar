package com.example.cocktailapi.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = SoftGold,
    onPrimary = NavyBlue,
    secondary = WineRed,
    onSecondary = VintageCream,
    background = NavyBlue,
    onBackground = VintageCream,
    surface = SlateGray,
    onSurface = SoftGold
)

private val LightColorScheme = lightColorScheme(
    primary = SoftGold,
    onPrimary = NavyBlue,
    secondary = WineRed,
    onSecondary = VintageCream,
    background = VintageCream,
    onBackground = SlateGray,
    surface = SoftGold,
    onSurface = NavyBlue
)

@Composable
fun CocktailAPITheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}