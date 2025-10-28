package com.xiaojinzi.tally.module.core.module.first_sync.view

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.compose.util.clickableNoRipple
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.tally.lib.res.R
import com.xiaojinzi.tally.lib.res.ui.AppBackgroundColor
import com.xiaojinzi.tally.lib.res.ui.AppHeightSpace
import com.xiaojinzi.tally.module.core.module.first_sync.domain.FirstSyncIntent
import com.xiaojinzi.tally.module.core.module.first_sync.domain.FirstSyncUseCase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun FirstSyncView(
    needInit: Boolean? = false,
) {
    val context = LocalContext.current
    BackHandler {
        // empty
    }
    BusinessContentView<FirstSyncViewModel>(
        needInit = needInit,
    ) { vm ->
        val syncState by vm.syncStateStateOb.collectAsState(initial = null)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = AppBackgroundColor,
                )
                .clickableNoRipple {
                    if (syncState == FirstSyncUseCase.FirstSyncState.SYNC_FAIL) {
                        vm.addIntent(
                            intent = FirstSyncIntent.StartInit(
                                context = context,
                            )
                        )
                    }
                }
                .background(
                    color = AppBackgroundColor,
                )
                .nothing(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {

            val composition by rememberLottieComposition(
                LottieCompositionSpec.RawRes(R.raw.res_sync1)
            )
            LottieAnimation(
                modifier = Modifier
                    .size(240.dp)
                    .nothing(),
                isPlaying = syncState == FirstSyncUseCase.FirstSyncState.SYNCING,
                composition = composition,
                iterations = LottieConstants.IterateForever,
            )

            AppHeightSpace()

            when (syncState) {
                null,
                FirstSyncUseCase.FirstSyncState.SYNCING,
                FirstSyncUseCase.FirstSyncState.SYNC_SUCCESS -> {
                    var pointStr by remember {
                        mutableStateOf(value = "")
                    }
                    LaunchedEffect(key1 = Unit) {
                        while (true) {
                            pointStr = when (pointStr) {
                                "" -> "."
                                "." -> ".."
                                ".." -> "..."
                                "..." -> ""
                                else -> ""
                            }
                            delay(300)
                        }
                    }
                    Text(
                        text = "正在初始化必要的数据$pointStr",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface,
                        ),
                        textAlign = TextAlign.Center,
                    )
                }

                FirstSyncUseCase.FirstSyncState.SYNC_FAIL -> {
                    Text(
                        text = "同步失败\n请检查网络, 点击页面重试",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.error,
                        ),
                        textAlign = TextAlign.Center,
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
fun FirstSyncViewWrap() {
    FirstSyncView()
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun FirstSyncViewPreview() {
    FirstSyncView(
        needInit = false,
    )
}