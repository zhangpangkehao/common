package com.xiaojinzi.tally.module.base.view.compose

import androidx.annotation.FloatRange
import androidx.annotation.Keep
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.tally.lib.res.model.tally.MoneyYuan
import kotlin.random.Random

@Keep
data class TendencyChatItemVo(
    // 时间段
    val timeRange: Pair<Long, Long>? = null,
    val timeStr: String? = null,
    // 金额
    val amount: MoneyYuan,
    // 0 表示这个 item 的数据是最小的, 会在最下面
    // 1 表示这个 item 的数据是最大的, 会在最上面
    @FloatRange(from = 0.0, to = 1.0)
    val percent: Float,
)

@Keep
data class TendencyChatVo(
    val items: List<TendencyChatItemVo>,
)

@Composable
fun TendencyView(
    modifier: Modifier,
    vo: TendencyChatVo,
    initSelectIndex: Int = -1,
    onIndexChanged: (index: Int) -> Unit = {},
) {
    val pointColor = MaterialTheme.colorScheme.primary
    val lineColor = MaterialTheme.colorScheme.primary.copy(
        alpha = 0.5f,
    )
    val colorSelected = MaterialTheme.colorScheme.primaryContainer.copy(
        alpha = 0.5f,
    )
    val colorNormal = MaterialTheme.colorScheme.primaryContainer.copy(
        alpha = 0.2f,
    )
    var selectIndex by remember {
        mutableIntStateOf(value = initSelectIndex)
    }
    LaunchedEffect(key1 = vo.items.size) {
        selectIndex = initSelectIndex
        onIndexChanged.invoke(selectIndex)
    }
    val itemSize by rememberUpdatedState(newValue = vo.items.size)
    val scaleBetweenOuterAndInner = 0.8f
    Box(
        modifier = modifier
            .drawWithContent {
                if (itemSize > 0) {
                    val eachOuterWidth = (this.size.width) / itemSize
                    val eachInnerWidth = eachOuterWidth * scaleBetweenOuterAndInner
                    val circleRadius = eachInnerWidth / 5f
                    val halfDiffBetweenOuterAndInner = (eachOuterWidth - eachInnerWidth) / 2f
                    val eachHeight = this.size.height
                    var preCircleCenter: Offset? = null
                    for (index in 0 until itemSize) {
                        // 绘制柱子
                        run {
                            this.drawRect(
                                color = if (index == selectIndex) {
                                    colorSelected
                                } else {
                                    colorNormal
                                },
                                topLeft = Offset(
                                    x = index * eachOuterWidth + halfDiffBetweenOuterAndInner,
                                    y = 0f,
                                ),
                                size = androidx.compose.ui.geometry.Size(
                                    width = eachInnerWidth,
                                    height = eachHeight,
                                ),
                            )
                        }
                        // 绘制走势图的点
                        val circleCenter = Offset(
                            x = index * eachOuterWidth + eachOuterWidth / 2f,
                            y = circleRadius + (eachHeight - circleRadius * 2) * (1 - vo.items[index].percent),
                        )
                        run {
                            this.drawCircle(
                                pointColor,
                                radius = circleRadius,
                                center = circleCenter,
                            )
                            this.drawCircle(
                                lineColor,
                                radius = circleRadius / 2f,
                                center = circleCenter,
                            )
                        }
                        // 绘制点和点之间的线
                        preCircleCenter?.let {
                            this.drawLine(
                                color = lineColor,
                                start = it,
                                end = circleCenter,
                                strokeWidth = circleRadius / 2f,
                            )
                        }
                        preCircleCenter = circleCenter
                    }
                }
            }
            .pointerInput(Unit) {
                if (itemSize > 0) {
                    val calSelectIndex: (x: Float) -> Unit = {
                        selectIndex = (it / (this.size.width / itemSize)).toInt()
                        if (selectIndex > -1 && selectIndex < itemSize) {
                            onIndexChanged.invoke(selectIndex)
                        }
                    }
                    awaitEachGesture {
                        val firstDown = awaitFirstDown()
                        firstDown.consume()
                        calSelectIndex.invoke(firstDown.position.x)
                        do {
                            val pointerEvent = awaitPointerEvent()
                            pointerEvent.changes.forEach {
                                it.consume()
                            }
                            when (pointerEvent.type) {
                                PointerEventType.Move -> {
                                    pointerEvent.changes
                                        .firstOrNull()
                                        ?.let { pointerInputChange ->
                                            calSelectIndex.invoke(pointerInputChange.position.x)
                                        }
                                }
                            }
                        } while (pointerEvent.changes.any { it.pressed })
                    }
                }
            }
            .nothing()
    )
}

@Preview
@Composable
private fun TendencyViewPreview() {
    TendencyView(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 80.dp)
            .nothing(),
        vo = TendencyChatVo(
            items = listOf(
                TendencyChatItemVo(
                    amount = MoneyYuan(value = 1f),
                    percent = 0f,
                )
            ) + (0..20).map {
                TendencyChatItemVo(
                    amount = MoneyYuan(value = 1f),
                    percent = Random.nextInt(
                        from = 0, until = 100,
                    ) / 100f,
                )
            },
        ),
        initSelectIndex = 2,
    )
}