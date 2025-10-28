package com.xiaojinzi.tally.module.base.view.compose.reorderable

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.awaitLongPressOrCancellation
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput

fun Modifier.detectReorder(state: ReorderableState<*>) =
    this.then(
        Modifier.pointerInput(Unit) {
            awaitEachGesture {
                val down = awaitFirstDown(requireUnconsumed = false)
                var drag: PointerInputChange?
                var overSlop = Offset.Zero
                do {
                    drag = awaitPointerSlopOrCancellation(down.id, down.type) { change, over ->
                        change.consume()
                        overSlop = over
                    }
                } while (drag != null && !drag.isConsumed)
                if (drag != null) {
                    state.interactions.trySend(StartDrag(down.id, overSlop))
                }
            }
        }
    )


fun Modifier.detectReorderAfterLongPress(state: ReorderableState<*>) =
    this.then(
        Modifier.pointerInput(Unit) {
            awaitEachGesture {
                val down = awaitFirstDown(requireUnconsumed = false)
                this.awaitLongPressOrCancellation(pointerId = down.id)?.also {
                    state.interactions.trySend(StartDrag(down.id))
                }
            }
        }
    )