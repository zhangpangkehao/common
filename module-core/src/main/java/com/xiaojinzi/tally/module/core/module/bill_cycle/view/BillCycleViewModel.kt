package com.xiaojinzi.tally.module.core.module.bill_cycle.view

import androidx.lifecycle.viewModelScope
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.StateHotObservable
import com.xiaojinzi.support.annotation.ViewLayer
import com.xiaojinzi.support.ktx.orNull
import com.xiaojinzi.support.ktx.sharedStateIn
import com.xiaojinzi.tally.lib.res.model.tally.BillCycleResDto
import com.xiaojinzi.tally.lib.res.model.tally.TallyBillDto
import com.xiaojinzi.tally.module.base.support.AppServices
import com.xiaojinzi.tally.module.core.module.bill_cycle.domain.BillCycleUseCase
import com.xiaojinzi.tally.module.core.module.bill_cycle.domain.BillCycleUseCaseImpl
import kotlinx.coroutines.flow.map

@ViewLayer
class BillCycleViewModel(
    private val useCase: BillCycleUseCase = BillCycleUseCaseImpl(),
) : BaseViewModel(),
    BillCycleUseCase by useCase {

    @StateHotObservable
    val billCycleListStateObVo = useCase.cycleListStateOb
        .map { list ->
            list.map { item ->
                val categoryInfo = item.categoryId.orNull()?.let {
                    AppServices.tallyDataSourceSpi.getCategoryById(
                        id = it,
                    )?.getAdapter
                }
                val accountFrom = item.accountId.orNull()?.let {
                    AppServices.tallyDataSourceSpi.getAccountById(
                        id = it,
                    )?.getAdapter
                }
                val accountTo = item.transferTargetAccountId.orNull()?.let {
                    AppServices.tallyDataSourceSpi.getAccountById(
                        id = it,
                    )?.getAdapter
                }
                BillCycleVoItem(
                    id = item.id,
                    billType = TallyBillDto.Type.from(
                        value = item.billType,
                    ),
                    categoryIcon = AppServices.iconMappingSpi[categoryInfo?.iconName],
                    categoryName = categoryInfo?.name.orNull(),
                    accountFromName = accountFrom?.name,
                    accountToName = accountTo?.name,
                    amount = item.amount.toYuan().value,
                    loopStr = when (item.cycleType) {
                        BillCycleResDto.CYCLE_TYPE_DAY -> "每天 ${item.hour} 点执行"
                        BillCycleResDto.CYCLE_TYPE_WEEK -> {
                            "每${
                                when (item.dayOfWeek) {
                                    1 -> "周日"
                                    2 -> "周一"
                                    3 -> "周二"
                                    4 -> "周三"
                                    5 -> "周四"
                                    6 -> "周五"
                                    7 -> "周六"
                                    else -> "---"
                                }
                            } ${item.hour} 点执行"
                        }

                        BillCycleResDto.CYCLE_TYPE_MONTH -> {
                            "每月 ${item.dayOfMonth} 号 ${item.hour} 点执行"
                        }

                        else -> ""
                    },
                    loopCount = item.loopCount,
                    isRunning = item.state == BillCycleResDto.STATE_RUNNING,
                    nextExecTime = item.nextExecTime,
                )
            }
        }
        .sharedStateIn(
            scope = viewModelScope,
        )

}