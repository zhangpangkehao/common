package com.xiaojinzi.tally.module.core.module.sync_log.view

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.support.ktx.toStringItemDto
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_LARGE
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_NORMAL
import com.xiaojinzi.tally.lib.res.ui.AppBackgroundColor
import com.xiaojinzi.tally.lib.res.ui.AppHeightSpace
import com.xiaojinzi.tally.module.base.support.AppServices
import com.xiaojinzi.tally.module.base.view.compose.AppbarNormalM3
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun SyncLogItemView(
    title: String,
    unSyncCount: Long,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                color = MaterialTheme.colorScheme.surface,
            )
            .padding(horizontal = APP_PADDING_LARGE.dp, vertical = APP_PADDING_NORMAL.dp)
            .nothing(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall.copy(
                color = MaterialTheme.colorScheme.onSurface,
            ),
            textAlign = TextAlign.Start,
        )
        Spacer(modifier = Modifier.weight(weight = 1f, fill = true))
        Text(
            text = "未同步数量：$unSyncCount",
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.outline,
            ),
            textAlign = TextAlign.Start,
        )
    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun SyncLogView(
    needInit: Boolean? = false,
) {
    val unSyncCategoryCount by AppServices
        .tallyDataSourceSpi
        .unSyncCategoryCountStateOb
        .collectAsState(initial = 0)
    val unSyncAccountCount by AppServices
        .tallyDataSourceSpi
        .unSyncAccountCountStateOb
        .collectAsState(initial = 0)
    val unSyncLabelCount by AppServices
        .tallyDataSourceSpi
        .unSyncLabelCountStateOb
        .collectAsState(initial = 0)
    val unSyncBillLabelCount by AppServices
        .tallyDataSourceSpi
        .unSyncBillLabelCountStateOb
        .collectAsState(initial = 0)
    val unSyncBillImageCount by AppServices
        .tallyDataSourceSpi
        .unSyncBillImageCountStateOb
        .collectAsState(initial = 0)
    val unSyncBillCount by AppServices
        .tallyDataSourceSpi
        .unSyncBillCountStateOb
        .collectAsState(initial = 0)
    val context = LocalContext.current
    BusinessContentView<SyncLogViewModel>(
        needInit = needInit,
    ) { vm ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = AppBackgroundColor,
                )
                .nothing(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            AppHeightSpace()
            SyncLogItemView(
                title = "类别表",
                unSyncCount = unSyncCategoryCount,
            )
            SyncLogItemView(
                title = "账户表",
                unSyncCount = unSyncAccountCount,
            )
            SyncLogItemView(
                title = "标签表",
                unSyncCount = unSyncLabelCount,
            )
            SyncLogItemView(
                title = "账单标签中间表",
                unSyncCount = unSyncBillLabelCount,
            )
            SyncLogItemView(
                title = "账单图标表",
                unSyncCount = unSyncBillImageCount,
            )
            SyncLogItemView(
                title = "账单表",
                unSyncCount = unSyncBillCount,
            )

        }
    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun SyncLogViewWrap() {
    Scaffold(
        topBar = {
            AppbarNormalM3(
                title = "表同步情况".toStringItemDto(),
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(top = it.calculateTopPadding())
                .nothing(),
        ) {
            SyncLogView()
        }
    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun SyncLogViewPreview() {
    SyncLogView(
        needInit = false,
    )
}