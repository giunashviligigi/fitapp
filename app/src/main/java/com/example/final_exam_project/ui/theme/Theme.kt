package com.example.final_exam_project.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = FitTrackPrimary,
    onPrimary = FitTrackOnPrimary,
    secondary = FitTrackSecondary,
    background = FitTrackBackground,
    surface = FitTrackSurface,
    onSurface = FitTrackOnSurface
)

private val DarkColors = darkColorScheme(
    primary = FitTrackPrimary,
    secondary = FitTrackSecondary
)

@Composable
fun FitTrackTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colorScheme,
        typography = FitTrackTypography,
        content = content
    )
}
