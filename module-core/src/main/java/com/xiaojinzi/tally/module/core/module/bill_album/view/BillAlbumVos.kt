package com.xiaojinzi.tally.module.core.module.bill_album.view

import androidx.annotation.Keep
import com.xiaojinzi.support.bean.StringItemDto
import com.xiaojinzi.tally.lib.res.model.support.LocalImageItemDto
import com.xiaojinzi.tally.lib.res.model.tally.MoneyYuan
import com.xiaojinzi.tally.lib.res.model.tally.TallyBillDto

@Keep
sealed class BillAlbumItemVo

@Keep
data class BillAlbumItemHeaderVo(
    val billId: String,
    val billType: TallyBillDto.Type,
    val billTime: Long,
    val categoryIcon: LocalImageItemDto?,
    val categoryName: StringItemDto?,
    val billAmount: MoneyYuan,
): BillAlbumItemVo()

@Keep
data class BillAlbumItemNormalVo(
    val billImageId: String,
    val url: String?,
): BillAlbumItemVo()