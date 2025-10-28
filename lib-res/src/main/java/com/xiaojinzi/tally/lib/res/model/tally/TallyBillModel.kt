package com.xiaojinzi.tally.lib.res.model.tally

import androidx.annotation.Keep
import com.xiaojinzi.support.annotation.ModelForNetwork
import com.xiaojinzi.tally.lib.res.model.user.UserInfoCacheDto

@Keep
@ModelForNetwork
data class TallyRemoteBillRes(
    val id: String,
    val originBillId: String,
    val note: String?,
    val userId: String,
    val type: String,
    // 时间戳, 毫秒值
    val time: Long,
    val bookId: String,
    val categoryId: String?,
    val accountId: String?,
    val transferTargetAccountId: String?,
    val amount: MoneyFen,
    val isNotCalculate: Boolean,
    // 时间戳, 毫秒值
    val timeModify: Long,
    // 时间戳, 毫秒值
    val timeCreate: Long,
    // 是否删除
    val isDeleted: Boolean,
)

@Keep
@ModelForNetwork
data class TallyRemoteBillReq(
    val id: String,
    val originBillId: String,
    val note: String?,
    val userId: String,
    val type: String,
    // 时间戳, 毫秒值
    val time: Long,
    val bookId: String,
    val categoryId: String?,
    val accountId: String?,
    val transferTargetAccountId: String?,
    val amount: Long,
    val isNotCalculate: Boolean,
    val timeCreate: Long,
    // 是否删除
    val isDeleted: Boolean,
)

@Keep
data class TallyBillInsertDto(
    // 从远程同步的时候, 这个字段需要赋值
    val id: String? = null,
    val originBillId: String? = null,
    val userId: String,
    val bookId: String,
    val type: String,
    val time: Long,
    val categoryId: String? = null,
    val accountId: String? = null,
    // 转账的目标账户
    val transferTargetAccountId: String? = null,
    // 金额
    val amount: MoneyFen,
    // 备注
    val note: String? = null,
    // 不计入收支, 默认 false
    val isNotCalculate: Boolean = false,
    val timeCreate: Long = System.currentTimeMillis(),
    val timeModify: Long? = null,
    val isDeleted: Boolean = false,
    val isSync: Boolean = false,
)

@Keep
data class TallyBillDto(
    val id: String,
    val userId: String,
    val type: Type,
    val time: Long,
    val bookId: String,
    val categoryId: String?,
    val accountId: String?,
    // 转账的目标账户
    val transferTargetAccountId: String?,
    // 退款的目标账单
    val originBillId: String?,
    // 金额
    val amount: MoneyFen,
    // 备注
    val note: String?,
    /**
     * 不计入收支
     * 含义是统计的各个地方都要忽略
     * 不提现在各个统计的地方, 比如当月支出和收入. 但是在账单列表中是要显示的
     * 账本统计也不能参与其中.
     * 但是账户的钱是要变化的. 因为不参与统计但是确实真实的支出或者收入了
     */
    val isNotCalculate: Boolean,
    val timeCreate: Long,
    val timeModify: Long?,
    val isDeleted: Boolean,
    val isSync: Boolean,
) {

    /**
     * 账单的类型
     */
    enum class Type(
        val value: String,
    ) // 占位
    {
        Unknown(
            value = "",
        ),
        NORMAL(
            value = TYPE_NORMAL
        ),
        REFUND(
            value = TYPE_REFUND
        ),
        TRANSFER(
            value = TYPE_TRANSFER,
        ),
        ;

        companion object {
            fun from(value: String): Type {
                return when (value) {
                    TYPE_NORMAL -> NORMAL
                    TYPE_REFUND -> REFUND
                    TYPE_TRANSFER -> TRANSFER
                    else -> Unknown
                }
            }
        }
    }

    companion object {
        // 普通账单
        const val TYPE_NORMAL = "normal"

        // 退款账单
        const val TYPE_REFUND = "refund"

        // 转账账单
        const val TYPE_TRANSFER = "transfer"
    }

    /**
     * 如果被删除了, 那么就是 null
     */
    val getAdapter: TallyBillDto?
        get() {
            return if (isDeleted) {
                null
            } else {
                this
            }
        }

    /**
     * 这个账单类型是否可退款
     */
    val canRefundWithType: Boolean
        get() {
            return type == TallyBillDto.Type.NORMAL || type == TallyBillDto.Type.TRANSFER
        }

    /**
     * 针对这个用户是否可退款
     */
    fun userCanRefund(targetUserId: String): Boolean {
        return this.userId == targetUserId && (type == TallyBillDto.Type.NORMAL || type == TallyBillDto.Type.TRANSFER)
    }

}

@Keep
data class TallyBillDetailDto(
    val core: TallyBillDto,
    val user: UserInfoCacheDto?,
    val book: TallyBookDto?,
    val category: TallyCategoryDto?,
    val account: TallyAccountDto?,
    val transferTargetAccount: TallyAccountDto?,
    val labelList: List<TallyLabelDto>,
) {

    val categoryAdapter: TallyCategoryDto?
        get() {
            return if (category?.isDeleted == true) {
                null
            } else {
                category
            }
        }

    /**
     * 表达到用户层面, 需要进行的转化的 flag: 1 或者 -1
     */
    val moneyTransform: Int?
        get() {
            return when (core.type) {

                // 如果是普通单, 那么这个值是 category 决定的
                TallyBillDto.Type.NORMAL -> {
                    categoryAdapter?.type?.moneyTransform
                }

                TallyBillDto.Type.TRANSFER -> -1

                TallyBillDto.Type.REFUND -> 1

                else -> null
            }
        }

    /**
     * 处理了被删除的情况
     */
    val getAdapter: TallyBillDetailDto?
        get() {
            return if (core.isDeleted) {
                null
            } else {
                this
            }
        }

}