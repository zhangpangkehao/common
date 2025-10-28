package com.xiaojinzi.tally.module.core.module.account_select.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xiaojinzi.component.impl.routeApi
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.compose.BottomView
import com.xiaojinzi.support.ktx.format2f
import com.xiaojinzi.support.ktx.getActivity
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.support.ktx.tryFinishActivity
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_LARGE
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_NORMAL
import com.xiaojinzi.tally.lib.res.ui.AppWidthSpace
import com.xiaojinzi.tally.lib.res.ui.topShape
import com.xiaojinzi.tally.module.base.support.AppRouterCoreApi
import com.xiaojinzi.tally.module.base.view.compose.AppCommonEmptyDataView
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun AccountSelectView(
    needInit: Boolean? = false,
) {
    val context = LocalContext.current
    BusinessContentView<AccountSelectViewModel>(
        needInit = needInit,
    ) { vm ->
        val accountList by vm.allAccountStateVo.collectAsState(initial = emptyList())
        BottomView(maxFraction = 0.6f) {
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
                if (accountList.isEmpty()) {
                    AppCommonEmptyDataView(
                        modifier = Modifier
                            .padding(horizontal = 0.dp, vertical = 20.dp)
                            .nothing(),
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .animateContentSize()
                            .fillMaxWidth()
                            .weight(weight = 1f, fill = false)
                            .nothing(),
                    ) {
                        itemsIndexed(
                            items = accountList,
                        ) { index, item ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .clickable {
                                        context
                                            .getActivity()
                                            ?.run {
                                                this.setResult(
                                                    Activity.RESULT_OK,
                                                    Intent().apply {
                                                        this.putExtra("data", item.id)
                                                    }
                                                )
                                            }
                                        context.tryFinishActivity()
                                    }
                                    .padding(
                                        horizontal = APP_PADDING_NORMAL.dp,
                                        vertical = APP_PADDING_LARGE.dp
                                    )
                                    .nothing(),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .size(size = 24.dp)
                                        .nothing(),
                                    painter = painterResource(
                                        id = item.iconRsd
                                            ?: com.xiaojinzi.tally.lib.res.R.drawable.res_money2,
                                    ),
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onSurface,
                                )
                                AppWidthSpace()
                                Text(
                                    text = item.name,
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        color = MaterialTheme.colorScheme.onSurface,
                                    ),
                                    textAlign = TextAlign.Start,
                                )
                                Spacer(modifier = Modifier.weight(weight = 1f, fill = true))
                                Text(
                                    text = item.balanceCurrent.value.format2f(),
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        color = MaterialTheme.colorScheme.onSurface,
                                    ),
                                    textAlign = TextAlign.Start,
                                )
                            }
                        }
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .clickable {
                                        context
                                            .getActivity()
                                            ?.run {
                                                this.setResult(
                                                    Activity.RESULT_OK,
                                                    Intent(),
                                                )
                                            }
                                        context.tryFinishActivity()
                                    }
                                    .padding(
                                        horizontal = APP_PADDING_NORMAL.dp,
                                        vertical = APP_PADDING_LARGE.dp
                                    )
                                    .nothing(),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .size(size = 24.dp)
                                        .nothing(),
                                    painter = painterResource(
                                        id = com.xiaojinzi.tally.lib.res.R.drawable.res_bank_card_no1,
                                    ),
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onSurface,
                                )
                                AppWidthSpace()
                                Text(
                                    text = "无账户",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        color = MaterialTheme.colorScheme.onSurface,
                                    ),
                                    textAlign = TextAlign.Start,
                                )
                            }
                        }
                    }
                }
                Button(
                    modifier = Modifier
                        .padding(bottom = APP_PADDING_NORMAL.dp)
                        .navigationBarsPadding()
                        .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .nothing(),
                    onClick = {
                        AppRouterCoreApi::class
                            .routeApi()
                            .toAccountCrudView(
                                context = context,
                            )
                    },
                ) {
                    Text(
                        text = "新增账户",
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
fun AccountSelectViewWrap() {
    AccountSelectView()
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun AccountSelectViewPreview() {
    AccountSelectView(
        needInit = false,
    )
}