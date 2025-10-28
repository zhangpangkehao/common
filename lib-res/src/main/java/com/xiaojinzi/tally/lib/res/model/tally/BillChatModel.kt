package com.xiaojinzi.tally.lib.res.model.tally

import androidx.annotation.Keep

@Keep
data class BillChatInsertDto(
    val state: String,
    val content: String? = null,
    val bookId: String? = null,
    val billId: String? = null,
)

@Keep
data class BillChatDto(
    val id: Long,
    val state: String,
    val content: String?,
    val bookId: String?,
    val billId: String?,
    val timeCreated: Long,
) {

    companion object {
        const val STATE_INIT = "init"
        const val STATE_FAIL = "fail"
        const val STATE_SUCCESS = "success"
    }

}
