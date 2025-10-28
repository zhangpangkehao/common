package com.xiaojinzi.tally.module.core.module.book_member.view

import androidx.annotation.Keep
import com.xiaojinzi.tally.lib.res.model.user.UserInfoDto

@Keep
data class BookMemberItemVo(
    val isOwner: Boolean,
    val userInfo: UserInfoDto,
    val canDelete: Boolean,
)