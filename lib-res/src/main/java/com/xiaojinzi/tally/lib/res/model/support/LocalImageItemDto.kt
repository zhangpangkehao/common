package com.xiaojinzi.tally.lib.res.model.support

import androidx.annotation.DrawableRes
import androidx.annotation.Keep
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource

@Keep
data class LocalImageItemDto(
    @DrawableRes
    val rsd: Int? = null,
    val vector: ImageVector? = null,
) {

    init {
        if (rsd == null && vector == null) {
            throw IllegalArgumentException("rsd and vector can not be null at the same time")
        }
    }

}

fun @receiver:DrawableRes Int.toLocalImageItemDto() = LocalImageItemDto(rsd = this)

fun ImageVector.toLocalImageItemDto() = LocalImageItemDto(vector = this)

@Composable
fun LocalImageItemDto.rememberPainter(): Painter {
    return when {
        this.rsd != null -> {
            return painterResource(id = this.rsd)
        }

        this.vector != null -> {
            rememberVectorPainter(image = this.vector)
        }

        else -> {
            throw IllegalArgumentException("rsd and vector can not be null at the same time")
        }
    }
}