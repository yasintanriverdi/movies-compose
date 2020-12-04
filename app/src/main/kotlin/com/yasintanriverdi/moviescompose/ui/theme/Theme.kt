package com.yasintanriverdi.moviescompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.onCommit
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import com.yasintanriverdi.moviescompose.ui.utils.SysUiController

private val DarkColorPalette = darkColors(
    primary = green200,
    primaryVariant = green700,
    secondary = teal200,
    background = Color.Black,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    error = Color.Red,
)

private val LightColorPalette = lightColors(
    primary = green500,
    primaryVariant = green700,
    secondary = teal200,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black
)

@Composable
fun MoviesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val sysUiController = SysUiController.current
    onCommit(sysUiController, colors.surface) {
        sysUiController.setSystemBarsColor(
            color = colors.background.copy(alpha = 0.85f),
            transformColorForLightContent = { original ->
                colors.onBackground.copy(alpha = 0.2f).compositeOver(original)
            }
        )
    }

    MaterialTheme(
        colors = colors,
        typography = MoviesTypography,
        shapes = shapes,
        content = content
    )
}
