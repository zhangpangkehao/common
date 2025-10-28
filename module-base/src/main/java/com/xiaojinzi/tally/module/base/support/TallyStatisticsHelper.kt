package com.xiaojinzi.tally.module.base.support

import com.xiaojinzi.support.annotation.StateHotObservable
import com.xiaojinzi.support.ktx.AppScope
import com.xiaojinzi.support.ktx.getMonthInterval
import com.xiaojinzi.support.ktx.sharedStateIn
import com.xiaojinzi.tally.lib.res.model.tally.MoneyFen
import com.xiaojinzi.tally.lib.res.model.tally.TallyBillDto
import com.xiaojinzi.tally.module.base.spi.TallyDataSourceSpi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

object TallyStatisticsHelper {

    /**
     * 当前月份的收入
     * 值是正数
     */
    @StateHotObservable
    val currentMonthIncomeStateOb = subscribeMonthIncome()
        .sharedStateIn(
            scope = AppScope,
        )

    /**
     * 当前月份的支出
     * 值是负数
     */
    @StateHotObservable
    val currentMonthSpendingStateOb = subscribeMonthSpending()
        .sharedStateIn(
            scope = AppScope,
        )

    @StateHotObservable
    val currentMonthBalanceStateOb = combine(
        currentMonthIncomeStateOb,
        currentMonthSpendingStateOb,
    ) { income, spending ->
        income + spending
    }

    @StateHotObservable
    fun subscribeTimeRangeIncome(
        startTime: Long,
        endTime: Long,
        bookIdList: List<String> = emptyList(),
    ): Flow<MoneyFen> {
        return AppServices
            .tallyDataSourceSpi
            .subscribeBillAmountByCondition(
                queryCondition = TallyDataSourceSpi.Companion.BillQueryConditionDto(
                    typeList = listOf(
                        TallyBillDto.Type.NORMAL,
                        TallyBillDto.Type.REFUND,
                    ),
                    bookIdList = bookIdList,
                    startTimeInclude = startTime,
                    endTimeInclude = endTime - 1,
                    amountMoreThanZero = true,
                    isNotCalculate = false,
                )
            )
    }

    /**
     * 订阅月份的收入
     * 值是正数
     */
    @StateHotObservable
    fun subscribeMonthIncome(
        offset: Int = 0,
    ): Flow<MoneyFen> {
        val (startTime, endTime) = getMonthInterval(
            timeStamp = System.currentTimeMillis(),
            monthOffset = offset,
        )
        return subscribeTimeRangeIncome(
            startTime = startTime,
            endTime = endTime,
        )
    }

    @StateHotObservable
    fun subscribeTimeRangeSpending(
        startTime: Long,
        endTime: Long,
        bookIdList: List<String> = emptyList(),
    ): Flow<MoneyFen> {
        return AppServices
            .tallyDataSourceSpi
            .subscribeBillAmountByCondition(
                queryCondition = TallyDataSourceSpi.Companion.BillQueryConditionDto(
                    typeList = listOf(
                        TallyBillDto.Type.NORMAL,
                        TallyBillDto.Type.REFUND,
                    ),
                    bookIdList = bookIdList,
                    startTimeInclude = startTime,
                    endTimeInclude = endTime - 1,
                    amountLessThanZero = true,
                    isNotCalculate = false,
                )
            )
    }

    /**
     * 订阅月份的支出
     * 值是负数
     */
    @StateHotObservable
    fun subscribeMonthSpending(
        offset: Int = 0,
    ): Flow<MoneyFen> {
        val (startTime, endTime) = getMonthInterval(
            timeStamp = System.currentTimeMillis(),
            monthOffset = offset,
        )
        return subscribeTimeRangeSpending(
            startTime = startTime,
            endTime = endTime,
        )
    }

}