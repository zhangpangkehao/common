package com.xiaojinzi.tally.lib.res.ui.theme.five

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable


private val TitianRedLightColors = lightColorScheme(
    primary = TitianRed_md_theme_light_primary,
    onPrimary = TitianRed_md_theme_light_onPrimary,
    primaryContainer = TitianRed_md_theme_light_primaryContainer,
    onPrimaryContainer = TitianRed_md_theme_light_onPrimaryContainer,
    secondary = TitianRed_md_theme_light_secondary,
    onSecondary = TitianRed_md_theme_light_onSecondary,
    secondaryContainer = TitianRed_md_theme_light_secondaryContainer,
    onSecondaryContainer = TitianRed_md_theme_light_onSecondaryContainer,
    tertiary = TitianRed_md_theme_light_tertiary,
    onTertiary = TitianRed_md_theme_light_onTertiary,
    tertiaryContainer = TitianRed_md_theme_light_tertiaryContainer,
    onTertiaryContainer = TitianRed_md_theme_light_onTertiaryContainer,
    error = TitianRed_md_theme_light_error,
    errorContainer = TitianRed_md_theme_light_errorContainer,
    onError = TitianRed_md_theme_light_onError,
    onErrorContainer = TitianRed_md_theme_light_onErrorContainer,
    background = TitianRed_md_theme_light_background,
    onBackground = TitianRed_md_theme_light_onBackground,
    surface = TitianRed_md_theme_light_surface,
    onSurface = TitianRed_md_theme_light_onSurface,
    surfaceVariant = TitianRed_md_theme_light_surfaceVariant,
    onSurfaceVariant = TitianRed_md_theme_light_onSurfaceVariant,
    outline = TitianRed_md_theme_light_outline,
    inverseOnSurface = TitianRed_md_theme_light_inverseOnSurface,
    inverseSurface = TitianRed_md_theme_light_inverseSurface,
    inversePrimary = TitianRed_md_theme_light_inversePrimary,
    surfaceTint = TitianRed_md_theme_light_surfaceTint,
    outlineVariant = TitianRed_md_theme_light_outlineVariant,
    scrim = TitianRed_md_theme_light_scrim,
)


private val TitianRedDarkColors = darkColorScheme(
    primary = TitianRed_md_theme_dark_primary,
    onPrimary = TitianRed_md_theme_dark_onPrimary,
    primaryContainer = TitianRed_md_theme_dark_primaryContainer,
    onPrimaryContainer = TitianRed_md_theme_dark_onPrimaryContainer,
    secondary = TitianRed_md_theme_dark_secondary,
    onSecondary = TitianRed_md_theme_dark_onSecondary,
    secondaryContainer = TitianRed_md_theme_dark_secondaryContainer,
    onSecondaryContainer = TitianRed_md_theme_dark_onSecondaryContainer,
    tertiary = TitianRed_md_theme_dark_tertiary,
    onTertiary = TitianRed_md_theme_dark_onTertiary,
    tertiaryContainer = TitianRed_md_theme_dark_tertiaryContainer,
    onTertiaryContainer = TitianRed_md_theme_dark_onTertiaryContainer,
    error = TitianRed_md_theme_dark_error,
    errorContainer = TitianRed_md_theme_dark_errorContainer,
    onError = TitianRed_md_theme_dark_onError,
    onErrorContainer = TitianRed_md_theme_dark_onErrorContainer,
    background = TitianRed_md_theme_dark_background,
    onBackground = TitianRed_md_theme_dark_onBackground,
    surface = TitianRed_md_theme_dark_surface,
    onSurface = TitianRed_md_theme_dark_onSurface,
    surfaceVariant = TitianRed_md_theme_dark_surfaceVariant,
    onSurfaceVariant = TitianRed_md_theme_dark_onSurfaceVariant,
    outline = TitianRed_md_theme_dark_outline,
    inverseOnSurface = TitianRed_md_theme_dark_inverseOnSurface,
    inverseSurface = TitianRed_md_theme_dark_inverseSurface,
    inversePrimary = TitianRed_md_theme_dark_inversePrimary,
    surfaceTint = TitianRed_md_theme_dark_surfaceTint,
    outlineVariant = TitianRed_md_theme_dark_outlineVariant,
    scrim = TitianRed_md_theme_dark_scrim,
)

@Composable
fun TitianRedAppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (!useDarkTheme) {
        TitianRedLightColors
    } else {
        TitianRedDarkColors
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}