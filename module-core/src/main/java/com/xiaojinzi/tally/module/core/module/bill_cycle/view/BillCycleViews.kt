package com.xiaojinzi.tally.module.core.module.bill_cycle.view

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.xiaojinzi.component.impl.routeApi
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.compose.util.clickableNoRipple
import com.xiaojinzi.support.ktx.commonTimeFormat2
import com.xiaojinzi.support.ktx.format2f
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.support.ktx.orNull
import com.xiaojinzi.support.ktx.toStringItemDto
import com.xiaojinzi.tally.lib.res.model.tally.TallyBillDto
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_LARGE
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_NORMAL
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_SMALL
import com.xiaojinzi.tally.lib.res.ui.AppBackgroundColor
import com.xiaojinzi.tally.lib.res.ui.AppHeightSpace
import com.xiaojinzi.tally.module.base.support.AppRouterCoreApi
import com.xiaojinzi.tally.module.base.view.compose.AppCommonEmptyDataView
import com.xiaojinzi.tally.module.base.view.compose.AppbarNormalM3
import com.xiaojinzi.tally.module.core.module.bill_cycle.domain.BillCycleIntent
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun BillCycleView(
    needInit: Boolean? = null,
) {
    val context = LocalContext.current
    BusinessContentView<BillCycleViewModel>(
        needInit = needInit,
    ) { vm ->
        val billCycleList by vm.billCycleListStateObVo.collectAsState(initial = emptyList())
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = AppBackgroundColor,
                )
                .nothing(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (billCycleList.isEmpty()) {
                AppCommonEmptyDataView(
                    modifier = Modifier
                        .fillMaxSize()
                        .nothing(),
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .navigationBarsPadding()
                        .padding(bottom = 12.dp)
                        .nothing(),
                ) {
                    items(
                        items = billCycleList,
                    ) { item ->
                        ConstraintLayout(
                            modifier = Modifier
                                .padding(
                                    horizontal = APP_PADDING_NORMAL.dp,
                                    vertical = APP_PADDING_NORMAL.dp
                                )
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .clip(
                                    shape = MaterialTheme.shapes.small,
                                )
                                .combinedClickable(
                                    onLongClick = {
                                        vm.addIntent(
                                            intent = BillCycleIntent.ShowItemMenu(
                                                context = context,
                                                id = item.id,
                                            )
                                        )
                                    },
                                ) {
                                    AppRouterCoreApi::class
                                        .routeApi()
                                        .toBillCycleCrudView(
                                            context = context,
                                            editId = item.id,
                                        )
                                }
                                .background(
                                    color = MaterialTheme.colorScheme.surface,
                                )
                                .nothing(),
                        ) {
                            val (
                                icon, name,
                                amount,
                                loopDesc,
                                loopCount,
                                nextExecTime,
                                runningState,
                            ) = createRefs()
                            Icon(
                                modifier = Modifier
                                    .constrainAs(ref = icon) {
                                        this.start.linkTo(
                                            anchor = parent.start, margin = APP_PADDING_NORMAL.dp,
                                        )
                                        this.top.linkTo(
                                            anchor = parent.top, margin = APP_PADDING_LARGE.dp,
                                        )
                                    }
                                    .size(size = 24.dp)
                                    .nothing(),
                                painter = painterResource(
                                    id = when (item.billType) {
                                        TallyBillDto.Type.NORMAL -> {
                                            item.categoryIcon
                                                ?: com.xiaojinzi.tally.lib.res.R.drawable.res_forbid1
                                        }

                                        TallyBillDto.Type.TRANSFER -> {
                                            com.xiaojinzi.tally.lib.res.R.drawable.res_transfer2
                                        }

                                        else -> com.xiaojinzi.tally.lib.res.R.drawable.res_forbid1
                                    }
                                ),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurface,
                            )
                            Text(
                                modifier = Modifier
                                    .constrainAs(ref = name) {
                                        this.start.linkTo(
                                            anchor = icon.end, margin = APP_PADDING_NORMAL.dp,
                                        )
                                        this.centerVerticallyTo(other = icon)
                                    }
                                    .nothing(),
                                text = when (item.billType) {
                                    TallyBillDto.Type.NORMAL -> {
                                        item.categoryName ?: "无类别"
                                    }

                                    TallyBillDto.Type.TRANSFER -> {
                                        "转账：${item.accountFromName.orNull() ?: "无账户"} → ${item.accountToName.orNull() ?: "无账户"}"
                                    }

                                    else -> ""
                                },
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.Medium,
                                    color = MaterialTheme.colorScheme.onSurface,
                                ),
                                textAlign = TextAlign.Start,
                            )
                            Text(
                                modifier = Modifier
                                    .constrainAs(ref = amount) {
                                        this.end.linkTo(
                                            anchor = parent.end, margin = APP_PADDING_NORMAL.dp,
                                        )
                                        this.centerVerticallyTo(other = icon)
                                    }
                                    .nothing(),
                                text = item.amount.format2f(),
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = MaterialTheme.colorScheme.outline,
                                ),
                                textAlign = TextAlign.Start,
                            )
                            Text(
                                modifier = Modifier
                                    .constrainAs(ref = nextExecTime) {
                                        this.start.linkTo(
                                            anchor = icon.start, margin = 0.dp,
                                        )
                                        this.top.linkTo(
                                            anchor = loopCount.bottom,
                                            margin = APP_PADDING_NORMAL.dp,
                                        )
                                        this.bottom.linkTo(
                                            anchor = parent.bottom, margin = APP_PADDING_NORMAL.dp,
                                        )
                                    }
                                    .nothing(),
                                text = "将于 ${item.nextExecTime.commonTimeFormat2()} 再次执行",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = MaterialTheme.colorScheme.outline,
                                ),
                                textAlign = TextAlign.Start,
                            )
                            Text(
                                modifier = Modifier
                                    .constrainAs(ref = loopDesc) {
                                        this.start.linkTo(
                                            anchor = icon.start, margin = 0.dp,
                                        )
                                        this.top.linkTo(
                                            anchor = icon.bottom, margin = APP_PADDING_SMALL.dp,
                                        )
                                    }
                                    .nothing(),
                                text = item.loopStr,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = MaterialTheme.colorScheme.outline,
                                ),
                                textAlign = TextAlign.Start,
                            )
                            Text(
                                modifier = Modifier
                                    .constrainAs(ref = loopCount) {
                                        this.start.linkTo(
                                            anchor = loopDesc.end, margin = 2.dp,
                                        )
                                        /*this.top.linkTo(
                                            anchor = loopDesc.bottom, margin = 2.dp,
                                        )*/
                                        this.centerVerticallyTo(other = loopDesc)
                                    }
                                    .nothing(),
                                text = if (item.loopCount == -1) {
                                    "| 永不结束"
                                } else {
                                    "| 剩余 ${item.loopCount} 次"
                                },
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = MaterialTheme.colorScheme.outline,
                                ),
                                textAlign = TextAlign.Start,
                            )
                            Column(
                                modifier = Modifier
                                    .constrainAs(ref = runningState) {
                                        this.end.linkTo(
                                            anchor = parent.end, margin = APP_PADDING_NORMAL.dp,
                                        )
                                        this.bottom.linkTo(
                                            anchor = parent.bottom, margin = APP_PADDING_NORMAL.dp,
                                        )
                                    }
                                    .clickableNoRipple {
                                        vm.addIntent(
                                            intent = BillCycleIntent.StateToggle(
                                                id = item.id,
                                            )
                                        )
                                    }
                                    .nothing(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                AppHeightSpace()
                                Icon(
                                    modifier = Modifier
                                        .size(size = 16.dp)
                                        .nothing(),
                                    painter = painterResource(
                                        id = if (item.isRunning) {
                                            com.xiaojinzi.tally.lib.res.R.drawable.res_pause1
                                        } else {
                                            com.xiaojinzi.tally.lib.res.R.drawable.res_play1
                                        },
                                    ),
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.outline,
                                )
                                Spacer(
                                    modifier = Modifier
                                        .height(height = 4.dp)
                                        .nothing()
                                )
                                Text(
                                    text = if (item.isRunning) "正在运行" else "已停止",
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
        }
    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun BillCycleViewWrap() {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            AppbarNormalM3(
                title = "周期记账".toStringItemDto(),
                menu1TextValue = "添加".toStringItemDto(),
                menu1ClickListener = {
                    AppRouterCoreApi::class
                        .routeApi()
                        .toBillCycleCrudView(
                            context = context,
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
            BillCycleView()
        }
    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun BillCycleViewPreview() {
    BillCycleView(
        needInit = false,
    )
}