package com.xiaojinzi.tally.module.base.spi

import com.xiaojinzi.reactive.domain.BaseUseCase
import com.xiaojinzi.support.annotation.StateHotObservable
import com.xiaojinzi.support.ktx.SharedStateFlow
import com.xiaojinzi.support.ktx.SuspendAction0
import com.xiaojinzi.tally.module.base.support.CostState
import kotlinx.coroutines.flow.Flow

/**
 * 这个是计算钱的一个通用的业务逻辑类
 * 不是一个单例的实现
 */
interface CostUseCaseSpi : BaseUseCase {

    /**
     * 钱的字符串, 比如 100.00 + 100.00
     */
    @StateHotObservable
    val costStrStateOb: SharedStateFlow<CostState>

    /**
     * costStr 解析出来的 Float, 如果不合法, 这里就是 null
     */
    @StateHotObservable
    val parsedCostStateOb: Flow<Float?>

    /**
     * 费用的格式是否正确
     */
    @StateHotObservable
    val costIsCorrectFormatStateOb: Flow<Boolean>

    /**
     * 是否是 0 的数字, 比如 0, 0.0, 0.00
     */
    suspend fun isEmptyOrZeroNumber(): Boolean

    /**
     * 计算一个结果, 根据给的算式
     */
    @Throws(Exception::class)
    suspend fun calculateResult(target: String): Double

    /**
     * cost 字符串的累加
     */
    fun costAppend(
        target: String,
        isRemoveDefaultInput: Boolean = false,
        isReset: Boolean = false,
    )

    fun appendNumber(value: Int)
    fun appendPoint()
    fun appendAddSymbol()
    fun appendMinusSymbol()

    /**
     * cost 字符串删除最后一个字符功能
     */
    fun costDeleteLast()

    /**
     * 反转 cost 的正负值
     */
    fun toggleCostValue()

    /**
     * 重置
     */
    fun resetAction(): SuspendAction0

}