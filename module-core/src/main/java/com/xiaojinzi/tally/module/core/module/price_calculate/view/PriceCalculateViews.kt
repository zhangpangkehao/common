package com.xiaojinzi.tally.module.core.module.price_calculate.view

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.compose.GridView
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_NORMAL
import com.xiaojinzi.tally.module.base.support.CostEmptyState
import com.xiaojinzi.tally.module.core.module.price_calculate.domain.PriceCalculateIntent
import kotlinx.coroutines.InternalCoroutinesApi

@Composable
private fun PriceCalculateKeyboardItemDecorateView(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = Modifier
            .clickable {
                onClick?.invoke()
            }
            .then(other = modifier)
            .padding(all = 1.dp)
            .nothing(),
    ) {
        content()
    }
}

@Composable
private fun PriceCalculateKeyboardTextOrIconItemView(
    modifier: Modifier = Modifier,
    text: String? = null,
    @DrawableRes
    iconRsd: Int? = null,
    iconColor: Color = MaterialTheme.colorScheme.onSurface,
    onClick: (() -> Unit)? = null,
) {
    PriceCalculateKeyboardItemDecorateView(
        modifier = modifier,
        onClick = onClick
    ) {
        text?.let { targetText ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .nothing()
            ) {
                Text(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .nothing(),
                    text = targetText,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                )
            }
        }
        iconRsd?.let { targetIconRsd ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .nothing(),
                contentAlignment = Alignment.Center,
            ) {
                val sizeDp = with(receiver = LocalDensity.current) {
                    MaterialTheme.typography.bodyMedium.fontSize.toDp() + 4.dp
                }
                Icon(
                    modifier = Modifier
                        .padding(vertical = 14.dp)
                        .size(size = sizeDp)
                        .nothing(),
                    painter = painterResource(id = targetIconRsd),
                    contentDescription = null,
                    tint = iconColor,
                )
            }
        }
    }
}

@Composable
private fun PriceCalculateKeyboardCompleteOrUpdateItemView(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
) {
    val vm: PriceCalculateViewModel = viewModel()
    val costState by vm.costUseCase.costStrStateOb.collectAsState(initial = CostEmptyState())
    val costIsCorrect by vm.costUseCase.costIsCorrectFormatStateOb.collectAsState(initial = false)
    PriceCalculateKeyboardItemDecorateView(
        modifier = modifier,
    ) {
        TextButton(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .navigationBarsPadding()
                .nothing(),
            enabled = (costState is CostEmptyState) || costIsCorrect,
            onClick = {
                onClick?.invoke()
            },
        ) {
            Text(
                text = "完成",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
private fun PriceCalculateKeyboardNumberItemView(
    modifier: Modifier = Modifier,
    number: Int,
    onClick: (() -> Unit)? = null
) {
    PriceCalculateKeyboardTextOrIconItemView(
        modifier = modifier,
        text = number.toString(),
        onClick = onClick
    )
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun PriceCalculateView(
    needInit: Boolean? = false,
    previewDefault: PriceCalculatePreviewDefault? = null,
) {
    val context = LocalContext.current
    BusinessContentView<PriceCalculateViewModel>(
        needInit = needInit,
    ) { vm ->
        val costState by vm.costUseCase.costStrStateOb.collectAsState(initial = CostEmptyState())
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color.Black.copy(
                        alpha = 0.2f,
                    ),
                )
                .nothing(),
        ) {
            Spacer(modifier = Modifier.weight(weight = 1f, fill = true))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(
                        color = MaterialTheme.colorScheme.surfaceColorAtElevation(elevation = 1.dp),
                    )
                    .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = APP_PADDING_NORMAL.dp)
                    .nothing(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Spacer(modifier = Modifier.weight(weight = 1f, fill = true))
                Text(
                    text = previewDefault?.numberStr ?: costState.strValue.ifEmpty { "0" },
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                    ),
                    textAlign = TextAlign.Start,
                )
            }
            GridView(
                Modifier
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                    )
                    .nothing(),
                items = (0..15).toList(),
                columnNumber = 4,
            ) { item ->
                when (item) {
                    in 0..2 -> PriceCalculateKeyboardNumberItemView(number = item + 7) {
                        vm.costUseCase.appendNumber(value = item + 7)
                        // vm.billCostUseCase.costAppend(target = (item + 7).toString())
                    }

                    in 4..6 -> PriceCalculateKeyboardNumberItemView(number = item) {
                        vm.costUseCase.appendNumber(value = item)
                        // vm.billCostUseCase.costAppend(target = item.toString())
                    }

                    in 8..10 -> PriceCalculateKeyboardNumberItemView(number = item - 7) {
                        vm.costUseCase.appendNumber(value = item - 7)
                        // vm.billCostUseCase.costAppend(target = (item - 7).toString())
                    }

                    3 -> PriceCalculateKeyboardTextOrIconItemView(
                        iconRsd = com.xiaojinzi.tally.lib.res.R.drawable.res_delete1,
                    ) {
                        vm.costUseCase.costDeleteLast()
                    }

                    7 -> PriceCalculateKeyboardTextOrIconItemView(text = "+") {
                        vm.costUseCase.appendAddSymbol()
                        // vm.billCostUseCase.costAppend(target = "+")
                    }

                    11 -> PriceCalculateKeyboardTextOrIconItemView(text = "-") {
                        vm.costUseCase.appendMinusSymbol()
                        // vm.billCostUseCase.costAppend(target = "-")
                    }

                    12 -> PriceCalculateKeyboardNumberItemView(
                        modifier = Modifier
                            .navigationBarsPadding()
                            .nothing(),
                        number = 0,
                    ) {
                        vm.costUseCase.appendNumber(value = 0)
                        // vm.billCostUseCase.costAppend(target = "0")
                    }

                    13 -> PriceCalculateKeyboardTextOrIconItemView(
                        modifier = Modifier
                            .navigationBarsPadding()
                            .nothing(),
                        text = ".",
                    ) {
                        vm.costUseCase.appendPoint()
                        // vm.billCostUseCase.costAppend(target = ".")
                    }

                    14 -> {}
                    15 -> PriceCalculateKeyboardCompleteOrUpdateItemView(
                        modifier = Modifier
                            .navigationBarsPadding()
                            .nothing(),
                    ) {
                        vm.addIntent(
                            intent = PriceCalculateIntent.Submit(
                                context = context,
                            ),
                        )
                    }

                    else -> {
                        error("Not support")
                    }
                }
            }
        }
    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun PriceCalculateViewWrap() {
    PriceCalculateView()
}

private data class PriceCalculatePreviewDefault(
    val numberStr: String,
)

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun PriceCalculateViewPreview() {
    PriceCalculateView(
        needInit = false,
        previewDefault = PriceCalculatePreviewDefault(
            numberStr = "123.00",
        )
    )
}