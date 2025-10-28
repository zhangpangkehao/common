package com.xiaojinzi.tally.module.core.module.bill_cycle_crud.view

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.bean.StringItemDto
import com.xiaojinzi.support.compose.util.contentWithComposable
import com.xiaojinzi.support.ktx.format2f
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.support.ktx.orNull
import com.xiaojinzi.support.ktx.toStringItemDto
import com.xiaojinzi.tally.lib.res.model.tally.TallyBillDto
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_NORMAL
import com.xiaojinzi.tally.lib.res.ui.AppBackgroundColor
import com.xiaojinzi.tally.lib.res.ui.AppHeightSpace
import com.xiaojinzi.tally.module.base.view.compose.AppbarNormalM3
import com.xiaojinzi.tally.module.core.module.bill_cycle_crud.domain.BillCycleCrudCycleType
import com.xiaojinzi.tally.module.core.module.bill_cycle_crud.domain.BillCycleCrudCycleWeekType
import com.xiaojinzi.tally.module.core.module.bill_cycle_crud.domain.BillCycleCrudEndType
import com.xiaojinzi.tally.module.core.module.bill_cycle_crud.domain.BillCycleCrudIntent
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun BillCycleCrudActionView1(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = APP_PADDING_NORMAL.dp)
        .nothing(),
    title: StringItemDto,
    subTitle: StringItemDto? = null,
    valueIconRsd: Int? = null,
    value: StringItemDto? = null,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .clickable {
                onClick.invoke()
            }
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column {
            Text(
                text = title.contentWithComposable(),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight(550),
                    color = MaterialTheme.colorScheme.onSurface,
                ),
                textAlign = TextAlign.Start,
            )
            subTitle?.let {
                Spacer(
                    modifier = Modifier
                        .height(height = 3.dp)
                        .nothing()
                )
                Text(
                    text = subTitle.contentWithComposable(),
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.outline,
                    ),
                    textAlign = TextAlign.Start,
                )
            }
        }
        Spacer(modifier = Modifier.weight(weight = 1f, fill = true))
        valueIconRsd?.let {
            Icon(
                modifier = Modifier
                    .size(size = 20.dp)
                    .nothing(),
                painter = painterResource(id = valueIconRsd),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface.copy(
                    alpha = 0.8f,
                ),
            )
        }
        value?.let {
            Text(
                text = value.contentWithComposable(),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 0.8f,
                    ),
                ),
                textAlign = TextAlign.Start,
            )
        }
    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun BillCycleCrudView(
    needInit: Boolean? = null,
) {
    val context = LocalContext.current
    BusinessContentView<BillCycleCrudViewModel>(
        needInit = needInit,
    ) { vm ->
        val cycleType by vm.cycleTypeStateOb.collectAsState(initial = BillCycleCrudCycleType.DAILY)
        val cycleWeekType by vm.cycleWeekTypeStateOb.collectAsState(initial = BillCycleCrudCycleWeekType.Monday)
        val cycleMonthValue by vm.cycleMonthValueStateOb.collectAsState(initial = 1)
        val cycleEndType by vm.cycleEndTypeStateOb.collectAsState(initial = BillCycleCrudEndType.NEVER)
        val cycleCount by vm.cycleCountStateOb.collectAsState(initial = 0)
        val hour by vm.hourStateOb.collectAsState(initial = null)
        val billAmount by vm.billAmountStateOb.collectAsState(initial = 0f)
        val billType by vm.billTypeStateOb.collectAsState(initial = TallyBillDto.Type.NORMAL)
        val bookInfo by vm.bookInfoStateOb.collectAsState(initial = null)
        val categoryInfo by vm.categoryInfoStateOb.collectAsState(initial = null)
        val accountInfo by vm.accountInfoStateOb.collectAsState(initial = null)
        val transferFromAccountInfo by vm.transferFromAccountInfoStateOb.collectAsState(initial = null)
        val transferToAccountInfo by vm.transferToAccountInfoStateOb.collectAsState(initial = null)
        val note by vm.noteStateOb.collectAsState(initial = "")
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

            Column(
                modifier = Modifier
                    .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(
                        shape = MaterialTheme.shapes.small,
                    )
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                    )
                    .nothing(),
            ) {
                BillCycleCrudActionView1(
                    title = "重复周期".toStringItemDto(),
                    value = when (cycleType) {
                        BillCycleCrudCycleType.DAILY -> "每天"
                        BillCycleCrudCycleType.WEEKLY -> "每周"
                        BillCycleCrudCycleType.MONTHLY -> "每月"
                    }.toStringItemDto(),
                ) {
                    vm.addIntent(
                        intent = BillCycleCrudIntent.CycleTypeSelect(
                            context = context,
                        )
                    )
                }
                AnimatedVisibility(visible = cycleType == BillCycleCrudCycleType.WEEKLY) {
                    BillCycleCrudActionView1(
                        title = "周几".toStringItemDto(),
                        value = cycleWeekType.str.toStringItemDto(),
                    ) {
                        vm.addIntent(
                            intent = BillCycleCrudIntent.CycleWeekTypeSelect(
                                context = context,
                            )
                        )
                    }
                }
                AnimatedVisibility(visible = cycleType == BillCycleCrudCycleType.MONTHLY) {
                    BillCycleCrudActionView1(
                        title = "几号".toStringItemDto(),
                        value = "${cycleMonthValue}号".toStringItemDto(),
                    ) {
                        vm.addIntent(
                            intent = BillCycleCrudIntent.CycleMonthTypeSelect(
                                context = context,
                            )
                        )
                    }
                }
                BillCycleCrudActionView1(
                    title = "时间".toStringItemDto(),
                    value = (hour?.let {
                        "${it}:00"
                    } ?: "请选择").toStringItemDto()
                ) {
                    vm.addIntent(
                        intent = BillCycleCrudIntent.HourSelect(
                            context = context,
                        )
                    )
                }
                BillCycleCrudActionView1(
                    title = "结束周期".toStringItemDto(),
                    value = when (cycleEndType) {
                        BillCycleCrudEndType.NEVER -> "永不结束"
                        BillCycleCrudEndType.COUNT -> "按次数结束重复"
                    }.toStringItemDto(),
                ) {
                    vm.addIntent(
                        intent = BillCycleCrudIntent.CycleEndTypeSelect(
                            context = context,
                        )
                    )
                }
                AnimatedVisibility(visible = cycleEndType == BillCycleCrudEndType.COUNT) {
                    BillCycleCrudActionView1(
                        title = "循环次数".toStringItemDto(),
                        value = "${cycleCount}次".toStringItemDto(),
                    ) {
                        vm.addIntent(
                            intent = BillCycleCrudIntent.CycleCountSelect(
                                context = context,
                            )
                        )
                    }
                }
            }

            AppHeightSpace()

            Column(
                modifier = Modifier
                    .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(
                        shape = MaterialTheme.shapes.small,
                    )
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                    )
                    .nothing(),
            ) {
                BillCycleCrudActionView1(
                    title = "金额".toStringItemDto(),
                    subTitle = "此处金额 < 0 为支出 > 0 为收入".toStringItemDto(),
                    value = billAmount.format2f().toStringItemDto(),
                ) {
                    vm.addIntent(
                        intent = BillCycleCrudIntent.BillAmountSelect(
                            context = context,
                        )
                    )
                }
                BillCycleCrudActionView1(
                    title = "账本".toStringItemDto(),
                    value = bookInfo?.name.orEmpty().toStringItemDto(),
                ) {
                    vm.addIntent(
                        intent = BillCycleCrudIntent.BillBookSelect(
                            context = context,
                        )
                    )
                }
                BillCycleCrudActionView1(
                    title = "账单类型".toStringItemDto(),
                    value = when (billType) {
                        TallyBillDto.Type.NORMAL -> "普通"
                        TallyBillDto.Type.TRANSFER -> "转账"
                        TallyBillDto.Type.REFUND, TallyBillDto.Type.Unknown -> "未知"
                    }.toStringItemDto(),
                ) {
                    vm.addIntent(
                        intent = BillCycleCrudIntent.BillTypeSelect(
                            context = context,
                        )
                    )
                }
                AnimatedVisibility(visible = billType == TallyBillDto.Type.NORMAL) {
                    BillCycleCrudActionView1(
                        title = "类别".toStringItemDto(),
                        value = (categoryInfo?.name.orNull() ?: "无类别").toStringItemDto(),
                    ) {
                        vm.addIntent(
                            intent = BillCycleCrudIntent.BillCategorySelect(
                                context = context,
                            )
                        )
                    }
                }
                AnimatedVisibility(visible = billType == TallyBillDto.Type.NORMAL) {
                    BillCycleCrudActionView1(
                        title = "账户".toStringItemDto(),
                        value = (accountInfo?.name.orNull() ?: "无账户").toStringItemDto(),
                    ) {
                        vm.addIntent(
                            intent = BillCycleCrudIntent.BillAccountSelect(
                                context = context,
                            )
                        )
                    }
                }
                AnimatedVisibility(visible = billType == TallyBillDto.Type.TRANSFER) {
                    BillCycleCrudActionView1(
                        title = "转出账户".toStringItemDto(),
                        value = (transferFromAccountInfo?.name.orNull()
                            ?: "无账户").toStringItemDto(),
                    ) {
                        vm.addIntent(
                            intent = BillCycleCrudIntent.BillTransferFromAccountSelect(
                                context = context,
                            )
                        )
                    }
                }
                AnimatedVisibility(visible = billType == TallyBillDto.Type.TRANSFER) {
                    BillCycleCrudActionView1(
                        title = "转入账户".toStringItemDto(),
                        value = (transferToAccountInfo?.name.orNull()
                            ?: "无账户").toStringItemDto(),
                    ) {
                        vm.addIntent(
                            intent = BillCycleCrudIntent.BillTransferToAccountSelect(
                                context = context,
                            )
                        )
                    }
                }
                BillCycleCrudActionView1(
                    title = "备注".toStringItemDto(),
                    value = (note.orNull() ?: "无备注").toStringItemDto(),
                ) {
                    vm.addIntent(
                        intent = BillCycleCrudIntent.BillNoteSelect(
                            context = context,
                        )
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
fun BillCycleCrudViewWrap() {
    val vm: BillCycleCrudViewModel = viewModel()
    val isEdit by vm.isEditStateOb.collectAsState(initial = false)
    Scaffold(
        topBar = {
            AppbarNormalM3(
                title = "添加周期任务".toStringItemDto(),
                menu2IconRsd = if (isEdit) {
                    com.xiaojinzi.tally.lib.res.R.drawable.res_delete2
                } else {
                    null
                },
                menu1IconRsd = com.xiaojinzi.tally.lib.res.R.drawable.res_ok1,
                menu2ClickListener = {
                    if (isEdit) {
                        vm.addIntent(
                            intent = BillCycleCrudIntent.Delete,
                        )
                    }
                },
                menu1ClickListener = {
                    vm.addIntent(
                        intent = BillCycleCrudIntent.Submit,
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
            BillCycleCrudView()
        }
    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun BillCycleCrudViewPreview() {
    BillCycleCrudView(
        needInit = false,
    )
}