package com.xiaojinzi.tally.module.core.module.bill_cycle.view

import androidx.annotation.DrawableRes
import androidx.annotation.Keep
import com.xiaojinzi.tally.lib.res.model.tally.TallyBillDto

@Keep
data class BillCycleVoItem(
    val id: Long,
    val billType: TallyBillDto.Type,
    @DrawableRes
    val categoryIcon: Int?,
    val categoryName: String?,
    val accountFromName: String?,
    val accountToName: String?,
    val amount: Float,
    val loopStr: String,
    val loopCount: Int,
    val isRunning: Boolean,
    val nextExecTime: Long,
)