package com.xiaojinzi.tally.lib.res.model.tally

import androidx.annotation.Keep
import com.xiaojinzi.support.annotation.ModelForNetwork

@Keep
@ModelForNetwork
data class TallyRemoteLabelRes(
    val id: String,
    val userId: String,
    val bookId: String,
    val name: String?,
    val timeCreate: Long,
    val timeModify: Long,
    val isDeleted: Boolean,
)

@Keep
@ModelForNetwork
data class TallyRemoteLabelReq(
    val id: String,
    val userId: String,
    val bookId: String,
    val name: String?,
    val timeCreate: Long,
    val isDeleted: Boolean,
)

@Keep
data class TallyLabelInsertDto(
    // 如果为 null, 后续会自动创建
    val id: String? = null,
    val userId: String,
    val bookId: String,
    val name: String?,
    val timeCreate: Long = System.currentTimeMillis(),
    val timeModify: Long? = null,
    val isDeleted: Boolean = false,
    val isSync: Boolean = false,
)

@Keep
data class TallyLabelDto(
    val id: String,
    val userId: String,
    val bookId: String,
    val name: String?,
    val timeCreate: Long,
    val timeModify: Long?,
    val isDeleted: Boolean,
    val isSync: Boolean,
) {

    val getAdapter: TallyLabelDto?
        get() = if (this.isDeleted) {
            null
        } else {
            this
        }

}