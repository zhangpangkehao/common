package com.xiaojinzi.tally.module.core.module.account_icon_select.view

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.compose.GridView
import com.xiaojinzi.support.compose.util.circleClip
import com.xiaojinzi.support.compose.util.contentWithComposable
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.support.ktx.toStringItemDto
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_LARGE
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_NORMAL
import com.xiaojinzi.tally.module.base.view.compose.AppbarNormalM3
import com.xiaojinzi.tally.module.core.module.account_icon_select.domain.AccountIconSelectGroup
import com.xiaojinzi.tally.module.core.module.account_icon_select.domain.AccountIconSelectIntent
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun AccountIconSelectGroupView(
    vm: AccountIconSelectViewModel,
    itemGroup: AccountIconSelectGroup,
    isLastItem: Boolean,
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .run {
                if (isLastItem) {
                    this
                        .navigationBarsPadding()
                        .padding(bottom = APP_PADDING_NORMAL.dp)
                } else {
                    this
                }
            }
            .nothing(),
        horizontalAlignment = Alignment.Start,
    ) {
        Row(
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = APP_PADDING_NORMAL.dp)
                .nothing(),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Box(
                modifier = Modifier
                    .size(
                        width = 4.dp,
                        height = 14.dp,
                    )
                    .circleClip()
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                    )
                    .nothing(),
            )

            Spacer(
                modifier = Modifier
                    .width(width = APP_PADDING_NORMAL.dp)
                    .nothing()
            )

            Text(
                text = itemGroup.title.contentWithComposable(),
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Start,
            )

        }

        GridView(
            modifier = Modifier
                .padding(horizontal = APP_PADDING_LARGE.dp, vertical = 0.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .nothing(),
            columnNumber = 5,
            items = itemGroup.items,
            verticalSpace = APP_PADDING_NORMAL.dp,
        ) { item ->
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .nothing(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    modifier = Modifier
                        .circleClip()
                        .clickable {
                            vm.addIntent(
                                intent = AccountIconSelectIntent.ItemClick(
                                    context = context,
                                    item = item,
                                )
                            )
                        }
                        .background(
                            color = MaterialTheme.colorScheme.surfaceColorAtElevation(elevation = 1.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                        .size(size = 24.dp)
                        .nothing(),
                    painter = painterResource(id = item.iconInfo.rsd),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(
                    modifier = Modifier
                        .height(height = 8.dp)
                        .nothing()
                )
                Text(
                    text = item.name.contentWithComposable(),
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                    ),
                    textAlign = TextAlign.Start,
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
private fun AccountIconSelectView(
    needInit: Boolean? = false,
    previewDefault: AccountIconSelectPreviewDefault? = null,
) {
    val context = LocalContext.current
    BusinessContentView<AccountIconSelectViewModel>(
        needInit = needInit,
    ) { vm ->
        val dataList by vm.dataListStateOb.collectAsState(initial = emptyList())
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .nothing(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            itemsIndexed(
                items = dataList,
            ) { index, item ->
                AccountIconSelectGroupView(
                    vm = vm,
                    itemGroup = item,
                    isLastItem = index == dataList.lastIndex,
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
fun AccountIconSelectViewWrap() {
    Scaffold(
        topBar = {
            AppbarNormalM3(
                title = "选择账户".toStringItemDto(),
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(top = it.calculateTopPadding())
                .nothing(),
        ) {
            AccountIconSelectView()
        }
    }
}

private data class AccountIconSelectPreviewDefault(
    val str: String,
)

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
private fun AccountIconSelectViewPreview() {
    AccountIconSelectView(
        needInit = false,
        previewDefault = AccountIconSelectPreviewDefault(
            str = "测试数据",
        )
    )
}