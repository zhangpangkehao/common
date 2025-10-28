package com.xiaojinzi.tally.module.base.support

import androidx.annotation.Keep
import com.xiaojinzi.support.ktx.Assert
import com.xiaojinzi.support.ktx.LogSupport

private const val TAG = "CostState"

/**
 * 状态模式的抽象
 */
abstract class CostState(
    // 上一个状态
    open val preState: CostState?,
    val strValue: String
) //
{

    val length: Int = strValue.length

    /**
     * 退格键
     */
    open fun delete(): CostState {
        LogSupport.d(
            tag = TAG,
            content = "delete 退格键. strValue = $strValue, preState = ${preState?.strValue}"
        )
        return preState ?: this
    }

    /**
     * 是否是 0 或者 empty
     */
    open val isEmptyOrZero: Boolean = false

    /**
     * 是否格式正确
     */
    abstract fun isCorrectFormat(): Boolean

    /**
     * 添加数字
     */
    abstract fun appendNumber(value: Int): CostState

    /**
     * 加号
     */
    abstract fun appendAddFlag(): CostState

    /**
     * 减号
     */
    abstract fun appendMinusFlag(): CostState

    /**
     * 小数点
     */
    abstract fun appendPointFlag(): CostState

}

@Keep
class CostEmptyState : CostState(preState = null, strValue = "") {

    override val isEmptyOrZero: Boolean
        get() = true

    override fun isCorrectFormat(): Boolean {
        return false
    }

    override fun appendNumber(value: Int): CostState {
        return CostNumberState(
            preState = this, number = value
        )
        /*return if (value == 0) {
            this
        } else {
            CostNumberState(
                preState = this, number = value
            )
        }*/
    }

    override fun appendAddFlag(): CostState {
        return this
    }

    override fun appendMinusFlag(): CostState {
        return CostMinusSignState(
            preState = this,
        )
    }

    override fun appendPointFlag(): CostState {
        return this
    }

}

@Keep
class CostInitState private constructor(
    private val initState: CostState,
    private val emptyState: CostEmptyState = CostEmptyState(),
) : CostState(
    preState = emptyState, strValue = initState.strValue,
) {

    constructor(
        initState: CostState,
    ) : this(
        initState = initState,
        emptyState = CostEmptyState(),
    )

    override fun isCorrectFormat(): Boolean {
        return initState.isCorrectFormat()
    }

    override fun appendNumber(value: Int): CostState {
        return emptyState.appendNumber(value = value)
    }

    override fun appendAddFlag(): CostState {
        return initState.appendAddFlag()
    }

    override fun appendMinusFlag(): CostState {
        return initState.appendMinusFlag()
    }

    override fun appendPointFlag(): CostState {
        return initState.appendPointFlag()
    }


}

@Keep
class CostMinusSignState(override val preState: CostEmptyState) :
    CostState(preState = preState, strValue = "-") {

    override fun isCorrectFormat(): Boolean {
        return false
    }

    override fun appendNumber(value: Int): CostState {
        return CostNumberState(
            preState = this,
            number = value
        )
    }

    override fun appendAddFlag(): CostState {
        return preState
    }

    override fun appendMinusFlag(): CostState {
        return this
    }

    override fun appendPointFlag(): CostState {
        return this
    }

}

/**
 * 表示输入的数字部分：123432
 * 最长7位
 */
@Keep
class CostNumberState(preState: CostState?, val number: Int) :
    CostState(preState = preState, strValue = "${preState?.strValue ?: ""}$number") {

    // 自然数字最多支持八个
    private val maxCount = 7

    init {
        Assert.assertTrue(b = number >= 0)
        Assert.assertTrue(b = number <= 9)
    }

    override val isEmptyOrZero: Boolean
        get() = strValue == "0"

    override fun isCorrectFormat(): Boolean {
        return true
    }

    override fun appendNumber(value: Int): CostState {
        return if (strValue.count { it.isDigit() } >= maxCount) {
            this
        } else {
            if (strValue == "0") { // 如果是 0, 然后添加了其他数字
                CostNumberState(
                    preState = CostEmptyState(),
                    number = value,
                )
            } else {
                CostNumberState(preState = this, number = value)
            }
        }
    }

    override fun appendAddFlag(): CostState {
        return CostNumberAndArithmeticSymbolsState(preState = this, symbols = '+')
    }

    override fun appendMinusFlag(): CostState {
        return CostNumberAndArithmeticSymbolsState(preState = this, symbols = '-')
    }

    override fun appendPointFlag(): CostState {
        return CostNumberAndPointState(preState = this)
    }

}

/**
 * 数字和算术符号
 */
@Keep
class CostNumberAndArithmeticSymbolsState(
    override val preState: CostNumberState,
    symbols: Char
) : CostState(preState = preState, strValue = "${preState.strValue}$symbols") {

    init {
        Assert.assertTrue(b = symbols in listOf('+', '-'))
    }

    override fun isCorrectFormat(): Boolean {
        return false
    }

    override fun appendNumber(value: Int): CostState {
        return CostExpressionState(
            preState = this,
            nextState = CostNumberState(
                preState = CostEmptyState(),
                number = value
            ),
        )
    }

    override fun appendAddFlag(): CostState {
        return CostNumberAndArithmeticSymbolsState(
            preState = preState,
            symbols = '+'
        )
    }

    override fun appendMinusFlag(): CostState {
        return CostNumberAndArithmeticSymbolsState(
            preState = preState,
            symbols = '-'
        )
    }

    override fun appendPointFlag(): CostState {
        return CostNumberAndPointState(
            preState = preState
        )
    }

}

/**
 * 数字和小数点
 */
@Keep
class CostNumberAndPointState(override val preState: CostNumberState) :
    CostState(preState = preState, strValue = "${preState.strValue}.") {

    override fun isCorrectFormat(): Boolean {
        return false
    }

    override fun appendNumber(value: Int): CostState {
        return CostDoubleState(
            preState = this,
            number = value,
            // 两位小数, 那这里就是 2-1, 因为这里已经添加了一个
            restNumberCount = 1,
        )
    }

    override fun appendAddFlag(): CostState {
        return CostNumberAndArithmeticSymbolsState(
            preState = preState,
            symbols = '+'
        )
    }

    override fun appendMinusFlag(): CostState {
        return CostNumberAndArithmeticSymbolsState(
            preState = preState,
            symbols = '-'
        )
    }

    override fun appendPointFlag(): CostState {
        return this
    }

}

/**
 * 小数 Double
 */
@Keep
class CostDoubleState(
    preState: CostState,
    number: Int,
    // 小数点后面剩余的数字个数
    private val restNumberCount: Int
) :
    CostState(preState = preState, strValue = "${preState.strValue}$number") {

    override val isEmptyOrZero: Boolean
        get() = strValue == "0.0"

    override fun isCorrectFormat(): Boolean {
        return true
    }

    override fun appendNumber(value: Int): CostState {
        return if (restNumberCount <= 0) {
            this
        } else {
            CostDoubleState(
                preState = this,
                number = value,
                restNumberCount = restNumberCount - 1,
            )
        }
    }

    override fun appendAddFlag(): CostState {
        return CostDoubleAndArithmeticSymbolsState(
            preState = this,
            symbols = '+'
        )
    }

    override fun appendMinusFlag(): CostState {
        return CostDoubleAndArithmeticSymbolsState(
            preState = this,
            symbols = '-'
        )
    }

    override fun appendPointFlag(): CostState {
        return this
    }

}

/**
 * 小数和算术符号
 */
@Keep
class CostDoubleAndArithmeticSymbolsState(
    override val preState: CostDoubleState,
    symbols: Char,
) :
    CostState(preState = preState, strValue = "${preState.strValue}$symbols") {

    override fun isCorrectFormat(): Boolean {
        return false
    }

    override fun appendNumber(value: Int): CostState {
        return CostExpressionState(
            preState = this,
            nextState = CostNumberState(
                preState = CostEmptyState(),
                number = value
            )
        )
    }

    override fun appendAddFlag(): CostState {
        return CostDoubleAndArithmeticSymbolsState(
            preState = preState,
            symbols = '+'
        )
    }

    override fun appendMinusFlag(): CostState {
        return CostDoubleAndArithmeticSymbolsState(
            preState = preState,
            symbols = '-'
        )
    }

    override fun appendPointFlag(): CostState {
        return this
    }

}

/**
 * 算术表达式
 */
@Keep
class CostExpressionState(
    override val preState: CostState,
    val nextState: CostState,
) : CostState(
    preState = preState,
    strValue = "${preState.strValue}${nextState.strValue}"
) {

    override fun delete(): CostState {
        val nextPre = nextState.delete()
        return if (nextPre == nextState) {
            preState.delete()
        } else {
            CostExpressionState(
                preState = preState,
                nextState = nextPre
            )
        }
    }

    override fun isCorrectFormat(): Boolean {
        return nextState.isCorrectFormat()
    }

    override fun appendNumber(value: Int): CostState {
        return CostExpressionState(
            preState = preState,
            nextState = nextState.appendNumber(value = value)
        )
    }

    override fun appendAddFlag(): CostState {
        return CostExpressionState(
            preState = preState,
            nextState = nextState.appendAddFlag()
        )
    }

    override fun appendMinusFlag(): CostState {
        return CostExpressionState(
            preState = preState,
            nextState = nextState.appendMinusFlag()
        )
    }

    override fun appendPointFlag(): CostState {
        return CostExpressionState(
            preState = preState,
            nextState = nextState.appendPointFlag()
        )
    }

}
