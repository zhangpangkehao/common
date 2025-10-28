package com.xiaojinzi.tally.module.main.module.theme_select.view

import androidx.annotation.Keep
import androidx.compose.ui.graphics.Color

@Keep
sealed class ThemeSelectItemVo(
    open val name: String,
    open val themeName: String,
    open val isSelected: Boolean,
    open val isNeedVip: Boolean = false,
)

@Keep
data class ThemeNormalSelectItemVo(
    override val name: String,
    override val themeName: String,
    override val isSelected: Boolean,
    override val isNeedVip: Boolean = false,
    val lightPrimary: Color,
    val lightPrimaryContainer: Color,
    val darkPrimary: Color,
    val darkPrimaryContainer: Color,
    val lightSecondary: Color,
    val lightSecondaryContainer: Color,
    val darkSecondary: Color,
    val darkSecondaryContainer: Color,
    val lightTertiary: Color,
    val lightTertiaryContainer: Color,
    val darkTertiary: Color,
    val darkTertiaryContainer: Color,
): ThemeSelectItemVo(
    name = name,
    themeName = themeName,
    isSelected = isSelected,
    isNeedVip = isNeedVip,
)

@Keep
data class ThemeSystemSelectItemVo(
    override val name: String,
    override val themeName: String,
    override val isSelected: Boolean,
    override val isNeedVip: Boolean = false,
): ThemeSelectItemVo(
    name = name,
    themeName = themeName,
    isSelected = isSelected,
    isNeedVip = isNeedVip,
)