package com.xiaojinzi.tally.module.core.module.ai_bill_chat.view

import androidx.annotation.DrawableRes
import androidx.annotation.Keep
import com.xiaojinzi.tally.lib.res.model.tally.BillChatDto
import com.xiaojinzi.tally.lib.res.model.tally.MoneyYuan

@Keep
data class AiBillChatItemVo(
    val core: BillChatDto,
    val billId: String?,
    val time: Long?,
    @DrawableRes
    val cateIconRsd: Int?,
    val cateName: String?,
    val note: String?,
    val amount: MoneyYuan?,
)