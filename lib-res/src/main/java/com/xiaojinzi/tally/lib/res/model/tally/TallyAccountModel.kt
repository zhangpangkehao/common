package com.xiaojinzi.tally.lib.res.model.tally

import androidx.annotation.Keep
import com.xiaojinzi.support.annotation.ModelForNetwork
import com.xiaojinzi.tally.lib.res.model.support.MoneyFenAnno

@Keep
@ModelForNetwork
data class TallyRemoteAccountRes(
    val id: String,
    val userId: String,
    val bookId: String,
    val name: String?,
    val iconName: String?,
    val balanceInit: MoneyFen,
    val isExcluded: Boolean,
    val isDefault: Boolean,
    val timeCreate: Long,
    val timeModify: Long,
    val isDeleted: Boolean,
)

@Keep
@ModelForNetwork
data class TallyRemoteAccountReq(
    val id: String,
    val userId: String,
    val bookId: String,
    val name: String?,
    val iconName: String?,
    val balanceInit: MoneyFen,
    val isExcluded: Boolean,
    val isDefault: Boolean,
    val timeCreate: Long,
    val isDeleted: Boolean,
)

@Keep
data class TallyAccountInsertDto(
    // 如果为 null, 后续会自动创建
    val id: String? = null,
    val userId: String,
    val bookId: String,
    val iconName: String?,
    val name: String?,
    val balanceInit: MoneyFen,
    val isExcluded: Boolean,
    val isDefault: Boolean,
    val timeCreate: Long = System.currentTimeMillis(),
    val timeModify: Long? = null,
    val isDeleted: Boolean = false,
    val isSync: Boolean = false,
)

@Keep
data class TallyAccountDto(
    val id: String,
    val userId: String,
    val bookId: String,
    val name: String?,
    val iconName: String?,
    @MoneyFenAnno
    val balanceInit: MoneyFen,
    // 这个值是算出来的
    val balanceCurrent: MoneyFen,
    val isExcluded: Boolean,
    val isDefault: Boolean,
    val timeCreate: Long,
    val timeModify: Long?,
    val isDeleted: Boolean,
    val isSync: Boolean,
) {

    val getAdapter: TallyAccountDto?
        get() = if (this.isDeleted) {
            null
        } else {
            this
        }

}