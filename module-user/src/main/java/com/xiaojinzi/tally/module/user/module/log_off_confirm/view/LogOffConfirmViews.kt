package com.xiaojinzi.tally.module.user.module.log_off_confirm.view

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.compose.BottomView
import com.xiaojinzi.support.compose.util.circleClip
import com.xiaojinzi.support.ktx.getActivity
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.support.ktx.tryFinishActivity
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_NORMAL
import com.xiaojinzi.tally.lib.res.ui.AppBackgroundColor
import com.xiaojinzi.tally.lib.res.ui.topShape
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun LogOffConfirmView(
    needInit: Boolean? = false,
) {
    val context = LocalContext.current
    var countDown by remember { mutableIntStateOf(5) }
    val canLogOff by remember {
        derivedStateOf {
            countDown <= 0
        }
    }
    LaunchedEffect(key1 = Unit) {
        while (countDown > 0) {
            delay(1000)
            countDown--
        }
    }
    BusinessContentView<LogOffConfirmViewModel>(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .nothing(),
        needInit = needInit,
    ) { vm ->
        BottomView(
            maxFraction = 1f,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(
                        shape = MaterialTheme.shapes.medium.topShape(),
                    )
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                    )
                    .nothing(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                // empty
                Spacer(
                    modifier = Modifier
                        .height(height = 20.dp)
                        .nothing()
                )
                Text(
                    text = "注销账号",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center,
                )
                Spacer(
                    modifier = Modifier
                        .height(height = 20.dp)
                        .nothing()
                )
                Text(
                    modifier = Modifier
                        .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clip(
                            shape = MaterialTheme.shapes.medium,
                        )
                        .background(
                            color = AppBackgroundColor,
                        )
                        .padding(
                            horizontal = APP_PADDING_NORMAL.dp,
                            vertical = APP_PADDING_NORMAL.dp
                        )
                        .nothing(),
                    text = "1. 注销账号将会删除您的账号, 以及该账号上包括但不限于个人资料、会员权益、记账数据等的全部数据。\n2. 注销后该账号将无法使用且无法找回, 请慎重考虑",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium,
                    ),
                    textAlign = TextAlign.Start,
                )
                Spacer(
                    modifier = Modifier
                        .height(height = 20.dp)
                        .nothing()
                )
                Button(
                    modifier = Modifier
                        .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .circleClip()
                        .nothing(),
                    onClick = {
                        context.tryFinishActivity()
                    },
                ) {
                    Text(
                        modifier = Modifier
                            .nothing(),
                        text = "我再想想",
                        style = MaterialTheme.typography.titleSmall.copy(
                            color = MaterialTheme.colorScheme.onPrimary,
                        ),
                        textAlign = TextAlign.Center,
                    )
                }
                Spacer(
                    modifier = Modifier
                        .height(height = 10.dp)
                        .nothing()
                )
                TextButton(
                    modifier = Modifier
                        .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .circleClip()
                        .nothing(),
                    onClick = {
                        context.getActivity()?.run {
                            this.setResult(Activity.RESULT_OK)
                            this.finish()
                        }
                    },
                    enabled = canLogOff,
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = MaterialTheme.colorScheme.error,
                        contentColor = MaterialTheme.colorScheme.onError,
                        disabledContainerColor = MaterialTheme.colorScheme.error.copy(
                            alpha = 0.6f,
                        ),
                        disabledContentColor = MaterialTheme.colorScheme.onError,
                    ),
                ) {
                    Text(
                        text = "我已知晓, 确认注销 ${
                            if (countDown > 0) {
                                "(${countDown})"
                            } else {
                                ""
                            }
                        }",
                        style = MaterialTheme.typography.titleSmall,
                        textAlign = TextAlign.Center,
                    )
                }
                Spacer(
                    modifier = Modifier
                        .navigationBarsPadding()
                        .height(height = 10.dp)
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
fun LogOffConfirmViewWrap() {
    LogOffConfirmView()
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun LogOffConfirmViewPreview() {
    LogOffConfirmView(
        needInit = false,
    )
}