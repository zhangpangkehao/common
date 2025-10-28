package com.xiaojinzi.tally.lib.res.ui.theme.two

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable


private val KleinBlueLightColors = lightColorScheme(
    primary = KleinBlue_md_theme_light_primary,
    onPrimary = KleinBlue_md_theme_light_onPrimary,
    primaryContainer = KleinBlue_md_theme_light_primaryContainer,
    onPrimaryContainer = KleinBlue_md_theme_light_onPrimaryContainer,
    secondary = KleinBlue_md_theme_light_secondary,
    onSecondary = KleinBlue_md_theme_light_onSecondary,
    secondaryContainer = KleinBlue_md_theme_light_secondaryContainer,
    onSecondaryContainer = KleinBlue_md_theme_light_onSecondaryContainer,
    tertiary = KleinBlue_md_theme_light_tertiary,
    onTertiary = KleinBlue_md_theme_light_onTertiary,
    tertiaryContainer = KleinBlue_md_theme_light_tertiaryContainer,
    onTertiaryContainer = KleinBlue_md_theme_light_onTertiaryContainer,
    error = KleinBlue_md_theme_light_error,
    errorContainer = KleinBlue_md_theme_light_errorContainer,
    onError = KleinBlue_md_theme_light_onError,
    onErrorContainer = KleinBlue_md_theme_light_onErrorContainer,
    background = KleinBlue_md_theme_light_background,
    onBackground = KleinBlue_md_theme_light_onBackground,
    surface = KleinBlue_md_theme_light_surface,
    onSurface = KleinBlue_md_theme_light_onSurface,
    surfaceVariant = KleinBlue_md_theme_light_surfaceVariant,
    onSurfaceVariant = KleinBlue_md_theme_light_onSurfaceVariant,
    outline = KleinBlue_md_theme_light_outline,
    inverseOnSurface = KleinBlue_md_theme_light_inverseOnSurface,
    inverseSurface = KleinBlue_md_theme_light_inverseSurface,
    inversePrimary = KleinBlue_md_theme_light_inversePrimary,
    surfaceTint = KleinBlue_md_theme_light_surfaceTint,
    outlineVariant = KleinBlue_md_theme_light_outlineVariant,
    scrim = KleinBlue_md_theme_light_scrim,
)


private val KleinBlueDarkColors = darkColorScheme(
    primary = KleinBlue_md_theme_dark_primary,
    onPrimary = KleinBlue_md_theme_dark_onPrimary,
    primaryContainer = KleinBlue_md_theme_dark_primaryContainer,
    onPrimaryContainer = KleinBlue_md_theme_dark_onPrimaryContainer,
    secondary = KleinBlue_md_theme_dark_secondary,
    onSecondary = KleinBlue_md_theme_dark_onSecondary,
    secondaryContainer = KleinBlue_md_theme_dark_secondaryContainer,
    onSecondaryContainer = KleinBlue_md_theme_dark_onSecondaryContainer,
    tertiary = KleinBlue_md_theme_dark_tertiary,
    onTertiary = KleinBlue_md_theme_dark_onTertiary,
    tertiaryContainer = KleinBlue_md_theme_dark_tertiaryContainer,
    onTertiaryContainer = KleinBlue_md_theme_dark_onTertiaryContainer,
    error = KleinBlue_md_theme_dark_error,
    errorContainer = KleinBlue_md_theme_dark_errorContainer,
    onError = KleinBlue_md_theme_dark_onError,
    onErrorContainer = KleinBlue_md_theme_dark_onErrorContainer,
    background = KleinBlue_md_theme_dark_background,
    onBackground = KleinBlue_md_theme_dark_onBackground,
    surface = KleinBlue_md_theme_dark_surface,
    onSurface = KleinBlue_md_theme_dark_onSurface,
    surfaceVariant = KleinBlue_md_theme_dark_surfaceVariant,
    onSurfaceVariant = KleinBlue_md_theme_dark_onSurfaceVariant,
    outline = KleinBlue_md_theme_dark_outline,
    inverseOnSurface = KleinBlue_md_theme_dark_inverseOnSurface,
    inverseSurface = KleinBlue_md_theme_dark_inverseSurface,
    inversePrimary = KleinBlue_md_theme_dark_inversePrimary,
    surfaceTint = KleinBlue_md_theme_dark_surfaceTint,
    outlineVariant = KleinBlue_md_theme_dark_outlineVariant,
    scrim = KleinBlue_md_theme_dark_scrim,
)

@Composable
fun KleinBlueAppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (!useDarkTheme) {
        KleinBlueLightColors
    } else {
        KleinBlueDarkColors
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}