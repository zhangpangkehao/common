package com.xiaojinzi.tally.module.user.module.vip_expire_remind.view

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.xiaojinzi.component.impl.routeApi
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.compose.util.circleClip
import com.xiaojinzi.support.compose.util.clickableNoRipple
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.support.ktx.tryFinishActivity
import com.xiaojinzi.tally.lib.res.model.user.ALL_VIP_RIGHT_LIST
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_NORMAL
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_SMALL
import com.xiaojinzi.tally.lib.res.ui.AppHeightSpace
import com.xiaojinzi.tally.module.base.support.AppRouterUserApi
import com.xiaojinzi.tally.module.base.support.AppServices
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun VipExpireRemindView(
    needInit: Boolean? = false,
) {
    val context = LocalContext.current
    val isTipBeforeVipExpire by AppServices
        .appConfigSpi
        .isTipBeforeVipExpireStateOb
        .collectAsState(
            initial = true
        )
    BusinessContentView<VipExpireRemindViewModel>(
        needInit = needInit,
    ) { vm ->
        Dialog(
            onDismissRequest = {
                // 增加弹框取消的难度
                // context.tryFinishActivity()
            }
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(
                        shape = MaterialTheme.shapes.medium,
                    )
                    .nothing(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clip(
                            shape = MaterialTheme.shapes.medium,
                        )
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                        )
                        /* .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    MaterialTheme.colorScheme.primary.copy(
                                        alpha = 0.6f,
                                    ),
                                    MaterialTheme.colorScheme.primary,
                                )
                            ),
                        )*/
                        .padding(horizontal = 0.dp, vertical = APP_PADDING_NORMAL.dp)
                        .nothing(),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = (APP_PADDING_NORMAL * 2).dp, vertical = 0.dp)
                            .nothing(),
                        text = "续费会员享受全部权益",
                        // 斜体
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                        ),
                        textAlign = TextAlign.Start,
                    )
                    AppHeightSpace()
                    LazyColumn(
                        modifier = Modifier
                            .padding(horizontal = (APP_PADDING_NORMAL * 2).dp, vertical = 0.dp)
                            .fillMaxWidth()
                            .height(height = 200.dp)
                            .clip(
                                shape = MaterialTheme.shapes.small,
                            )
                            .background(
                                color = MaterialTheme.colorScheme.surface,
                            )
                            .nothing(),
                        contentPadding = PaddingValues(
                            all = APP_PADDING_SMALL.dp,
                        ),
                    ) {
                        item(key = "header") {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .nothing(),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Spacer(modifier = Modifier.weight(weight = 1f, fill = true))
                                Text(
                                    modifier = Modifier
                                        .weight(weight = 1f, fill = true)
                                        .wrapContentHeight()
                                        .nothing(),
                                    text = "普通用户",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface,
                                    ),
                                    textAlign = TextAlign.Center,
                                )
                                Text(
                                    modifier = Modifier
                                        .weight(weight = 1f, fill = true)
                                        .wrapContentHeight()
                                        .nothing(),
                                    text = "Vip",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface,
                                    ),
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                        items(
                            items = ALL_VIP_RIGHT_LIST,
                        ) { item ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .padding(horizontal = 0.dp, vertical = 5.dp)
                                    .nothing(),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    modifier = Modifier
                                        .weight(weight = 1f, fill = true)
                                        .wrapContentHeight()
                                        .nothing(),
                                    text = item.title,
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        fontWeight = FontWeight.SemiBold,
                                        color = MaterialTheme.colorScheme.onSurface,
                                    ),
                                    textAlign = TextAlign.Start,
                                )
                                Icon(
                                    modifier = Modifier
                                        .weight(weight = 1f, fill = true)
                                        .size(size = 16.dp)
                                        .nothing(),
                                    painter = painterResource(
                                        id = com.xiaojinzi.tally.lib.res.R.drawable.res_clear1,
                                    ),
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.error,
                                )
                                Icon(
                                    modifier = Modifier
                                        .weight(weight = 1f, fill = true)
                                        .size(size = 20.dp)
                                        .nothing(),
                                    painter = rememberVectorPainter(
                                        image = Icons.TwoTone.Check,
                                    ),
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                )
                            }
                        }
                    }
                    AppHeightSpace()
                    Button(
                        modifier = Modifier
                            .padding(horizontal = (APP_PADDING_NORMAL * 2).dp, vertical = 0.dp)
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .nothing(),
                        onClick = {
                            AppRouterUserApi::class
                                .routeApi()
                                .toVipBuyView(
                                    context = context,
                                ) {
                                    context.tryFinishActivity()
                                }
                        }) {
                        Text(
                            text = "立即续费",
                            textAlign = TextAlign.Start,
                        )
                    }
                    Text(
                        modifier = Modifier
                            .align(
                                alignment = Alignment.CenterHorizontally,
                            )
                            .wrapContentSize()
                            .circleClip()
                            .clickableNoRipple {
                                context.tryFinishActivity()
                            }
                            .padding(
                                horizontal = APP_PADDING_NORMAL.dp,
                                vertical = APP_PADDING_SMALL.dp
                            )
                            .nothing(),
                        text = "暂不续费",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                        ),
                        textAlign = TextAlign.Start,
                    )
                }
                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .nothing(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "不在提醒",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.White.copy(
                                alpha = 0.6f,
                            ),
                        ),
                        textAlign = TextAlign.Start,
                    )
                    Checkbox(
                        modifier = Modifier
                            .scale(scale = 0.8f)
                            .nothing(),
                        checked = !isTipBeforeVipExpire,
                        onCheckedChange = {
                            AppServices.appConfigSpi
                                .switchTipBeforeVipExpire(
                                    b = !it,
                                )
                        },
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
fun VipExpireRemindViewWrap() {
    VipExpireRemindView()
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun VipExpireRemindViewPreview() {
    VipExpireRemindView(
        needInit = false,
    )
}