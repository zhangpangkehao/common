package com.xiaojinzi.tally.module.core.module.account_select.view

import androidx.annotation.DrawableRes
import androidx.annotation.Keep
import com.xiaojinzi.tally.lib.res.model.tally.MoneyYuan
import java.util.concurrent.Flow

@Keep
data class AccountSelectVo(
    val id: String,
    @DrawableRes
    val iconRsd: Int?,
    val name: String,
    val balanceCurrent: MoneyYuan,
)