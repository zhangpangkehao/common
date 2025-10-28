package com.xiaojinzi.tally.module.core.spi

import com.xiaojinzi.component.anno.ServiceAnno
import com.xiaojinzi.reactive.domain.BaseUseCaseImpl
import com.xiaojinzi.support.ktx.ErrorIgnoreContext
import com.xiaojinzi.support.ktx.MutableSharedStateFlow
import com.xiaojinzi.support.ktx.SuspendAction0
import com.xiaojinzi.support.ktx.notSupportError
import com.xiaojinzi.support.ktx.sharedStateIn
import com.xiaojinzi.support.ktx.suspendAction0
import com.xiaojinzi.tally.lib.res.model.exception.AbsNumberCanNotGreaterThan9999999Exception
import com.xiaojinzi.tally.module.base.spi.CostUseCaseSpi
import com.xiaojinzi.tally.module.base.support.CostEmptyState
import com.xiaojinzi.tally.module.base.support.CostInitState
import com.xiaojinzi.tally.module.base.support.CostState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.absoluteValue

@ServiceAnno(CostUseCaseSpi::class, singleTon = false)
class CostUseCaseSpiImpl : BaseUseCaseImpl(), CostUseCaseSpi {

    /**
     * 耗费的钱的字符串
     */
    override val costStrStateOb =
        MutableSharedStateFlow<CostState>(initValue = CostEmptyState())

    override val parsedCostStateOb = costStrStateOb
        .map { costState ->
            runCatching {
                calculateResult(target = costState.strValue)
            }.getOrNull()?.toFloat()
        }.sharedStateIn(
            scope = scope,
        )

    override val costIsCorrectFormatStateOb = costStrStateOb
        .map {
            it.isCorrectFormat()
        }

    override suspend fun isEmptyOrZeroNumber(): Boolean {
        return costStrStateOb.first().isEmptyOrZero
    }

    private suspend fun doCalculateResult(target: String): Double {
        return withContext(context = Dispatchers.Default) {
            if (target.isEmpty()) {
                return@withContext 0.0
            }
            var index = target.indexOf('+')
            if (index != -1) {
                return@withContext doCalculateResult(
                    target = target.substring(startIndex = 0, endIndex = index)
                ) + doCalculateResult(
                    target = target.substring(startIndex = index + 1)
                )
            }
            index = target.lastIndexOf('-')
            if (index != -1) {
                return@withContext doCalculateResult(
                    target = target.substring(startIndex = 0, endIndex = index)
                ) - doCalculateResult(
                    target = target.substring(startIndex = index + 1)
                )
            }
            return@withContext target.toDouble()
        }
    }

    override suspend fun calculateResult(target: String): Double {
        return doCalculateResult(target = target).apply {
            if (this.absoluteValue > 9999999.0) {
                throw AbsNumberCanNotGreaterThan9999999Exception()
            }
        }
    }

    override fun costAppend(target: String, isRemoveDefaultInput: Boolean, isReset: Boolean) {
        var temp = if (isReset) {
            CostEmptyState()
        } else {
            costStrStateOb.value
        }
        target.forEach {
            temp = when (it) {
                '+' -> temp.appendAddFlag()
                '-' -> temp.appendMinusFlag()
                '.' -> temp.appendPointFlag()
                in ('0'..'9') -> temp.appendNumber(value = it.code - 48)
                else -> notSupportError()
            }
        }
        costStrStateOb.value = if (isRemoveDefaultInput) {
            CostInitState(
                initState = temp,
            )
        } else {
            temp
        }
    }

    override fun appendNumber(value: Int) {
        costStrStateOb.value = costStrStateOb.value.appendNumber(value = value)
    }

    override fun appendPoint() {
        costStrStateOb.value = costStrStateOb.value.appendPointFlag()
    }

    override fun appendAddSymbol() {
        costStrStateOb.value = costStrStateOb.value.appendAddFlag()
    }

    override fun appendMinusSymbol() {
        costStrStateOb.value = costStrStateOb.value.appendMinusFlag()
    }

    override fun costDeleteLast() {
        costStrStateOb.value = costStrStateOb.value.delete()
        /*val currValue = costStrObservableDTO.value
        if (currValue.isNotEmpty()) {
            costStrObservableDTO.value =
                currValue.substring(startIndex = 0, endIndex = currValue.lastIndex)
        }*/
    }

    override fun toggleCostValue() {
        scope.launch(context = ErrorIgnoreContext) {
            val costToggledStr =
                (-calculateResult(target = costStrStateOb.value.strValue)).toString()
            costStrStateOb.value = CostEmptyState()
            costAppend(target = costToggledStr)
        }
    }

    override fun resetAction(): SuspendAction0 {
        return suspendAction0 {
            costStrStateOb.value = CostEmptyState()
        }
    }

}