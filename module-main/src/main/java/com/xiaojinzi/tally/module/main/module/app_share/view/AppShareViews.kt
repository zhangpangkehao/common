package com.xiaojinzi.tally.module.main.module.app_share.view

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.compose.GridView
import com.xiaojinzi.support.compose.util.clickableNoRipple
import com.xiaojinzi.support.compose.util.contentWithComposable
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.support.ktx.tryFinishActivity
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_LARGE
import com.xiaojinzi.tally.module.main.module.app_share.domain.AppShareIntent
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun AppShareView(
    needInit: Boolean? = false,
) {
    val context = LocalContext.current
    BusinessContentView<AppShareViewModel>(
        needInit = needInit,
    ) { vm ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .nothing(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(weight = 1f, fill = true)
                    .background(
                        color = Color.Black.copy(
                            alpha = 0.44f,
                        )
                    )
                    .clickableNoRipple {
                        context.tryFinishActivity()
                    }
                    .nothing()
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                    )
                    .nothing(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(horizontal = 0.dp, vertical = APP_PADDING_LARGE.dp)
                        .nothing(),
                    text = "分享一刻记账",
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                    ),
                    textAlign = TextAlign.Start,
                )
                Spacer(
                    modifier = Modifier
                        .height(height = 20.dp)
                        .nothing()
                )
                GridView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .nothing(),
                    items = AppShareList,
                    columnNumber = 3,
                    contentCombineItem = { index, item ->
                        Column(
                            modifier = Modifier
                                .wrapContentSize()
                                .clickableNoRipple {
                                    when (index) {
                                        0 -> {
                                            vm.addIntent(
                                                intent = AppShareIntent.ShareAppForWxChat,
                                            )
                                        }

                                        1 -> {
                                            vm.addIntent(
                                                intent = AppShareIntent.ShareAppForWxSTATE,
                                            )
                                        }

                                        2 -> {
                                            vm.addIntent(
                                                intent = AppShareIntent.CopyToClipboard,
                                            )
                                        }
                                    }
                                }
                                .nothing(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(size = 24.dp)
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
                                text = item.name.contentWithComposable(),
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = MaterialTheme.colorScheme.primary.copy(
                                        alpha = 0.8f,
                                    ),
                                ),
                                textAlign = TextAlign.Start,
                            )
                        }
                    }
                )
                Spacer(
                    modifier = Modifier
                        .navigationBarsPadding()
                        .height(height = 20.dp)
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
fun AppShareViewWrap() {
    AppShareView()
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun AppShareViewPreview() {
    AppShareView(
        needInit = false,
    )
}