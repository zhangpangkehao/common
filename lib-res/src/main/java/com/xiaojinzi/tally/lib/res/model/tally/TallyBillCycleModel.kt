package com.xiaojinzi.tally.lib.res.model.tally

import androidx.annotation.Keep
import com.xiaojinzi.support.annotation.ModelDto
import com.xiaojinzi.support.annotation.ModelForNetwork

@Keep
@ModelForNetwork
data class BillCycleRes(
    val id: Long,
    val userId: String,
    val bookId: String,
    val state: String,
    val cycleType: String,
    val loopCount: Int,
    val timeZone: Int,
    val dayOfMonth: Int,
    val dayOfWeek: Int,
    val hour: Int,
    val billType: String,
    val categoryId: String,
    val accountId: String,
    val transferTargetAccountId: String,
    val amount: MoneyFen,
    val note: String,
    val nextExecTime: Long,
)

@Keep
@ModelDto
data class BillCycleResDto(
    val id: Long,
    val userId: String,
    val bookId: String,
    val state: String,
    val cycleType: String,
    val loopCount: Int,
    val timeZone: Int,
    val dayOfMonth: Int,
    val dayOfWeek: Int,
    val hour: Int,
    val billType: String,
    val categoryId: String,
    val accountId: String,
    val transferTargetAccountId: String,
    val amount: MoneyFen,
    val note: String,
    val nextExecTime: Long,
) {
    companion object {
        const val STATE_RUNNING = "running"
        const val STATE_STOPPED = "stopped"
        const val CYCLE_TYPE_DAY = "day"
        const val CYCLE_TYPE_WEEK = "week"
        const val CYCLE_TYPE_MONTH = "month"
    }
}

fun BillCycleRes.toDto() = BillCycleResDto(
    id = id,
    userId = userId,
    bookId = bookId,
    state = state,
    cycleType = cycleType,
    loopCount = loopCount,
    timeZone = timeZone,
    dayOfMonth = dayOfMonth,
    dayOfWeek = dayOfWeek,
    hour = hour,
    billType = billType,
    categoryId = categoryId,
    accountId = accountId,
    transferTargetAccountId = transferTargetAccountId,
    amount = amount,
    note = note,
    nextExecTime = nextExecTime,
)