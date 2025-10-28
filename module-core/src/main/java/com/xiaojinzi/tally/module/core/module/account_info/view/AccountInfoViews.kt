package com.xiaojinzi.tally.module.core.module.account_info.view

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.xiaojinzi.component.impl.routeApi
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.ktx.format2f
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.support.ktx.toStringItemDto
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_LARGE
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_NORMAL
import com.xiaojinzi.tally.lib.res.ui.AppWidthSpace
import com.xiaojinzi.tally.module.base.support.AppRouterCoreApi
import com.xiaojinzi.tally.module.base.support.AppServices
import com.xiaojinzi.tally.module.base.view.compose.AppCommonEmptyDataView
import com.xiaojinzi.tally.module.base.view.compose.AppbarNormalM3
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun AccountInfoView(
    needInit: Boolean? = false,
) {
    val context = LocalContext.current
    val isAssetsVisible by AppServices.appConfigSpi.isAssetsVisibleStateOb.collectAsState(
        initial = false
    )
    BusinessContentView<AccountInfoViewModel>(
        needInit = needInit,
    ) { vm ->
        val allAccount by vm.allAccountStateOb.collectAsState(initial = emptyList())
        val assets by vm.assetsStateOb.collectAsState(initial = null)
        val dept by vm.deptStateOb.collectAsState(initial = null)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .nothing(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Spacer(
                modifier = Modifier
                    .height(height = 16.dp)
                    .nothing()
            )

            ConstraintLayout(
                modifier = Modifier
                    .padding(horizontal = APP_PADDING_LARGE.dp, vertical = 0.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(shape = MaterialTheme.shapes.medium)
                    .background(color = MaterialTheme.colorScheme.secondary)
                    .padding(bottom = APP_PADDING_LARGE.dp)
                    .nothing(),
            ) {
                val (
                    textNetAssetsName, iconEye,
                    textNetAssetsValue,
                    textAssetsName,
                    textAssetsValue,
                    textDeptName,
                    textDeptValue,
                ) = createRefs()

                Text(
                    modifier = Modifier
                        .constrainAs(ref = textNetAssetsName) {
                            this.start.linkTo(
                                anchor = parent.start,
                                margin = APP_PADDING_LARGE.dp,
                            )
                            this.centerVerticallyTo(other = iconEye)
                        }
                        .nothing(),
                    text = "净资产",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSecondary,
                    ),
                    textAlign = TextAlign.Start,
                )

                IconButton(
                    modifier = Modifier
                        .scale(scale = 0.9f)
                        .constrainAs(ref = iconEye) {
                            this.start.linkTo(
                                anchor = textNetAssetsName.end,
                                margin = 0.dp,
                            )
                            this.top.linkTo(
                                anchor = parent.top,
                                margin = 0.dp
                            )
                        }
                        .nothing(),
                    onClick = {
                        AppServices
                            .appConfigSpi
                            .switchAssetsVisible(
                                b = !isAssetsVisible,
                            )
                    },
                ) {
                    Icon(
                        modifier = Modifier
                            .size(size = 16.dp)
                            .nothing(),
                        painter = painterResource(
                            id = if (isAssetsVisible) {
                                com.xiaojinzi.tally.lib.res.R.drawable.res_eye1
                            } else {
                                com.xiaojinzi.tally.lib.res.R.drawable.res_eye1_closed
                            }
                        ),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSecondary,
                    )
                }

                Text(
                    modifier = Modifier
                        .constrainAs(ref = textNetAssetsValue) {
                            this.start.linkTo(
                                anchor = textNetAssetsName.start,
                                margin = 0.dp,
                            )
                            this.top.linkTo(
                                anchor = textNetAssetsName.bottom,
                                margin = 4.dp,
                            )
                        }
                        .nothing(),
                    text = if (isAssetsVisible) {
                        ((assets?.value ?: 0f) + (dept?.value ?: 0f)).format2f()
                    } else {
                        "*****"
                    },
                    style = MaterialTheme.typography.titleLarge.copy(
                        contentColorFor(backgroundColor = MaterialTheme.colorScheme.secondary)
                    ),
                    textAlign = TextAlign.Start,
                )

                Text(
                    modifier = Modifier
                        .constrainAs(ref = textAssetsName) {
                            this.start.linkTo(
                                anchor = textNetAssetsName.start,
                                margin = 0.dp,
                            )
                            this.bottom.linkTo(
                                anchor = textAssetsValue.bottom,
                                margin = 2.dp,
                            )
                        }
                        .nothing(),
                    text = "资产",
                    style = MaterialTheme.typography.bodySmall.copy(
                        contentColorFor(backgroundColor = MaterialTheme.colorScheme.secondary)
                    ),
                    textAlign = TextAlign.Start,
                )

                Text(
                    modifier = Modifier
                        .constrainAs(ref = textAssetsValue) {
                            this.start.linkTo(
                                anchor = textAssetsName.end,
                                margin = 4.dp,
                            )
                            this.top.linkTo(
                                anchor = textNetAssetsValue.bottom,
                                margin = APP_PADDING_LARGE.dp,
                            )
                        }
                        .nothing(),
                    text = if (isAssetsVisible) {
                        (assets?.value ?: 0f).format2f()
                    } else {
                        "*****"
                    },
                    style = MaterialTheme.typography.titleMedium.copy(
                        contentColorFor(backgroundColor = MaterialTheme.colorScheme.secondary)
                    ),
                    textAlign = TextAlign.Start,
                )

                Text(
                    modifier = Modifier
                        .constrainAs(ref = textDeptName) {
                            this.end.linkTo(
                                anchor = textDeptValue.start,
                                margin = 4.dp,
                            )
                            this.bottom.linkTo(
                                anchor = textDeptValue.bottom,
                                margin = 2.dp,
                            )
                        }
                        .nothing(),
                    text = "负债",
                    style = MaterialTheme.typography.bodySmall.copy(
                        contentColorFor(backgroundColor = MaterialTheme.colorScheme.secondary)
                    ),
                    textAlign = TextAlign.Start,
                )

                Text(
                    modifier = Modifier
                        .constrainAs(ref = textDeptValue) {
                            this.end.linkTo(anchor = parent.end, margin = APP_PADDING_LARGE.dp)
                            this.centerVerticallyTo(other = textAssetsValue)
                        }
                        .nothing(),
                    text = if (isAssetsVisible) {
                        (dept?.value ?: 0f).format2f()
                    } else {
                        "*****"
                    },
                    style = MaterialTheme.typography.titleMedium.copy(
                        contentColorFor(backgroundColor = MaterialTheme.colorScheme.secondary),
                    ),
                    textAlign = TextAlign.Start,
                )

            }

            if (allAccount.isEmpty()) {
                AppCommonEmptyDataView(
                    modifier = Modifier
                        .fillMaxSize()
                        .nothing(),
                    text = "暂无资产账户".toStringItemDto(),
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .padding(horizontal = APP_PADDING_LARGE.dp, vertical = APP_PADDING_LARGE.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clip(
                            shape = MaterialTheme.shapes.small,
                        )
                        .background(color = MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp))
                        .nothing(),
                ) {
                    items(
                        items = allAccount,
                    ) { item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .clickable {
                                    AppRouterCoreApi::class
                                        .routeApi()
                                        .toAccountDetailView(
                                            context = context,
                                            accountId = item.id,
                                        )
                                }
                                .padding(
                                    horizontal = APP_PADDING_NORMAL.dp,
                                    vertical = APP_PADDING_NORMAL.dp,
                                )
                                .nothing(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(size = 24.dp)
                                    .nothing(),
                                painter = item.iconRsd?.let {
                                    painterResource(id = it)
                                } ?: ColorPainter(
                                    color = Color.Transparent,
                                ),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.secondary,
                            )

                            AppWidthSpace()

                            Column {
                                Text(
                                    text = "${item.name.orEmpty()}${
                                        if(item.isDefault) {
                                            " (默认)"
                                        } else {
                                            ""
                                        }
                                    }",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        color = MaterialTheme.colorScheme.secondary,
                                    ),
                                    textAlign = TextAlign.Start,
                                )
                                item.userCacheInfo?.let { userCacheInfo ->
                                    Spacer(
                                        modifier = Modifier
                                            .height(height = 4.dp)
                                            .nothing()
                                    )
                                    Row(
                                        modifier = Modifier
                                            .wrapContentSize()
                                            .nothing(),
                                        verticalAlignment = Alignment.Bottom,
                                    ) {
                                        Icon(
                                            modifier = Modifier
                                                .size(size = 12.dp)
                                                .nothing(),
                                            painter = painterResource(id = com.xiaojinzi.tally.lib.res.R.drawable.res_people1),
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.primary.copy(
                                                alpha = 0.5f,
                                            ),
                                        )
                                        Spacer(
                                            modifier = Modifier
                                                .width(width = 4.dp)
                                                .nothing()
                                        )
                                        Text(
                                            modifier = Modifier
                                                .nothing(),
                                            text = userCacheInfo.name.orEmpty(),
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                color = MaterialTheme.colorScheme.primary.copy(
                                                    alpha = 0.5f,
                                                ),
                                            ),
                                            textAlign = TextAlign.Start,
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.weight(weight = 1f, fill = true))

                            Text(
                                text = if (isAssetsVisible) {
                                    item.balanceCurrent.value.format2f()
                                } else {
                                    "*****"
                                },
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    color = MaterialTheme.colorScheme.secondary,
                                ),
                                textAlign = TextAlign.Start,
                            )

                        }
                    }
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
fun AccountInfoViewWrap(
    isShowBackIcon: Boolean = true,
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            if (isShowBackIcon) {
                AppbarNormalM3(
                    title = "资产".toStringItemDto(),
                )
            } else {
                AppbarNormalM3(
                    backIcon = null,
                    title = "资产".toStringItemDto(),
                )
            }

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    AppRouterCoreApi::class
                        .routeApi()
                        .toAccountCrudView(
                            context = context,
                        )
                }
            ) {
                Icon(
                    modifier = Modifier
                        .nothing(),
                    painter = rememberVectorPainter(image = Icons.Sharp.Add),
                    contentDescription = null,
                )
            }
        },
    ) {
        Box(
            modifier = Modifier
                .padding(top = it.calculateTopPadding())
                .nothing(),
        ) {
            AccountInfoView()
        }
    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun AccountInfoViewPreview() {
    AccountInfoView(
        needInit = false,
    )
}