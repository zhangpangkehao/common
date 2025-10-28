package com.xiaojinzi.tally.module.core.module.bill_crud.view

import androidx.annotation.Keep
import com.xiaojinzi.support.bean.StringItemDto
import com.xiaojinzi.support.ktx.toStringItemDto
import com.xiaojinzi.tally.lib.res.model.support.LocalImageItemDto
import com.xiaojinzi.tally.lib.res.model.support.toLocalImageItemDto
import com.xiaojinzi.tally.lib.res.model.tally.TallyCategoryDto
import com.xiaojinzi.tally.module.base.support.AppServices

@Keep
data class BillCrudCategoryVo(
    val id: String,
    val type: TallyCategoryDto.Companion.TallyCategoryType,
    val icon: LocalImageItemDto?,
    val name: StringItemDto?,
    val sort: Long,
)

fun TallyCategoryDto.toBillCrudCategoryVo(): BillCrudCategoryVo {
    val iconMappingSpi = AppServices.iconMappingSpi
    return BillCrudCategoryVo(
        id = this.id,
        type = this.type,
        icon = iconMappingSpi[this.iconName]?.toLocalImageItemDto(),
        name = this.name?.toStringItemDto(),
        sort = this.sort,
    )
}