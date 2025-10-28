package com.xiaojinzi.tally.lib.res.model.support

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.Keep
import com.xiaojinzi.support.bean.StringItemDto
import kotlinx.parcelize.Parcelize

enum class MenuItemLevel {
    Normal,
    Danger,
}

@Keep
@Parcelize
data class MenuItem(
    @DrawableRes
    val svgIcon: Int? = null,
    val content: StringItemDto,
    val level: MenuItemLevel? = null,
    // 做标记用
    val flag: String? = null,
) : Parcelable