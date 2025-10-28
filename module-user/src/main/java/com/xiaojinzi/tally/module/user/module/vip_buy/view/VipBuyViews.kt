package com.xiaojinzi.tally.module.user.module.vip_buy.view

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.xiaojinzi.component.impl.routeApi
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.compose.util.circleClip
import com.xiaojinzi.support.compose.util.clickableNoRipple
import com.xiaojinzi.support.ktx.format2f
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.support.ktx.tryFinishActivity
import com.xiaojinzi.tally.lib.res.model.user.ALL_VIP_RIGHT_LIST
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_NORMAL
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_SMALL
import com.xiaojinzi.tally.lib.res.ui.AppBackgroundColor
import com.xiaojinzi.tally.lib.res.ui.AppHeightSpace
import com.xiaojinzi.tally.lib.res.ui.AppWidthSpace
import com.xiaojinzi.tally.module.base.support.AppRouterBaseApi
import com.xiaojinzi.tally.module.base.support.AppServices
import com.xiaojinzi.tally.module.user.module.vip_buy.domain.VipBuyIntent
import kotlinx.coroutines.InternalCoroutinesApi

private val Shape1 = object : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val temp = 0.15f
        return Outline.Generic(
            path = Path().apply {
                this.reset()
                this.lineTo(
                    x = 0f, y = size.height * (1f - 1 * temp),
                )
                this.quadraticBezierTo(
                    x1 = size.width * 1f / 4f, y1 = size.height * (1f - 2 * temp),
                    x2 = size.width * 2f / 4f, y2 = size.height * (1f - 1 * temp),
                )
                this.quadraticBezierTo(
                    x1 = size.width * 3f / 4f, y1 = size.height * (1f - 0 * temp),
                    x2 = size.width * 4f / 4f, y2 = size.height * (1f - 1 * temp),
                )
                this.lineTo(
                    x = size.width, y = 0f,
                )
                this.close()
            },
        )
    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun VipBuyView(
    needInit: Boolean? = null,
) {
    val context = LocalContext.current
    val isVip by AppServices.userSpi.isVipStateOb.collectAsState(initial = false)
    BusinessContentView<VipBuyViewModel>(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = AppBackgroundColor,
            )
            .nothing(),
        needInit = needInit,
    ) { vm ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .nothing(),
            contentAlignment = Alignment.Center,
        ) {
            val items by vm.itemsStateOb.collectAsState(initial = emptyList())
            val pageState = rememberPagerState {
                items.size
            }
            val currentItem = items.getOrNull(
                index = pageState.currentPage,
            )
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .fillMaxWidth()
                    .aspectRatio(ratio = 1.1f)
                    .clip(
                        shape = Shape1,
                    )
                    .background(
                        color = MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.8f,
                        ),
                    )
                    .nothing(),
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .nothing(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .statusBarsPadding()
                        .nothing(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(
                        modifier = Modifier
                            .nothing(),
                        onClick = {
                            context.tryFinishActivity()
                        },
                    ) {
                        Icon(
                            painter = rememberVectorPainter(
                                image = Icons.Filled.ArrowBack,
                            ),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary,
                        )
                    }
                }

                AppHeightSpace()

                if (items.isNotEmpty()) {
                    HorizontalPager(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .nothing(),
                        beyondViewportPageCount = 1,
                        state = pageState,
                        contentPadding = PaddingValues(horizontal = 52.dp),
                    ) { pageIndex ->
                        val itemScale by animateFloatAsState(
                            targetValue = if (pageState.currentPage == pageIndex) 1f else 0.85f,
                            animationSpec = tween(300),
                            label = "",
                        )
                        ConstraintLayout(
                            modifier = Modifier
                                .scale(scale = itemScale)
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .clip(
                                    shape = MaterialTheme.shapes.large,
                                )
                                .background(
                                    color = MaterialTheme.colorScheme.primary,
                                )
                                .nothing(),
                        ) {
                            val (
                                title,
                                desc,
                                priceFlag, price,
                                originPrice,
                            ) = createRefs()
                            val item = items.getOrNull(index = pageIndex)
                            Text(
                                modifier = Modifier
                                    .constrainAs(ref = title) {
                                        this.start.linkTo(
                                            anchor = parent.start,
                                            margin = APP_PADDING_NORMAL.dp
                                        )
                                        this.top.linkTo(
                                            anchor = parent.top,
                                            margin = APP_PADDING_NORMAL.dp
                                        )
                                    }
                                    .nothing(),
                                text = item?.title.orEmpty(),
                                style = MaterialTheme.typography.titleMedium.copy(
                                    color = MaterialTheme.colorScheme.onPrimary,
                                ),
                                textAlign = TextAlign.Start,
                            )
                            Text(
                                modifier = Modifier
                                    .constrainAs(ref = desc) {
                                        this.start.linkTo(
                                            anchor = parent.start,
                                            margin = APP_PADDING_NORMAL.dp
                                        )
                                        this.end.linkTo(
                                            anchor = parent.end,
                                            margin = APP_PADDING_NORMAL.dp
                                        )
                                        this.top.linkTo(
                                            anchor = title.bottom,
                                            margin = APP_PADDING_SMALL.dp
                                        )
                                    }
                                    .nothing(),
                                text = "1. 此价格一次性购买 Vip 时长, 不会自动续订\n2. 非 Vip 也可以使用其他基础功能",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    color = MaterialTheme.colorScheme.onPrimary,
                                ),
                                textAlign = TextAlign.Start,
                            )
                            Text(
                                modifier = Modifier
                                    .constrainAs(ref = priceFlag) {
                                        this.start.linkTo(
                                            anchor = parent.start,
                                            margin = APP_PADDING_NORMAL.dp
                                        )
                                        this.top.linkTo(anchor = desc.bottom, margin = 90.dp)
                                        this.bottom.linkTo(
                                            anchor = parent.bottom,
                                            margin = APP_PADDING_NORMAL.dp
                                        )
                                    }
                                    .nothing(),
                                text = "¥",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = MaterialTheme.typography.bodyMedium.fontSize.times(
                                        0.8f
                                    ),
                                    color = MaterialTheme.colorScheme.onPrimary,
                                ),
                                textAlign = TextAlign.Start,
                            )
                            Text(
                                modifier = Modifier
                                    .constrainAs(ref = price) {
                                        this.start.linkTo(
                                            anchor = priceFlag.end,
                                            margin = 0.dp
                                        )
                                        this.bottom.linkTo(
                                            anchor = priceFlag.bottom,
                                            margin = 0.dp
                                        )
                                    }
                                    .nothing(),
                                text = item?.price?.format2f().orEmpty(),
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimary,
                                ),
                                textAlign = TextAlign.Start,
                            )
                            if (item?.isSamePrice == false) {
                                Text(
                                    modifier = Modifier
                                        .constrainAs(ref = originPrice) {
                                            this.start.linkTo(
                                                anchor = parent.start,
                                                margin = APP_PADDING_NORMAL.dp
                                            )
                                            this.bottom.linkTo(
                                                anchor = price.top,
                                                margin = 0.dp
                                            )
                                        }
                                        .nothing(),
                                    text = "原价: ${item.originalPrice.format2f()}",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontWeight = FontWeight.Medium,
                                        color = MaterialTheme.colorScheme.onPrimary,
                                    ),
                                    textAlign = TextAlign.Start,
                                    textDecoration = TextDecoration.LineThrough,
                                )
                            }
                        }
                    }
                }

                Spacer(
                    modifier = Modifier
                        .height(height = 30.dp)
                        .nothing()
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .nothing(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Spacer(
                        modifier = Modifier
                            .width(width = 40.dp)
                            .height(2.dp)
                            .circleClip()
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        MaterialTheme.colorScheme.primary,
                                    ),
                                )
                            )
                            .nothing()
                    )
                    AppWidthSpace()
                    Text(
                        text = "会员权益",
                        style = MaterialTheme.typography.titleSmall.copy(
                            color = MaterialTheme.colorScheme.onSurface,
                        ),
                        textAlign = TextAlign.Start,
                    )
                    AppWidthSpace()
                    Spacer(
                        modifier = Modifier
                            .width(width = 40.dp)
                            .height(2.dp)
                            .circleClip()
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        MaterialTheme.colorScheme.primary,
                                        Color.Transparent,
                                    ),
                                )
                            )
                            .nothing()
                    )
                }

                AppHeightSpace()

                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(weight = 1f, fill = true)
                        .nothing(),
                    columns = GridCells.Fixed(count = 3)
                ) {
                    items(
                        items = ALL_VIP_RIGHT_LIST,
                    ) { item ->
                        Box(
                            modifier = Modifier
                                .wrapContentSize()
                                .nothing(),
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(horizontal = 0.dp, vertical = APP_PADDING_SMALL.dp)
                                    .wrapContentSize()
                                    .nothing(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .size(size = 16.dp)
                                        .nothing(),
                                    painter = painterResource(id = item.iconRsd),
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary.copy(
                                        alpha = 0.8f,
                                    ),
                                )
                                Spacer(
                                    modifier = Modifier
                                        .height(height = 4.dp)
                                        .nothing()
                                )
                                Text(
                                    text = item.title,
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        fontWeight = FontWeight.Medium,
                                        color = MaterialTheme.colorScheme.onSurface,
                                    ),
                                    textAlign = TextAlign.Start,
                                )
                                Spacer(
                                    modifier = Modifier
                                        .height(height = 2.dp)
                                        .nothing()
                                )
                                Text(
                                    text = item.desc,
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontSize = MaterialTheme.typography.labelSmall.fontSize.times(
                                            0.8f
                                        ),
                                        color = MaterialTheme.colorScheme.outline,
                                    ),
                                    textAlign = TextAlign.Start,
                                )
                            }
                            if (item.isComingSoon) {
                                Icon(
                                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .size(size = 24.dp)
                                        .nothing(),
                                    painter = painterResource(id = com.xiaojinzi.tally.lib.res.R.drawable.res_coming_soon1),
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.error,
                                )
                            }
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .nothing(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        modifier = Modifier
                            .size(size = 24.dp)
                            .nothing(),
                        painter = painterResource(
                            id = com.xiaojinzi.tally.lib.res.R.drawable.res_alipay1,
                        ),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                    AppWidthSpace()
                    Text(
                        text = "支付宝支付",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface,
                        ),
                        textAlign = TextAlign.Start,
                    )
                    Spacer(modifier = Modifier.weight(weight = 1f, fill = true))
                    RadioButton(
                        selected = true,
                        onClick = {
                        },
                    )
                }

                Button(
                    modifier = Modifier
                        .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 0.dp, vertical = APP_PADDING_SMALL.dp)
                        .nothing(),
                    onClick = {
                        items.getOrNull(
                            index = pageState.currentPage,
                        )?.let { item ->
                            vm.addIntent(
                                intent = VipBuyIntent.Submit(
                                    context = context,
                                    itemId = item.id,
                                )
                            )
                        }
                    }
                ) {
                    Row(
                        modifier = Modifier
                            .wrapContentSize()
                            .nothing(),
                        verticalAlignment = Alignment.Bottom,
                    ) {
                        Text(
                            modifier = Modifier
                                .alignByBaseline()
                                .wrapContentSize()
                                .nothing(),
                            text = "¥",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimary,
                            ),
                            textAlign = TextAlign.Start,
                        )
                        Text(
                            modifier = Modifier
                                .alignByBaseline()
                                .wrapContentSize()
                                .nothing(),
                            text = currentItem?.price?.format2f().orEmpty(),
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimary,
                            ),
                            textAlign = TextAlign.Start,
                        )
                        if (currentItem?.isSamePrice == false) {
                            Spacer(
                                modifier = Modifier
                                    .width(width = 4.dp)
                                    .nothing()
                            )
                            Text(
                                modifier = Modifier
                                    .alignByBaseline()
                                    .wrapContentSize()
                                    .nothing(),
                                text = "原价 ¥${
                                    currentItem.originalPrice.format2f()
                                }",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = MaterialTheme.colorScheme.onPrimary,
                                ),
                                textAlign = TextAlign.Start,
                                textDecoration = TextDecoration.LineThrough,
                            )
                        }
                    }
                    AppWidthSpace()
                    Text(
                        text = if (isVip) {
                            "立即续费"
                        } else {
                            "立即开通"
                        },
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = MaterialTheme.colorScheme.onPrimary,
                        ),
                        textAlign = TextAlign.Start,
                    )
                }

                Text(
                    modifier = Modifier
                        .clickableNoRipple {
                            AppRouterBaseApi::class
                                .routeApi()
                                .toWebView(
                                    context = context,
                                    url = AppServices.appInfoSpi.vipProtocolUrl,
                                )
                        }
                        .padding(horizontal = 20.dp, vertical = APP_PADDING_SMALL.dp)
                        .wrapContentSize()
                        .padding(horizontal = 0.dp, vertical = 5.dp)
                        .nothing(),
                    text = "会员服务协议",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.outline,
                    ),
                    textAlign = TextAlign.Start,
                )
                Spacer(
                    modifier = Modifier
                        .navigationBarsPadding()
                        .nothing()
                )
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
fun VipBuyViewWrap() {
    VipBuyView()
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun VipBuyViewPreview() {
    VipBuyView(
        needInit = false,
    )
}