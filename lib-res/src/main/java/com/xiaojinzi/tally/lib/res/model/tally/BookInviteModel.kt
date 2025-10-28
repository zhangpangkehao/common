package com.xiaojinzi.tally.lib.res.model.tally

import androidx.annotation.Keep
import com.xiaojinzi.support.annotation.ModelForNetwork

@Keep
@ModelForNetwork
class BookInviteShareRes(
    val code: String,
    val bookId: String,
    val expiredTime: Long,
)

@Keep
class BookInviteShareResDto(
    val code: String,
    val bookId: String,
    val expiredTime: Long,
)

fun BookInviteShareRes.toDto() = BookInviteShareResDto(
    code = code,
    bookId = bookId,
    expiredTime = expiredTime,
)