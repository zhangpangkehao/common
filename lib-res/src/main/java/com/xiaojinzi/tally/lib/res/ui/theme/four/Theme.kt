package com.xiaojinzi.tally.lib.res.ui.theme.four

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable


private val SchenbrunnYellowLightColors = lightColorScheme(
    primary = Schenbrunn_Yellowmd_md_theme_light_primary,
    onPrimary = Schenbrunn_Yellowmd_md_theme_light_onPrimary,
    primaryContainer = Schenbrunn_Yellowmd_md_theme_light_primaryContainer,
    onPrimaryContainer = Schenbrunn_Yellowmd_md_theme_light_onPrimaryContainer,
    secondary = Schenbrunn_Yellowmd_md_theme_light_secondary,
    onSecondary = Schenbrunn_Yellowmd_md_theme_light_onSecondary,
    secondaryContainer = Schenbrunn_Yellowmd_md_theme_light_secondaryContainer,
    onSecondaryContainer = Schenbrunn_Yellowmd_md_theme_light_onSecondaryContainer,
    tertiary = Schenbrunn_Yellowmd_md_theme_light_tertiary,
    onTertiary = Schenbrunn_Yellowmd_md_theme_light_onTertiary,
    tertiaryContainer = Schenbrunn_Yellowmd_md_theme_light_tertiaryContainer,
    onTertiaryContainer = Schenbrunn_Yellowmd_md_theme_light_onTertiaryContainer,
    error = Schenbrunn_Yellowmd_md_theme_light_error,
    errorContainer = Schenbrunn_Yellowmd_md_theme_light_errorContainer,
    onError = Schenbrunn_Yellowmd_md_theme_light_onError,
    onErrorContainer = Schenbrunn_Yellowmd_md_theme_light_onErrorContainer,
    background = Schenbrunn_Yellowmd_md_theme_light_background,
    onBackground = Schenbrunn_Yellowmd_md_theme_light_onBackground,
    surface = Schenbrunn_Yellowmd_md_theme_light_surface,
    onSurface = Schenbrunn_Yellowmd_md_theme_light_onSurface,
    surfaceVariant = Schenbrunn_Yellowmd_md_theme_light_surfaceVariant,
    onSurfaceVariant = Schenbrunn_Yellowmd_md_theme_light_onSurfaceVariant,
    outline = Schenbrunn_Yellowmd_md_theme_light_outline,
    inverseOnSurface = Schenbrunn_Yellowmd_md_theme_light_inverseOnSurface,
    inverseSurface = Schenbrunn_Yellowmd_md_theme_light_inverseSurface,
    inversePrimary = Schenbrunn_Yellowmd_md_theme_light_inversePrimary,
    surfaceTint = Schenbrunn_Yellowmd_md_theme_light_surfaceTint,
    outlineVariant = Schenbrunn_Yellowmd_md_theme_light_outlineVariant,
    scrim = Schenbrunn_Yellowmd_md_theme_light_scrim,
)


private val SchenbrunnYellowDarkColors = darkColorScheme(
    primary = Schenbrunn_Yellowmd_md_theme_dark_primary,
    onPrimary = Schenbrunn_Yellowmd_md_theme_dark_onPrimary,
    primaryContainer = Schenbrunn_Yellowmd_md_theme_dark_primaryContainer,
    onPrimaryContainer = Schenbrunn_Yellowmd_md_theme_dark_onPrimaryContainer,
    secondary = Schenbrunn_Yellowmd_md_theme_dark_secondary,
    onSecondary = Schenbrunn_Yellowmd_md_theme_dark_onSecondary,
    secondaryContainer = Schenbrunn_Yellowmd_md_theme_dark_secondaryContainer,
    onSecondaryContainer = Schenbrunn_Yellowmd_md_theme_dark_onSecondaryContainer,
    tertiary = Schenbrunn_Yellowmd_md_theme_dark_tertiary,
    onTertiary = Schenbrunn_Yellowmd_md_theme_dark_onTertiary,
    tertiaryContainer = Schenbrunn_Yellowmd_md_theme_dark_tertiaryContainer,
    onTertiaryContainer = Schenbrunn_Yellowmd_md_theme_dark_onTertiaryContainer,
    error = Schenbrunn_Yellowmd_md_theme_dark_error,
    errorContainer = Schenbrunn_Yellowmd_md_theme_dark_errorContainer,
    onError = Schenbrunn_Yellowmd_md_theme_dark_onError,
    onErrorContainer = Schenbrunn_Yellowmd_md_theme_dark_onErrorContainer,
    background = Schenbrunn_Yellowmd_md_theme_dark_background,
    onBackground = Schenbrunn_Yellowmd_md_theme_dark_onBackground,
    surface = Schenbrunn_Yellowmd_md_theme_dark_surface,
    onSurface = Schenbrunn_Yellowmd_md_theme_dark_onSurface,
    surfaceVariant = Schenbrunn_Yellowmd_md_theme_dark_surfaceVariant,
    onSurfaceVariant = Schenbrunn_Yellowmd_md_theme_dark_onSurfaceVariant,
    outline = Schenbrunn_Yellowmd_md_theme_dark_outline,
    inverseOnSurface = Schenbrunn_Yellowmd_md_theme_dark_inverseOnSurface,
    inverseSurface = Schenbrunn_Yellowmd_md_theme_dark_inverseSurface,
    inversePrimary = Schenbrunn_Yellowmd_md_theme_dark_inversePrimary,
    surfaceTint = Schenbrunn_Yellowmd_md_theme_dark_surfaceTint,
    outlineVariant = Schenbrunn_Yellowmd_md_theme_dark_outlineVariant,
    scrim = Schenbrunn_Yellowmd_md_theme_dark_scrim,
)

@Composable
fun SchenbrunnYellowAppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (!useDarkTheme) {
        SchenbrunnYellowLightColors
    } else {
        SchenbrunnYellowDarkColors
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}