package com.xiaojinzi.tally.lib.res.model.tally

import androidx.annotation.Keep
import com.xiaojinzi.support.annotation.ModelForNetwork

@Keep
@ModelForNetwork
data class TallyRemoteBillImageRes(
    val id: String,
    val userId: String,
    val bookId: String,
    val billId: String,
    val url: String?,
    val timeCreate: Long,
    val timeModify: Long,
    val isDeleted: Boolean,
)

@Keep
@ModelForNetwork
data class TallyRemoteBillImageReq(
    val id: String,
    val userId: String,
    val bookId: String,
    val billId: String,
    val url: String?,
    val isDeleted: Boolean,
)

@Keep
data class TallyBillImageInsertDto(
    // 从远程同步的时候, 这个字段需要赋值
    val id: String? = null,
    val userId: String,
    val bookId: String,
    val billId: String,
    val url: String?,
    val timeModify: Long? = null,
    val isDeleted: Boolean = false,
    val isSync: Boolean = false,
)

@Keep
data class TallyBillImageDto(
    val id: String,
    val userId: String,
    val bookId: String,
    val billId: String,
    val url: String?,
    val timeModify: Long?,
    val isDeleted: Boolean,
    val isSync: Boolean,
) {

    val getAdapter: TallyBillImageDto?
        get() = if (isDeleted) {
            null
        } else {
            this
        }

}