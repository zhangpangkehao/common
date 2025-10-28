package com.xiaojinzi.tally.lib.res.ui.theme.custom1

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable


private val Custom1LightColors = lightColorScheme(
    primary = Custom1_md_theme_light_primary,
    onPrimary = Custom1_md_theme_light_onPrimary,
    primaryContainer = Custom1_md_theme_light_primaryContainer,
    onPrimaryContainer = Custom1_md_theme_light_onPrimaryContainer,
    secondary = Custom1_md_theme_light_secondary,
    onSecondary = Custom1_md_theme_light_onSecondary,
    secondaryContainer = Custom1_md_theme_light_secondaryContainer,
    onSecondaryContainer = Custom1_md_theme_light_onSecondaryContainer,
    tertiary = Custom1_md_theme_light_tertiary,
    onTertiary = Custom1_md_theme_light_onTertiary,
    tertiaryContainer = Custom1_md_theme_light_tertiaryContainer,
    onTertiaryContainer = Custom1_md_theme_light_onTertiaryContainer,
    error = Custom1_md_theme_light_error,
    errorContainer = Custom1_md_theme_light_errorContainer,
    onError = Custom1_md_theme_light_onError,
    onErrorContainer = Custom1_md_theme_light_onErrorContainer,
    background = Custom1_md_theme_light_background,
    onBackground = Custom1_md_theme_light_onBackground,
    surface = Custom1_md_theme_light_surface,
    onSurface = Custom1_md_theme_light_onSurface,
    surfaceVariant = Custom1_md_theme_light_surfaceVariant,
    onSurfaceVariant = Custom1_md_theme_light_onSurfaceVariant,
    outline = Custom1_md_theme_light_outline,
    inverseOnSurface = Custom1_md_theme_light_inverseOnSurface,
    inverseSurface = Custom1_md_theme_light_inverseSurface,
    inversePrimary = Custom1_md_theme_light_inversePrimary,
    surfaceTint = Custom1_md_theme_light_surfaceTint,
    outlineVariant = Custom1_md_theme_light_outlineVariant,
    scrim = Custom1_md_theme_light_scrim,
)


private val Custom1DarkColors = darkColorScheme(
    primary = Custom1_md_theme_dark_primary,
    onPrimary = Custom1_md_theme_dark_onPrimary,
    primaryContainer = Custom1_md_theme_dark_primaryContainer,
    onPrimaryContainer = Custom1_md_theme_dark_onPrimaryContainer,
    secondary = Custom1_md_theme_dark_secondary,
    onSecondary = Custom1_md_theme_dark_onSecondary,
    secondaryContainer = Custom1_md_theme_dark_secondaryContainer,
    onSecondaryContainer = Custom1_md_theme_dark_onSecondaryContainer,
    tertiary = Custom1_md_theme_dark_tertiary,
    onTertiary = Custom1_md_theme_dark_onTertiary,
    tertiaryContainer = Custom1_md_theme_dark_tertiaryContainer,
    onTertiaryContainer = Custom1_md_theme_dark_onTertiaryContainer,
    error = Custom1_md_theme_dark_error,
    errorContainer = Custom1_md_theme_dark_errorContainer,
    onError = Custom1_md_theme_dark_onError,
    onErrorContainer = Custom1_md_theme_dark_onErrorContainer,
    background = Custom1_md_theme_dark_background,
    onBackground = Custom1_md_theme_dark_onBackground,
    surface = Custom1_md_theme_dark_surface,
    onSurface = Custom1_md_theme_dark_onSurface,
    surfaceVariant = Custom1_md_theme_dark_surfaceVariant,
    onSurfaceVariant = Custom1_md_theme_dark_onSurfaceVariant,
    outline = Custom1_md_theme_dark_outline,
    inverseOnSurface = Custom1_md_theme_dark_inverseOnSurface,
    inverseSurface = Custom1_md_theme_dark_inverseSurface,
    inversePrimary = Custom1_md_theme_dark_inversePrimary,
    surfaceTint = Custom1_md_theme_dark_surfaceTint,
    outlineVariant = Custom1_md_theme_dark_outlineVariant,
    scrim = Custom1_md_theme_dark_scrim,
)

@Composable
fun Custom1AppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (!useDarkTheme) {
        Custom1LightColors
    } else {
        Custom1DarkColors
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}