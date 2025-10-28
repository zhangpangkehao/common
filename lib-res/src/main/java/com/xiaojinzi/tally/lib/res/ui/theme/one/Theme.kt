package com.xiaojinzi.tally.lib.res.ui.theme.one

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable


private val ChinaRedLightColors = lightColorScheme(
    primary = ChinaRed_md_theme_light_primary,
    onPrimary = ChinaRed_md_theme_light_onPrimary,
    primaryContainer = ChinaRed_md_theme_light_primaryContainer,
    onPrimaryContainer = ChinaRed_md_theme_light_onPrimaryContainer,
    secondary = ChinaRed_md_theme_light_secondary,
    onSecondary = ChinaRed_md_theme_light_onSecondary,
    secondaryContainer = ChinaRed_md_theme_light_secondaryContainer,
    onSecondaryContainer = ChinaRed_md_theme_light_onSecondaryContainer,
    tertiary = ChinaRed_md_theme_light_tertiary,
    onTertiary = ChinaRed_md_theme_light_onTertiary,
    tertiaryContainer = ChinaRed_md_theme_light_tertiaryContainer,
    onTertiaryContainer = ChinaRed_md_theme_light_onTertiaryContainer,
    error = ChinaRed_md_theme_light_error,
    errorContainer = ChinaRed_md_theme_light_errorContainer,
    onError = ChinaRed_md_theme_light_onError,
    onErrorContainer = ChinaRed_md_theme_light_onErrorContainer,
    background = ChinaRed_md_theme_light_background,
    onBackground = ChinaRed_md_theme_light_onBackground,
    surface = ChinaRed_md_theme_light_surface,
    onSurface = ChinaRed_md_theme_light_onSurface,
    surfaceVariant = ChinaRed_md_theme_light_surfaceVariant,
    onSurfaceVariant = ChinaRed_md_theme_light_onSurfaceVariant,
    outline = ChinaRed_md_theme_light_outline,
    inverseOnSurface = ChinaRed_md_theme_light_inverseOnSurface,
    inverseSurface = ChinaRed_md_theme_light_inverseSurface,
    inversePrimary = ChinaRed_md_theme_light_inversePrimary,
    surfaceTint = ChinaRed_md_theme_light_surfaceTint,
    outlineVariant = ChinaRed_md_theme_light_outlineVariant,
    scrim = ChinaRed_md_theme_light_scrim,
)


private val ChinaRedDarkColors = darkColorScheme(
    primary = ChinaRed_md_theme_dark_primary,
    onPrimary = ChinaRed_md_theme_dark_onPrimary,
    primaryContainer = ChinaRed_md_theme_dark_primaryContainer,
    onPrimaryContainer = ChinaRed_md_theme_dark_onPrimaryContainer,
    secondary = ChinaRed_md_theme_dark_secondary,
    onSecondary = ChinaRed_md_theme_dark_onSecondary,
    secondaryContainer = ChinaRed_md_theme_dark_secondaryContainer,
    onSecondaryContainer = ChinaRed_md_theme_dark_onSecondaryContainer,
    tertiary = ChinaRed_md_theme_dark_tertiary,
    onTertiary = ChinaRed_md_theme_dark_onTertiary,
    tertiaryContainer = ChinaRed_md_theme_dark_tertiaryContainer,
    onTertiaryContainer = ChinaRed_md_theme_dark_onTertiaryContainer,
    error = ChinaRed_md_theme_dark_error,
    errorContainer = ChinaRed_md_theme_dark_errorContainer,
    onError = ChinaRed_md_theme_dark_onError,
    onErrorContainer = ChinaRed_md_theme_dark_onErrorContainer,
    background = ChinaRed_md_theme_dark_background,
    onBackground = ChinaRed_md_theme_dark_onBackground,
    surface = ChinaRed_md_theme_dark_surface,
    onSurface = ChinaRed_md_theme_dark_onSurface,
    surfaceVariant = ChinaRed_md_theme_dark_surfaceVariant,
    onSurfaceVariant = ChinaRed_md_theme_dark_onSurfaceVariant,
    outline = ChinaRed_md_theme_dark_outline,
    inverseOnSurface = ChinaRed_md_theme_dark_inverseOnSurface,
    inverseSurface = ChinaRed_md_theme_dark_inverseSurface,
    inversePrimary = ChinaRed_md_theme_dark_inversePrimary,
    surfaceTint = ChinaRed_md_theme_dark_surfaceTint,
    outlineVariant = ChinaRed_md_theme_dark_outlineVariant,
    scrim = ChinaRed_md_theme_dark_scrim,
)

@Composable
fun ChinaRedAppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (!useDarkTheme) {
        ChinaRedLightColors
    } else {
        ChinaRedDarkColors
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}