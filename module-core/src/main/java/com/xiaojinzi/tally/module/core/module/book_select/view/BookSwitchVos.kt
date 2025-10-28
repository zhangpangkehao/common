package com.xiaojinzi.tally.module.core.module.book_select.view

import androidx.annotation.Keep
import com.xiaojinzi.support.bean.StringItemDto
import com.xiaojinzi.tally.lib.res.model.support.LocalImageItemDto

@Keep
data class BookSelectItemVo(
    val bookId: String,
    val bookIcon: LocalImageItemDto?,
    val bookName: StringItemDto?,
    // null 表示是自己的, 否则是别人的
    val userName: StringItemDto?,
)