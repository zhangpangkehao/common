package com.xiaojinzi.tally.module.main.module.app_share.view

import androidx.annotation.DrawableRes
import androidx.annotation.Keep
import com.xiaojinzi.support.bean.StringItemDto
import com.xiaojinzi.support.ktx.toStringItemDto

@Keep
data class AppShareItemVo(
    val name: StringItemDto,
    @DrawableRes
    val iconRsd: Int,
)

val AppShareList = listOf(
    AppShareItemVo(
        name = "微信".toStringItemDto(),
        iconRsd = com.xiaojinzi.tally.lib.res.R.drawable.res_wechat1,
    ),
    AppShareItemVo(
        name = "朋友圈".toStringItemDto(),
        iconRsd = com.xiaojinzi.tally.lib.res.R.drawable.res_friends_circle1,
    ),
    AppShareItemVo(
        name = "复制".toStringItemDto(),
        iconRsd = com.xiaojinzi.tally.lib.res.R.drawable.res_copy1,
    ),
)