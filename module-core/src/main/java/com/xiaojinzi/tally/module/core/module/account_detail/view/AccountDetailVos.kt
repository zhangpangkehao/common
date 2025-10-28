package com.xiaojinzi.tally.module.core.module.account_detail.view

import androidx.annotation.Keep
import com.xiaojinzi.support.bean.StringItemDto
import com.xiaojinzi.tally.lib.res.model.support.LocalImageItemDto
import com.xiaojinzi.tally.lib.res.model.tally.MoneyYuan

@Keep
data class AccountDetailVo(
    val accountId: String?,
    val icon: LocalImageItemDto?,
    val name: StringItemDto?,
    val balanceCurrent: MoneyYuan?,
)