package com.xiaojinzi.tally.module.main.module.main.statistics.view

import androidx.lifecycle.viewModelScope
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.StateHotObservable
import com.xiaojinzi.support.annotation.ViewLayer
import com.xiaojinzi.support.ktx.sharedStateIn
import com.xiaojinzi.support.ktx.toStringItemDto
import com.xiaojinzi.tally.module.base.view.compose.PieChartItemVo
import com.xiaojinzi.tally.module.base.view.compose.PieChartVo
import com.xiaojinzi.tally.module.base.view.compose.TendencyChatItemVo
import com.xiaojinzi.tally.module.base.view.compose.TendencyChatVo
import com.xiaojinzi.tally.module.main.module.main.statistics.domain.StatisticsUseCase
import com.xiaojinzi.tally.module.main.module.main.statistics.domain.StatisticsUseCaseImpl
import kotlinx.coroutines.flow.map

@ViewLayer
class StatisticsViewModel(
    private val useCase: StatisticsUseCase = StatisticsUseCaseImpl(),
) : BaseViewModel(),
    StatisticsUseCase by useCase {

    @StateHotObservable
    val tendencyChatSpendingStatisticsStateObVo = useCase
        .tendencyChatSpendingStatisticsStateOb
        .map { list ->
            val maxValue = list.maxOfOrNull { it.amount.value }?: 0L
            TendencyChatVo(
                items = list.map { item ->
                    TendencyChatItemVo(
                        timeRange = item.timeRange,
                        timeStr = item.timeStr,
                        amount = item.amount.toYuan(),
                        percent = if (maxValue == 0L) {
                            0f
                        } else {
                            item.amount.value / maxValue.toFloat()
                        }
                    )
                }
            )
        }
        .sharedStateIn(
            scope = viewModelScope,
        )

    @StateHotObservable
    val tendencyChatIncomeStatisticsStateObVo = useCase
        .tendencyChatIncomeStatisticsStateOb
        .map { list ->
            val maxValue = list.maxOfOrNull { it.amount.value }?: 0L
            TendencyChatVo(
                items = list.map { item ->
                    TendencyChatItemVo(
                        timeRange = item.timeRange,
                        timeStr = item.timeStr,
                        amount = item.amount.toYuan(),
                        percent = if (maxValue == 0L) {
                            0f
                        } else {
                            item.amount.value / maxValue.toFloat()
                        }
                    )
                }
            )
        }
        .sharedStateIn(
            scope = viewModelScope,
        )

    @StateHotObservable
    val categorySpendingPieChartStateObVo = useCase
        .allCategorySpendingStatisticsStateOb
        .map { list ->
            PieChartVo(
                content = "".toStringItemDto(),
                items = list.map { item ->
                    PieChartItemVo(
                        content = item.categoryName,
                        percent = item.percent,
                    )
                }.take(n = 10)
            )
        }
        .sharedStateIn(
            scope = viewModelScope,
        )

    @StateHotObservable
    val categoryIncomePieChartStateObVo = useCase
        .allCategoryIncomeStatisticsStateOb
        .map { list ->
            PieChartVo(
                content = "".toStringItemDto(),
                items = list.map { item ->
                    PieChartItemVo(
                        content = item.categoryName,
                        percent = item.percent,
                    )
                }.take(n = 10)
            )
        }
        .sharedStateIn(
            scope = viewModelScope,
        )

    @StateHotObservable
    val labelSpendingPieChartStateObVo = useCase
        .allLabelSpendingStatisticsStateOb
        .map { list ->
            PieChartVo(
                content = "".toStringItemDto(),
                items = list.map { item ->
                    PieChartItemVo(
                        content = item.labelName,
                        percent = item.percent,
                    )
                }.take(n = 10)
            )
        }
        .sharedStateIn(
            scope = viewModelScope,
        )

    @StateHotObservable
    val labelIncomePieChartStateObVo = useCase
        .allLabelIncomeStatisticsStateOb
        .map { list ->
            PieChartVo(
                content = "".toStringItemDto(),
                items = list.map { item ->
                    PieChartItemVo(
                        content = item.labelName,
                        percent = item.percent,
                    )
                }.take(n = 10)
            )
        }
        .sharedStateIn(
            scope = viewModelScope,
        )

}