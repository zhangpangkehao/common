package com.xiaojinzi.tally.lib.res.model.tally

import androidx.annotation.Keep
import com.xiaojinzi.support.annotation.ModelDto
import com.xiaojinzi.support.annotation.ModelForNetwork

@Keep
@ModelForNetwork
data class TallyRemoteBillLabelRes(
    val id: String,
    val userId: String,
    val bookId: String,
    val billId: String,
    val labelId: String,
    val timeModify: Long,
    val isDeleted: Boolean,
)

@Keep
@ModelForNetwork
data class TallyRemoteBillLabelReq(
    val id: String,
    val userId: String,
    val bookId: String,
    val billId: String,
    val labelId: String,
    val isDeleted: Boolean,
)

@Keep
@ModelDto
data class TallyBillLabelDto(
    val id: String,
    val userId: String,
    val bookId: String,
    val billId: String,
    val labelId: String,
    val timeModify: Long?,
    val isDeleted: Boolean,
    val isSync: Boolean
)

@Keep
data class TallyBillLabelInsertDto(
    // 从远程同步的时候, 这个字段需要赋值
    val id: String? = null,
    val userId: String,
    val bookId: String,
    val billId: String,
    val labelId: String,
    val timeModify: Long?,
    val isDeleted: Boolean,
    val isSync: Boolean = false,
)