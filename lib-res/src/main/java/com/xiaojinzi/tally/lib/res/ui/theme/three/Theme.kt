package com.xiaojinzi.tally.lib.res.ui.theme.three

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext


private val OliveGreenLightColors = lightColorScheme(
    primary = OliveGreen_md_theme_light_primary,
    onPrimary = OliveGreen_md_theme_light_onPrimary,
    primaryContainer = OliveGreen_md_theme_light_primaryContainer,
    onPrimaryContainer = OliveGreen_md_theme_light_onPrimaryContainer,
    secondary = OliveGreen_md_theme_light_secondary,
    onSecondary = OliveGreen_md_theme_light_onSecondary,
    secondaryContainer = OliveGreen_md_theme_light_secondaryContainer,
    onSecondaryContainer = OliveGreen_md_theme_light_onSecondaryContainer,
    tertiary = OliveGreen_md_theme_light_tertiary,
    onTertiary = OliveGreen_md_theme_light_onTertiary,
    tertiaryContainer = OliveGreen_md_theme_light_tertiaryContainer,
    onTertiaryContainer = OliveGreen_md_theme_light_onTertiaryContainer,
    error = OliveGreen_md_theme_light_error,
    errorContainer = OliveGreen_md_theme_light_errorContainer,
    onError = OliveGreen_md_theme_light_onError,
    onErrorContainer = OliveGreen_md_theme_light_onErrorContainer,
    background = OliveGreen_md_theme_light_background,
    onBackground = OliveGreen_md_theme_light_onBackground,
    surface = OliveGreen_md_theme_light_surface,
    onSurface = OliveGreen_md_theme_light_onSurface,
    surfaceVariant = OliveGreen_md_theme_light_surfaceVariant,
    onSurfaceVariant = OliveGreen_md_theme_light_onSurfaceVariant,
    outline = OliveGreen_md_theme_light_outline,
    inverseOnSurface = OliveGreen_md_theme_light_inverseOnSurface,
    inverseSurface = OliveGreen_md_theme_light_inverseSurface,
    inversePrimary = OliveGreen_md_theme_light_inversePrimary,
    surfaceTint = OliveGreen_md_theme_light_surfaceTint,
    outlineVariant = OliveGreen_md_theme_light_outlineVariant,
    scrim = OliveGreen_md_theme_light_scrim,
)


private val OliveGreenDarkColors = darkColorScheme(
    primary = OliveGreen_md_theme_dark_primary,
    onPrimary = OliveGreen_md_theme_dark_onPrimary,
    primaryContainer = OliveGreen_md_theme_dark_primaryContainer,
    onPrimaryContainer = OliveGreen_md_theme_dark_onPrimaryContainer,
    secondary = OliveGreen_md_theme_dark_secondary,
    onSecondary = OliveGreen_md_theme_dark_onSecondary,
    secondaryContainer = OliveGreen_md_theme_dark_secondaryContainer,
    onSecondaryContainer = OliveGreen_md_theme_dark_onSecondaryContainer,
    tertiary = OliveGreen_md_theme_dark_tertiary,
    onTertiary = OliveGreen_md_theme_dark_onTertiary,
    tertiaryContainer = OliveGreen_md_theme_dark_tertiaryContainer,
    onTertiaryContainer = OliveGreen_md_theme_dark_onTertiaryContainer,
    error = OliveGreen_md_theme_dark_error,
    errorContainer = OliveGreen_md_theme_dark_errorContainer,
    onError = OliveGreen_md_theme_dark_onError,
    onErrorContainer = OliveGreen_md_theme_dark_onErrorContainer,
    background = OliveGreen_md_theme_dark_background,
    onBackground = OliveGreen_md_theme_dark_onBackground,
    surface = OliveGreen_md_theme_dark_surface,
    onSurface = OliveGreen_md_theme_dark_onSurface,
    surfaceVariant = OliveGreen_md_theme_dark_surfaceVariant,
    onSurfaceVariant = OliveGreen_md_theme_dark_onSurfaceVariant,
    outline = OliveGreen_md_theme_dark_outline,
    inverseOnSurface = OliveGreen_md_theme_dark_inverseOnSurface,
    inverseSurface = OliveGreen_md_theme_dark_inverseSurface,
    inversePrimary = OliveGreen_md_theme_dark_inversePrimary,
    surfaceTint = OliveGreen_md_theme_dark_surfaceTint,
    outlineVariant = OliveGreen_md_theme_dark_outlineVariant,
    scrim = OliveGreen_md_theme_dark_scrim,
)

/**
 * 橄榄绿的主题颜色
 */
@Composable
fun OliveGreenAppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (!useDarkTheme) {
        OliveGreenLightColors
    } else {
        OliveGreenDarkColors
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}

@Composable
fun OliveGreenDynamicAppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current
    val colors = if (useDarkTheme) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            dynamicDarkColorScheme(context = context)
        } else {
            OliveGreenDarkColors
        }
    } else {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            dynamicLightColorScheme(context = context)
        } else {
            OliveGreenLightColors
        }
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}