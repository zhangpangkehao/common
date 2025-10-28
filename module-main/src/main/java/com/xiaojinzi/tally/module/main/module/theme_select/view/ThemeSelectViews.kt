package com.xiaojinzi.tally.module.main.module.theme_select.view

import android.annotation.SuppressLint
import android.os.Build
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.compose.GridView
import com.xiaojinzi.support.compose.util.circleClip
import com.xiaojinzi.support.compose.util.clickableNoRipple
import com.xiaojinzi.support.ktx.notSupportError
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.support.ktx.toStringItemDto
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_LARGE
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_SMALL
import com.xiaojinzi.tally.lib.res.ui.AppBackgroundColor
import com.xiaojinzi.tally.lib.res.ui.AppHeightSpace
import com.xiaojinzi.tally.module.base.support.AppServices
import com.xiaojinzi.tally.module.base.view.compose.AppbarNormalM3
import com.xiaojinzi.tally.module.main.module.theme_select.domain.ThemeSelectIntent
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun ThemeSelectView(
    needInit: Boolean? = null,
) {
    val context = LocalContext.current
    BusinessContentView<ThemeSelectViewModel>(
        needInit = needInit,
    ) { vm ->
        val isUseDark by AppServices.appInfoSpi.isDarkThemeStateOb.collectAsState(initial = false)
        val themeList by vm.themeListStateObVo.collectAsState(initial = emptyList())

        val dynamicLightColors = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            dynamicLightColorScheme(context = context)
        } else {
            null
        }

        val dynamicDarkColors = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            dynamicDarkColorScheme(context = context)
        } else {
            null
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = AppBackgroundColor,
                )
                .statusBarsPadding()
                .nothing(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            AppHeightSpace()

            GridView(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .nothing(),
                items = themeList,
                columnNumber = 3,
                horizontalSpace = 4.dp,
                verticalSpace = 24.dp,
            ) { item ->
                Column(
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                        .wrapContentSize()
                        .nothing(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    val colorCount = 3
                    val boxSize = 40
                    val containerSize = (colorCount - 1) * boxSize / 2 + boxSize
                    Box(
                        modifier = Modifier
                            .requiredWidth(containerSize.dp)
                            .requiredHeight((boxSize * 3 / 2).dp)
                            .clickableNoRipple {
                                vm.addIntent(
                                    intent = ThemeSelectIntent.ThemeColorSet(
                                        context = context,
                                        isNeedVip = item.isNeedVip,
                                        themeName = item.themeName,
                                    )
                                )
                            }
                            .nothing(),
                    ) {
                        (0..2).forEach { index ->
                            when (item) {
                                is ThemeSystemSelectItemVo -> {
                                    dynamicLightColors?.let {
                                        dynamicDarkColors?.let {
                                            Box(
                                                modifier = Modifier
                                                    .offset(
                                                        x = (boxSize * index / 2).dp,
                                                    )
                                                    .size(boxSize.dp)
                                                    .circleClip()
                                                    .background(
                                                        color = when (index) {
                                                            0 -> if (isUseDark) {
                                                                dynamicDarkColors.primary
                                                            } else {
                                                                dynamicLightColors.primary
                                                            }

                                                            1 -> if (isUseDark) {
                                                                dynamicDarkColors.secondary
                                                            } else {
                                                                dynamicLightColors.secondary
                                                            }

                                                            2 -> if (isUseDark) {
                                                                dynamicDarkColors.tertiary
                                                            } else {
                                                                dynamicLightColors.tertiary
                                                            }

                                                            else -> notSupportError()
                                                        }
                                                    )
                                                    .nothing(),
                                            )
                                        }
                                    }
                                }

                                is ThemeNormalSelectItemVo -> {
                                    Box(
                                        modifier = Modifier
                                            .offset(
                                                x = (boxSize * index / 2).dp,
                                            )
                                            .size(boxSize.dp)
                                            .circleClip()
                                            .background(
                                                color = when (index) {
                                                    0 -> if (isUseDark) {
                                                        item.darkPrimary
                                                    } else {
                                                        item.lightPrimary
                                                    }

                                                    1 -> if (isUseDark) {
                                                        item.darkSecondary
                                                    } else {
                                                        item.lightSecondary
                                                    }

                                                    2 -> if (isUseDark) {
                                                        item.darkTertiary
                                                    } else {
                                                        item.lightTertiary
                                                    }

                                                    else -> notSupportError()
                                                }
                                            )
                                            .nothing(),
                                    )
                                }
                            }
                        }
                        (0..2).forEach { index ->
                            when (item) {
                                is ThemeSystemSelectItemVo -> {
                                    dynamicLightColors?.let {
                                        dynamicDarkColors?.let {
                                            Box(
                                                modifier = Modifier
                                                    .offset(
                                                        x = (boxSize * index / 2).dp,
                                                        y = (boxSize / 2).dp,
                                                    )
                                                    .size(boxSize.dp)
                                                    .circleClip()
                                                    .background(
                                                        color = when (index) {
                                                            0 -> if (isUseDark) {
                                                                dynamicDarkColors.primaryContainer
                                                            } else {
                                                                dynamicLightColors.primaryContainer
                                                            }

                                                            1 -> if (isUseDark) {
                                                                dynamicDarkColors.secondaryContainer
                                                            } else {
                                                                dynamicLightColors.secondaryContainer
                                                            }

                                                            2 -> if (isUseDark) {
                                                                dynamicDarkColors.tertiaryContainer
                                                            } else {
                                                                dynamicLightColors.tertiaryContainer
                                                            }

                                                            else -> notSupportError()
                                                        }
                                                    )
                                                    .nothing(),
                                            )
                                        }
                                    }
                                }

                                is ThemeNormalSelectItemVo -> {
                                    Box(
                                        modifier = Modifier
                                            .offset(
                                                x = (boxSize * index / 2).dp,
                                                y = (boxSize / 2).dp,
                                            )
                                            .size(boxSize.dp)
                                            .circleClip()
                                            .background(
                                                color = when (index) {
                                                    0 -> if (isUseDark) {
                                                        item.darkPrimaryContainer
                                                    } else {
                                                        item.lightPrimaryContainer
                                                    }

                                                    1 -> if (isUseDark) {
                                                        item.darkSecondaryContainer
                                                    } else {
                                                        item.lightSecondaryContainer
                                                    }

                                                    2 -> if (isUseDark) {
                                                        item.darkTertiaryContainer
                                                    } else {
                                                        item.lightTertiaryContainer
                                                    }

                                                    else -> notSupportError()
                                                }
                                            )
                                            .nothing(),
                                    )
                                }
                            }
                        }
                        if (item.isSelected) {
                            when (item) {
                                is ThemeSystemSelectItemVo -> {
                                    dynamicLightColors?.let {
                                        dynamicDarkColors?.let {
                                            Icon(
                                                modifier = Modifier
                                                    .align(alignment = Alignment.BottomEnd)
                                                    .padding(horizontal = 6.dp, vertical = 6.dp)
                                                    .size(size = 16.dp)
                                                    .nothing(),
                                                painter = rememberVectorPainter(image = Icons.Filled.CheckCircle),
                                                contentDescription = null,
                                                tint = if (isUseDark) {
                                                    dynamicDarkColors.primary
                                                } else {
                                                    dynamicLightColors.primary
                                                },
                                            )
                                        }
                                    }
                                }

                                is ThemeNormalSelectItemVo -> {
                                    Icon(
                                        modifier = Modifier
                                            .align(alignment = Alignment.BottomEnd)
                                            .padding(horizontal = 6.dp, vertical = 6.dp)
                                            .size(size = 16.dp)
                                            .nothing(),
                                        painter = rememberVectorPainter(image = Icons.Filled.CheckCircle),
                                        contentDescription = null,
                                        tint = if (isUseDark) {
                                            item.darkPrimary
                                        } else {
                                            item.lightPrimary
                                        },
                                    )
                                }
                            }
                        } else {
                            if (item.isNeedVip) {
                                Image(
                                    modifier = Modifier
                                        .align(alignment = Alignment.BottomEnd)
                                        .padding(horizontal = 6.dp, vertical = 6.dp)
                                        .size(size = 16.dp)
                                        .nothing(),
                                    painter = painterResource(
                                        id = com.xiaojinzi.tally.lib.res.R.drawable.res_vip3,
                                    ),
                                    contentDescription = null,
                                )
                            }
                        }
                    }

                    AppHeightSpace()

                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onSurface,
                        ),
                        textAlign = TextAlign.Start,
                    )

                }

            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                AppHeightSpace()

                Row(
                    modifier = Modifier
                        .padding(horizontal = APP_PADDING_LARGE.dp, vertical = APP_PADDING_SMALL.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .nothing(),
                    verticalAlignment = Alignment.Top,
                ) {
                    Text(
                        text = "系统配色：",
                        style = MaterialTheme.typography.titleSmall.copy(
                            color = MaterialTheme.colorScheme.onSurface,
                        ),
                        textAlign = TextAlign.Start,
                    )
                    Text(
                        text = "Android12 开始, 系统支持了动态配色, 您可以选择系统配色, 以便 App 自动使用系统配色",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.outline,
                        ),
                        textAlign = TextAlign.Start,
                    )
                }
            }

        }
    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun ThemeSelectViewWrap() {
    val context = LocalContext.current
    val vm: ThemeSelectViewModel = viewModel()
    Scaffold(
        topBar = {
            AppbarNormalM3(
                title = "主题皮肤".toStringItemDto(),
                menu1IconRsd = com.xiaojinzi.tally.lib.res.R.drawable.res_more3,
                menu1ClickListener = {
                    vm.addIntent(
                        intent = ThemeSelectIntent.OnMoreClick(
                            context = context,
                        )
                    )
                },
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(top = it.calculateTopPadding())
                .nothing(),
        ) {
            ThemeSelectView()
        }
    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun ThemeSelectViewPreview() {
    ThemeSelectView(
        needInit = false,
    )
}