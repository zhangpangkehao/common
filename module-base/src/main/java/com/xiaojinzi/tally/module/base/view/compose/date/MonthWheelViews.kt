package com.xiaojinzi.tally.module.base.view.compose.date

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.xiaojinzi.support.ktx.getMonthByTimeStamp
import com.xiaojinzi.support.ktx.toStringItemDto
import com.xiaojinzi.tally.module.base.view.compose.WheelItem
import com.xiaojinzi.tally.module.base.view.compose.WheelViewWithIndex
import com.xiaojinzi.tally.module.base.view.compose.rememberWheelState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

private val monthRsdList = listOf(
    com.xiaojinzi.tally.lib.res.R.string.res_str_january,
    com.xiaojinzi.tally.lib.res.R.string.res_str_february,
    com.xiaojinzi.tally.lib.res.R.string.res_str_march,
    com.xiaojinzi.tally.lib.res.R.string.res_str_april,
    com.xiaojinzi.tally.lib.res.R.string.res_str_may,
    com.xiaojinzi.tally.lib.res.R.string.res_str_june,
    com.xiaojinzi.tally.lib.res.R.string.res_str_july,
    com.xiaojinzi.tally.lib.res.R.string.res_str_august,
    com.xiaojinzi.tally.lib.res.R.string.res_str_september,
    com.xiaojinzi.tally.lib.res.R.string.res_str_october,
    com.xiaojinzi.tally.lib.res.R.string.res_str_november,
    com.xiaojinzi.tally.lib.res.R.string.res_str_december,
)

@ExperimentalMaterialApi
@Composable
fun MonthWheelView(
    modifier: Modifier = Modifier,
    monthWheelState: MonthWheelState = rememberMonthWheelState(
        initValue = getMonthByTimeStamp(
            timeStamp = System.currentTimeMillis()
        ),
    ),
    visibleCount: Int = 5,
    lineThickness: Dp = 1.dp,
    lineWidthPercent: Float = 1f,
    itemHeight: Dp = 48.dp,
) {
    val wheelState = rememberWheelState(
        initIndex = monthWheelState.currentValue.coerceIn(range = monthRsdList.indices)
    )
    LaunchedEffect(key1 = wheelState) {
        snapshotFlow { wheelState.currentIndex }
            .onEach {
                if (it != monthWheelState.currentValue) {
                    monthWheelState.currentValue = it
                }
            }
            .launchIn(scope = this)
    }
    WheelViewWithIndex(
        modifier = modifier,
        items = monthRsdList,
        wheelState = wheelState,
        visibleCount = visibleCount,
        lineThickness = lineThickness,
        lineWidthPercent = lineWidthPercent,
        itemHeight = itemHeight,
    ) { index, _ ->
        WheelItem(
            content = "${index + 1}月".toStringItemDto(),
            isSelected = index == wheelState.currentIndex,
        )
    }
}

class MonthWheelState(currentValue: Int) {

    var currentValue by mutableIntStateOf(currentValue)

    companion object {
        val Saver = object : Saver<MonthWheelState, Int> {
            override fun restore(value: Int): MonthWheelState {
                return MonthWheelState(currentValue = value)
            }

            override fun SaverScope.save(value: MonthWheelState): Int {
                return value.currentValue
            }
        }
    }

}

@Composable
fun rememberMonthWheelState(
    initValue: Int = 0
) = rememberSaveable(saver = MonthWheelState.Saver) {
    MonthWheelState(currentValue = initValue)
}