package com.xiaojinzi.tally.lib.res.model.ai

import androidx.annotation.Keep
import com.xiaojinzi.support.annotation.ModelDto
import com.xiaojinzi.support.annotation.ModelForNetwork

@Keep
@ModelForNetwork
data class AiBillAnalyzeRes(
    val isSpending: Boolean? = null,
    val categoryName: String? = null,
)

@Keep
@ModelDto
data class AiBillAnalyzeResDto(
    val isSpending: Boolean? = null,
    val categoryName: String? = null,
)

fun AiBillAnalyzeRes.toDto() = AiBillAnalyzeResDto(
    isSpending = isSpending,
    categoryName = categoryName,
)