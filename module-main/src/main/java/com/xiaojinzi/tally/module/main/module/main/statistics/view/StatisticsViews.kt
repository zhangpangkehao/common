package com.xiaojinzi.tally.module.main.module.main.statistics.view

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.TextButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.xiaojinzi.component.impl.routeApi
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.compose.util.circleClip
import com.xiaojinzi.support.compose.util.clickableNoRipple
import com.xiaojinzi.support.compose.util.contentWithComposable
import com.xiaojinzi.support.ktx.format2f
import com.xiaojinzi.support.ktx.launchIgnoreError
import com.xiaojinzi.support.ktx.notSupportError
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.tally.lib.res.model.support.DateTimeType
import com.xiaojinzi.tally.lib.res.model.support.rememberPainter
import com.xiaojinzi.tally.lib.res.model.tally.MoneyYuan
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_NORMAL
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_SMALL
import com.xiaojinzi.tally.lib.res.ui.AppBackgroundColor
import com.xiaojinzi.tally.lib.res.ui.AppHeightSpace
import com.xiaojinzi.tally.lib.res.ui.AppWidthSpace
import com.xiaojinzi.tally.module.base.spi.TallyDataSourceSpi
import com.xiaojinzi.tally.module.base.support.AppRouterCoreApi
import com.xiaojinzi.tally.module.base.support.AppServices
import com.xiaojinzi.tally.module.base.usecase.TimeSelectUseCase
import com.xiaojinzi.tally.module.base.view.compose.AppCommonEmptyDataView
import com.xiaojinzi.tally.module.base.view.compose.PieChartView
import com.xiaojinzi.tally.module.base.view.compose.PieChartVo
import com.xiaojinzi.tally.module.base.view.compose.TendencyChatVo
import com.xiaojinzi.tally.module.base.view.compose.TendencyView
import com.xiaojinzi.tally.module.main.module.main.statistics.domain.StatisticsCategoryItemUseCaseDto
import com.xiaojinzi.tally.module.main.module.main.statistics.domain.StatisticsIntent
import com.xiaojinzi.tally.module.main.module.main.statistics.domain.StatisticsLabelItemUseCaseDto
import com.xiaojinzi.tally.module.main.module.main.statistics.domain.StatisticsUseCase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun StatisticsCategoryItemView(
    modifier: Modifier = Modifier,
    item: StatisticsCategoryItemUseCaseDto?,
) {
    ConstraintLayout(
        modifier = modifier,
    ) {
        val (
            icon, categoryValue,
            amountValue, percentValue,
            percentForViewValue,
            percentView,
        ) = createRefs()

        Icon(
            modifier = Modifier
                .constrainAs(ref = icon) {
                    this.start.linkTo(anchor = parent.start, margin = APP_PADDING_NORMAL.dp)
                    this.top.linkTo(anchor = parent.top, margin = APP_PADDING_NORMAL.dp)
                }
                .circleClip()
                .background(
                    color = AppBackgroundColor,
                )
                .padding(all = 8.dp)
                .size(size = 20.dp)
                .nothing(),
            painter = item?.icon?.rememberPainter() ?: ColorPainter(
                color = Color.Transparent,
            ),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface,
        )

        Text(
            modifier = Modifier
                .constrainAs(ref = categoryValue) {
                    this.start.linkTo(anchor = icon.end, margin = APP_PADDING_NORMAL.dp)
                    this.top.linkTo(anchor = icon.top, margin = 0.dp)
                }
                .nothing(),
            text = item?.categoryName?.contentWithComposable() ?: "",
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface,
            ),
            textAlign = TextAlign.Start,
        )

        Text(
            modifier = Modifier
                .constrainAs(ref = amountValue) {
                    this.end.linkTo(anchor = parent.end, margin = APP_PADDING_NORMAL.dp)
                    this.centerVerticallyTo(other = categoryValue)
                }
                .nothing(),
            text = item?.amount?.value?.format2f() ?: "---",
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onSurface,
            ),
            textAlign = TextAlign.Start,
        )

        val targetPercentForViewAnim by animateFloatAsState(
            targetValue = item?.percentForView ?: 0f,
            label = "",
        )
        LinearProgressIndicator(
            modifier = Modifier
                .constrainAs(ref = percentView) {
                    this.width = Dimension.fillToConstraints
                    this.height = Dimension.value(6.dp)
                    this.start.linkTo(anchor = categoryValue.start, margin = 0.dp)
                    this.end.linkTo(anchor = parent.end, margin = APP_PADDING_NORMAL.dp)
                    this.top.linkTo(anchor = categoryValue.bottom, margin = 2.dp)
                }
                .circleClip()
                .nothing(),
            progress = targetPercentForViewAnim,
            color = MaterialTheme.colorScheme.primary.copy(
                alpha = 0.8f,
            ),
        )

        Text(
            modifier = Modifier
                .constrainAs(ref = percentValue) {
                    this.start.linkTo(anchor = icon.end, margin = APP_PADDING_NORMAL.dp)
                    this.top.linkTo(anchor = percentView.bottom, margin = 2.dp)
                    this.bottom.linkTo(anchor = parent.bottom, margin = APP_PADDING_NORMAL.dp)
                }
                .nothing(),
            text = "${(item?.percent?.times(100) ?: 0f).format2f()}%",
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.secondary,
            ),
            textAlign = TextAlign.Start,
        )

    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun StatisticsLabelItemView(
    modifier: Modifier = Modifier,
    item: StatisticsLabelItemUseCaseDto?,
) {
    ConstraintLayout(
        modifier = modifier,
    ) {
        val (
            categoryValue,
            amountValue, percentValue,
            percentForViewValue,
            percentView,
        ) = createRefs()

        Text(
            modifier = Modifier
                .constrainAs(ref = categoryValue) {
                    this.start.linkTo(anchor = parent.start, margin = APP_PADDING_NORMAL.dp)
                    this.top.linkTo(anchor = parent.top, margin = 0.dp)
                }
                .nothing(),
            text = item?.labelName?.contentWithComposable() ?: "",
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface,
            ),
            textAlign = TextAlign.Start,
        )

        Text(
            modifier = Modifier
                .constrainAs(ref = amountValue) {
                    this.end.linkTo(anchor = parent.end, margin = APP_PADDING_NORMAL.dp)
                    this.centerVerticallyTo(other = categoryValue)
                }
                .nothing(),
            text = item?.amount?.value?.format2f() ?: "---",
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onSurface,
            ),
            textAlign = TextAlign.Start,
        )

        val targetPercentForViewAnim by animateFloatAsState(
            targetValue = item?.percentForView ?: 0f,
            label = "",
        )
        LinearProgressIndicator(
            modifier = Modifier
                .constrainAs(ref = percentView) {
                    this.width = Dimension.fillToConstraints
                    this.height = Dimension.value(6.dp)
                    this.start.linkTo(anchor = categoryValue.start, margin = 0.dp)
                    this.end.linkTo(anchor = parent.end, margin = APP_PADDING_NORMAL.dp)
                    this.top.linkTo(anchor = categoryValue.bottom, margin = 2.dp)
                }
                .circleClip()
                .nothing(),
            progress = targetPercentForViewAnim,
            color = MaterialTheme.colorScheme.primary.copy(
                alpha = 0.8f,
            ),
        )

        Text(
            modifier = Modifier
                .constrainAs(ref = percentValue) {
                    this.start.linkTo(anchor = parent.start, margin = APP_PADDING_NORMAL.dp)
                    this.top.linkTo(anchor = percentView.bottom, margin = 2.dp)
                    this.bottom.linkTo(anchor = parent.bottom, margin = APP_PADDING_NORMAL.dp)
                }
                .nothing(),
            text = "${(item?.percent?.times(100) ?: 0f).format2f()}%",
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.secondary,
            ),
            textAlign = TextAlign.Start,
        )

    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun StatisticsPageView(
    vm: StatisticsViewModel,
    type: StatisticsUseCase.StatisticsType,
    amountStatistics: MoneyYuan?,
    amountDayAverageStatistics: MoneyYuan?,
    categoryStatisticsList: List<StatisticsCategoryItemUseCaseDto>,
    labelStatisticsList: List<StatisticsLabelItemUseCaseDto>,
    tendencyChatVo: TendencyChatVo?,
    categoryPieChartVo: PieChartVo?,
    labelPieChartVo: PieChartVo?,
) {
    val context = LocalContext.current
    val categoryType by vm.categoryTypeStateOb.collectAsState(
        initial = StatisticsUseCase.CategoryType.Big,
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
            .nothing(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
                .nothing(),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            ConstraintLayout(
                modifier = Modifier
                    .weight(weight = 1f, fill = true)
                    .wrapContentHeight()
                    .clip(
                        shape = MaterialTheme.shapes.medium,
                    )
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                    )
                    .nothing(),
            ) {
                val (iconAmount, amountName, amountValue) = createRefs()

                Icon(
                    modifier = Modifier
                        .constrainAs(ref = iconAmount) {
                            this.start.linkTo(
                                anchor = parent.start,
                                margin = APP_PADDING_NORMAL.dp
                            )
                            this.centerVerticallyTo(other = amountName)
                        }
                        .size(size = 16.dp)
                        .nothing(),
                    painter = painterResource(id = com.xiaojinzi.tally.lib.res.R.drawable.res_expenses1),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                )

                Text(
                    modifier = Modifier
                        .constrainAs(ref = amountName) {
                            this.start.linkTo(
                                anchor = iconAmount.end,
                                margin = APP_PADDING_SMALL.dp
                            )
                            this.top.linkTo(
                                anchor = parent.top,
                                margin = APP_PADDING_SMALL.dp
                            )
                        }
                        .nothing(),
                    text = when (type) {
                        StatisticsUseCase.StatisticsType.Spending -> "支出金额"
                        StatisticsUseCase.StatisticsType.Income -> "收入金额"
                    },
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface,
                    ),
                    textAlign = TextAlign.Start,
                )

                Text(
                    modifier = Modifier
                        .constrainAs(ref = amountValue) {
                            this.top.linkTo(
                                anchor = amountName.bottom,
                                margin = APP_PADDING_NORMAL.dp
                            )
                            this.bottom.linkTo(
                                anchor = parent.bottom,
                                margin = APP_PADDING_NORMAL.dp
                            )
                            this.start.linkTo(
                                anchor = parent.start,
                                margin = APP_PADDING_NORMAL.dp
                            )
                        }
                        .nothing(),
                    text = amountStatistics?.value?.format2f() ?: "---",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.6f,
                        ),
                    ),
                    textAlign = TextAlign.Start,
                )

            }

            AppWidthSpace()

            ConstraintLayout(
                modifier = Modifier
                    .weight(weight = 1f, fill = true)
                    .wrapContentHeight()
                    .clip(
                        shape = MaterialTheme.shapes.medium,
                    )
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                    )
                    .nothing(),
            ) {
                val (iconAmount, amountName, amountValue) = createRefs()

                Icon(
                    modifier = Modifier
                        .constrainAs(ref = iconAmount) {
                            this.start.linkTo(
                                anchor = parent.start,
                                margin = APP_PADDING_NORMAL.dp
                            )
                            this.centerVerticallyTo(other = amountName)
                        }
                        .size(size = 16.dp)
                        .nothing(),
                    painter = painterResource(id = com.xiaojinzi.tally.lib.res.R.drawable.res_average1),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                )

                Text(
                    modifier = Modifier
                        .constrainAs(ref = amountName) {
                            this.start.linkTo(
                                anchor = iconAmount.end,
                                margin = APP_PADDING_SMALL.dp
                            )
                            this.top.linkTo(
                                anchor = parent.top,
                                margin = APP_PADDING_SMALL.dp
                            )
                        }
                        .nothing(),
                    text = when (type) {
                        StatisticsUseCase.StatisticsType.Spending -> "日均支出"
                        StatisticsUseCase.StatisticsType.Income -> "日均收入"
                    },
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface,
                    ),
                    textAlign = TextAlign.Start,
                )

                Text(
                    modifier = Modifier
                        .constrainAs(ref = amountValue) {
                            this.top.linkTo(
                                anchor = amountName.bottom,
                                margin = APP_PADDING_NORMAL.dp
                            )
                            this.bottom.linkTo(
                                anchor = parent.bottom,
                                margin = APP_PADDING_NORMAL.dp
                            )
                            this.start.linkTo(
                                anchor = parent.start,
                                margin = APP_PADDING_NORMAL.dp
                            )
                        }
                        .nothing(),
                    text = amountDayAverageStatistics?.value?.format2f() ?: "---",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.6f,
                        ),
                    ),
                    textAlign = TextAlign.Start,
                )

            }

        }

        tendencyChatVo?.let { tendencyChatVo1 ->
            val tendencyChatBottomNumberList by vm.tendencyChatBottomNumberListStateOb.collectAsState(
                initial = emptyList()
            )
            AppHeightSpace()
            Column(
                modifier = Modifier
                    .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(
                        shape = MaterialTheme.shapes.medium,
                    )
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                    )
                    .padding(horizontal = APP_PADDING_SMALL.dp, vertical = APP_PADDING_NORMAL.dp)
                    .nothing(),
            ) {
                val items by rememberUpdatedState(newValue = tendencyChatVo1.items)
                var selectedIndex by remember {
                    mutableIntStateOf(value = -1)
                }
                val selectedItem by remember {
                    derivedStateOf {
                        items.getOrNull(index = selectedIndex)
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .nothing(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Spacer(
                        modifier = Modifier
                            .width(4.dp)
                            .height(12.dp)
                            .circleClip()
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                            )
                            .nothing(),
                    )
                    Spacer(
                        modifier = Modifier
                            .width(4.dp)
                            .nothing(),
                    )
                    Text(
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight()
                            .nothing(),
                        text = "收支趋势",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurface,
                        ),
                        textAlign = TextAlign.Start,
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = APP_PADDING_NORMAL.dp)
                            .weight(weight = 1f, fill = true)
                            .wrapContentHeight()
                            .nothing(),
                        text = selectedItem?.timeStr.orEmpty(),
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Normal,
                            color = MaterialTheme.colorScheme.primary.copy(
                                alpha = 0.6f,
                            ),
                        ),
                        textAlign = TextAlign.Start,
                    )
                    selectedItem?.let { selectedItem ->
                        Text(
                            modifier = Modifier
                                .wrapContentSize()
                                .nothing(),
                            text = "金额: ",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onSurface,
                            ),
                            textAlign = TextAlign.Start,
                        )
                        Text(
                            modifier = Modifier
                                .wrapContentSize()
                                .nothing(),
                            text = selectedItem.amount.value.format2f(),
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.primary.copy(
                                    alpha = 0.6f,
                                ),
                            ),
                            textAlign = TextAlign.Start,
                        )
                    }
                }
                AppHeightSpace()
                TendencyView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height = 80.dp)
                        .nothing(),
                    vo = tendencyChatVo1,
                ) { index ->
                    selectedIndex = index
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .nothing(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    tendencyChatBottomNumberList.forEach { item ->
                        Text(
                            modifier = Modifier
                                // .weight(weight = 1f, fill = true)
                                .wrapContentWidth()
                                .wrapContentHeight()
                                .nothing(),
                            text = "$item",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Normal,
                                color = MaterialTheme.colorScheme.onSurface,
                            ),
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }

        categoryPieChartVo?.let {
            AppHeightSpace()
            Column(
                modifier = Modifier
                    .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(
                        shape = MaterialTheme.shapes.medium,
                    )
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                    )
                    .padding(horizontal = APP_PADDING_SMALL.dp, vertical = APP_PADDING_NORMAL.dp)
                    .nothing(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .nothing(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Spacer(
                        modifier = Modifier
                            .width(4.dp)
                            .height(12.dp)
                            .circleClip()
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                            )
                            .nothing(),
                    )
                    Spacer(
                        modifier = Modifier
                            .width(4.dp)
                            .nothing(),
                    )
                    Text(
                        modifier = Modifier
                            .weight(weight = 1f, fill = true)
                            .wrapContentHeight()
                            .nothing(),
                        text = "分类占比",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurface,
                        ),
                        textAlign = TextAlign.Start,
                    )
                    Row(
                        modifier = Modifier
                            .wrapContentSize()
                            .circleClip()
                            .background(
                                color = MaterialTheme.colorScheme.surfaceColorAtElevation(
                                    elevation = 1.dp,
                                )
                            )
                            .nothing(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        StatisticsUseCase.CategoryTypeList.forEach { item ->
                            val isSelected = item == categoryType
                            Text(
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .wrapContentHeight()
                                    .circleClip()
                                    .clickableNoRipple {
                                        vm.addIntent(
                                            intent = StatisticsIntent.CategoryTypeChange(
                                                categoryType = item,
                                            )
                                        )
                                    }
                                    .background(
                                        color = if (isSelected) {
                                            MaterialTheme.colorScheme.primary
                                        } else {
                                            Color.Transparent
                                        }
                                    )
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                                    .nothing(),
                                text = when (item) {
                                    StatisticsUseCase.CategoryType.Big -> "大类"
                                    StatisticsUseCase.CategoryType.Small -> "小类"
                                },
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = if (isSelected) {
                                        MaterialTheme.colorScheme.onPrimary
                                    } else {
                                        MaterialTheme.colorScheme.onSurface
                                    }
                                ),
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                }
                AppHeightSpace()
                PieChartView(
                    modifier = Modifier
                        .wrapContentSize()
                        .nothing(),
                    vo = it,
                )
            }

        }

        AppHeightSpace()

        Column(
            modifier = Modifier
                .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(
                    shape = MaterialTheme.shapes.medium,
                )
                .background(
                    color = MaterialTheme.colorScheme.surface,
                )
                .padding(horizontal = 0.dp, vertical = APP_PADDING_NORMAL.dp)
                .nothing(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            var isShowAllCategory by remember {
                mutableStateOf(value = false)
            }
            if (categoryStatisticsList.isEmpty()) {
                AppCommonEmptyDataView()
            }
            categoryStatisticsList.forEachIndexed { index, item ->
                if (index < StatisticsUseCase.CATEGORY_MIN_COUNT || isShowAllCategory) {
                    StatisticsCategoryItemView(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .clickable {
                                AppRouterCoreApi::class
                                    .routeApi()
                                    .toBillListView(
                                        context = context,
                                        title = item.categoryName,
                                        question = TallyDataSourceSpi.Companion.BillQueryConditionDto(
                                            categoryIdList = item.categoryIdList,
                                        ),
                                    )
                            }
                            .nothing(),
                        item = item,
                    )
                }
            }
            if (categoryStatisticsList.size > StatisticsUseCase.CATEGORY_MIN_COUNT) {
                Text(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .wrapContentSize()
                        .clickableNoRipple {
                            isShowAllCategory = !isShowAllCategory
                        }
                        .padding(
                            horizontal = APP_PADDING_NORMAL.dp,
                            vertical = APP_PADDING_NORMAL.dp
                        )
                        .nothing(),
                    text = if (isShowAllCategory) {
                        "收起"
                    } else {
                        "显示更多"
                    },
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.5f,
                        )
                    ),
                    textAlign = TextAlign.Start,
                )
            }
        }

        labelPieChartVo?.let {
            AppHeightSpace()
            Column(
                modifier = Modifier
                    .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(
                        shape = MaterialTheme.shapes.medium,
                    )
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                    )
                    .padding(horizontal = APP_PADDING_SMALL.dp, vertical = APP_PADDING_NORMAL.dp)
                    .nothing(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    modifier = Modifier
                        .align(
                            alignment = Alignment.CenterHorizontally,
                        )
                        .nothing(),
                    text = "标签占比",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface,
                    ),
                    textAlign = TextAlign.Start,
                )
                AppHeightSpace()
                PieChartView(
                    modifier = Modifier
                        .wrapContentSize()
                        .nothing(),
                    vo = it,
                )
            }
        }

        AppHeightSpace()

        Column(
            modifier = Modifier
                .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(
                    shape = MaterialTheme.shapes.medium,
                )
                .background(
                    color = MaterialTheme.colorScheme.surface,
                )
                .padding(horizontal = 0.dp, vertical = APP_PADDING_NORMAL.dp)
                .nothing(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            var isShowAllLabel by remember {
                mutableStateOf(value = false)
            }
            if (labelStatisticsList.isEmpty()) {
                AppCommonEmptyDataView()
            }
            labelStatisticsList.forEachIndexed { index, item ->
                if (index < StatisticsUseCase.CATEGORY_MIN_COUNT || isShowAllLabel) {
                    StatisticsLabelItemView(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .clickable {
                                AppRouterCoreApi::class
                                    .routeApi()
                                    .toBillListView(
                                        context = context,
                                        title = item.labelName,
                                        question = if (item.billIdListForQuery.isNullOrEmpty()) {
                                            null
                                        } else {
                                            TallyDataSourceSpi.Companion.BillQueryConditionDto(
                                                idList = item.billIdListForQuery,
                                            )
                                        },
                                    )
                            }
                            .nothing(),
                        item = item,
                    )
                }
            }
            if (labelStatisticsList.size > StatisticsUseCase.LABEL_MIN_COUNT) {
                Text(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .wrapContentSize()
                        .clickableNoRipple {
                            isShowAllLabel = !isShowAllLabel
                        }
                        .padding(
                            horizontal = APP_PADDING_NORMAL.dp,
                            vertical = APP_PADDING_NORMAL.dp
                        )
                        .nothing(),
                    text = if (isShowAllLabel) {
                        "收起"
                    } else {
                        "显示更多"
                    },
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.5f,
                        )
                    ),
                    textAlign = TextAlign.Start,
                )
            }
        }

    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun StatisticsView(
    needInit: Boolean? = true,
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val currentBookInfo by AppServices.tallyDataSourceSpi.selectedBookStateOb.collectAsState(initial = null)
    BusinessContentView<StatisticsViewModel>(
        needInit = needInit,
    ) { vm ->

        val timeTypeSelected by vm.timeTypeSelectedStateOb.collectAsState(initial = null)
        val selectYear by vm.timeSelectUseCase.selectedYearStateOb.collectAsState(initial = null)
        val selectMonth by vm.timeSelectUseCase.selectedMonthStateOb.collectAsState(initial = null)
        val tabSelected by vm.tabSelectedStateOb.collectAsState(initial = StatisticsUseCase.Tab.Spending)
        val spendingStatistics by vm.spendingStatisticsStateOb.collectAsState(
            initial = null,
        )
        val incomeStatistics by vm.incomeStatisticsStateOb.collectAsState(
            initial = null,
        )
        val spendingDayAverageStatistics by vm.spendingDayAverageStatisticsStateOb.collectAsState(
            initial = null,
        )
        val incomeDayAverageStatistics by vm.incomeDayAverageStatisticsStateOb.collectAsState(
            initial = null,
        )

        val categorySpendingStatisticsList by vm.allCategorySpendingStatisticsStateOb.collectAsState(
            initial = emptyList()
        )
        val categoryIncomeStatisticsList by vm.allCategoryIncomeStatisticsStateOb.collectAsState(
            initial = emptyList()
        )

        val labelSpendingStatisticsList by vm.allLabelSpendingStatisticsStateOb.collectAsState(
            initial = emptyList()
        )
        val labelIncomeStatisticsList by vm.allLabelIncomeStatisticsStateOb.collectAsState(
            initial = emptyList()
        )

        val tendencyChatSpendingVo by vm.tendencyChatSpendingStatisticsStateObVo.collectAsState(
            initial = null,
        )

        val tendencyChatIncomeVo by vm.tendencyChatIncomeStatisticsStateObVo.collectAsState(
            initial = null,
        )

        val categorySpendingPieChart by vm.categorySpendingPieChartStateObVo.collectAsState(initial = null)
        val categoryIncomePieChart by vm.categoryIncomePieChartStateObVo.collectAsState(initial = null)

        val labelSpendingPieChart by vm.labelSpendingPieChartStateObVo.collectAsState(initial = null)
        val labelIncomePieChart by vm.labelIncomePieChartStateObVo.collectAsState(initial = null)

        val pageState = rememberPagerState {
            StatisticsUseCase.Tabs.size
        }
        LaunchedEffect(key1 = pageState) {
            snapshotFlow { pageState.currentPage }
                .onEach {
                    when (it) {
                        0 -> vm.tabSelectedStateOb.emit(
                            value = StatisticsUseCase.Tab.Spending,
                        )

                        1 -> vm.tabSelectedStateOb.emit(
                            value = StatisticsUseCase.Tab.Income,
                        )
                    }
                }
                .launchIn(scope = this)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = AppBackgroundColor,
                )
                .statusBarsPadding()
                .navigationBarsPadding()
                .nothing(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            // 顶部菜单
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 0.dp, vertical = 0.dp)
                    .nothing(),
            ) {
                Row(
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .clickableNoRipple {
                            AppRouterCoreApi::class
                                .routeApi()
                                .toBookSwitch(
                                    context = context,
                                )
                        }
                        .nothing(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        modifier = Modifier
                            .nothing(),
                        text = currentBookInfo?.name ?: "<无账本>",
                        style = MaterialTheme.typography.titleSmall.copy(
                            color = MaterialTheme.colorScheme.onSurface,
                        ),
                        textAlign = TextAlign.Start,
                    )
                    Image(
                        modifier = Modifier
                            .scale(scale = 0.6f)
                            .size(size = 20.dp)
                            .nothing(),
                        painter = painterResource(id = com.xiaojinzi.tally.lib.res.R.drawable.res_switch1),
                        contentDescription = null,
                    )
                }
                TextButton(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterEnd)
                        .nothing(),
                    onClick = {
                        vm.addIntent(
                            intent = StatisticsIntent.ToBillList(
                                context = context,
                            )
                        )
                    },
                ) {
                    Text(
                        text = when (timeTypeSelected) {
                            StatisticsUseCase.TimeType.Year -> "年账单"
                            StatisticsUseCase.TimeType.Month -> "月账单"
                            else -> "账单"
                        },
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurface,
                        ),
                        textAlign = TextAlign.Start,
                    )
                }
            }

            // 收入支出选择和时间选择
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
                    .nothing(),
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Row(
                    modifier = Modifier
                        .weight(weight = 2f, fill = true)
                        .wrapContentHeight()
                        .circleClip()
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                        )
                        .padding(horizontal = APP_PADDING_SMALL.dp, vertical = 0.dp)
                        .nothing(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    Icon(
                        modifier = Modifier
                            .circleClip()
                            .background(
                                color = AppBackgroundColor,
                            )
                            .clickable {
                                vm.addIntent(
                                    intent = StatisticsIntent.YearOrMonthAdjust(
                                        value = -1,
                                    )
                                )
                            }
                            .padding(4.dp)
                            .size(size = 16.dp)
                            .nothing(),
                        painter = painterResource(id = com.xiaojinzi.tally.lib.res.R.drawable.res_left1),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface.copy(
                            alpha = 0.5f,
                        ),
                    )

                    Text(
                        modifier = Modifier
                            .weight(weight = 1f, fill = true)
                            .wrapContentHeight()
                            .clickableNoRipple {
                                val dateTimeType = when (timeTypeSelected) {
                                    StatisticsUseCase.TimeType.Year -> {
                                        DateTimeType.Year
                                    }

                                    StatisticsUseCase.TimeType.Month -> {
                                        DateTimeType.Month
                                    }

                                    null -> null
                                }
                                dateTimeType?.let {
                                    vm.timeSelectUseCase.addIntent(
                                        intent = TimeSelectUseCase.Intent.DateTimeSelect(
                                            context = context,
                                            dateTimeType = dateTimeType,
                                        )
                                    )
                                }
                            }
                            .padding(horizontal = 0.dp, vertical = 6.dp)
                            .nothing(),
                        text = when (timeTypeSelected) {
                            StatisticsUseCase.TimeType.Month -> {
                                "${selectYear ?: "----"}年${selectMonth?.plus(1) ?: "---"}月"
                            }

                            StatisticsUseCase.TimeType.Year -> {
                                "${selectYear ?: "----"}年"
                            }

                            else -> ""
                        },
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface,
                        ),
                        textAlign = TextAlign.Center,
                    )

                    Icon(
                        modifier = Modifier
                            .circleClip()
                            .background(
                                color = AppBackgroundColor,
                            )
                            .clickable {
                                vm.addIntent(
                                    intent = StatisticsIntent.YearOrMonthAdjust(
                                        value = 1,
                                    )
                                )
                            }
                            .padding(4.dp)
                            .size(size = 16.dp)
                            .nothing(),
                        painter = painterResource(id = com.xiaojinzi.tally.lib.res.R.drawable.res_right1),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface.copy(
                            alpha = 0.5f,
                        ),
                    )

                }

                AppWidthSpace()

                Row(
                    modifier = Modifier
                        .weight(weight = 1f, fill = true)
                        .wrapContentHeight()
                        .circleClip()
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                        )
                        .nothing(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    (0..1).forEach { index ->
                        val isSelected = pageState.currentPage == index
                        Text(
                            modifier = Modifier
                                .weight(weight = 1f, fill = true)
                                .wrapContentHeight()
                                .circleClip()
                                .clickable {
                                    scope.launchIgnoreError {
                                        pageState.animateScrollToPage(
                                            page = index,
                                        )
                                    }
                                }
                                .background(
                                    color = if (isSelected) {
                                        MaterialTheme.colorScheme.primary
                                    } else {
                                        Color.Transparent
                                    }
                                )
                                .padding(horizontal = 0.dp, vertical = 6.dp)
                                .nothing(),
                            text = when (index) {
                                0 -> "支出"
                                1 -> "收入"
                                else -> notSupportError()
                            },
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = if (isSelected) {
                                    MaterialTheme.colorScheme.onPrimary
                                } else {
                                    MaterialTheme.colorScheme.onSurface
                                }
                            ),
                            textAlign = TextAlign.Center,
                        )
                    }

                }

                AppWidthSpace()

                Row(
                    modifier = Modifier
                        .weight(weight = 1f, fill = true)
                        .wrapContentHeight()
                        .circleClip()
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                        )
                        .nothing(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    (0..1).forEach { index ->
                        val isSelected = timeTypeSelected?.index == index
                        Text(
                            modifier = Modifier
                                .weight(weight = 1f, fill = true)
                                .wrapContentHeight()
                                .circleClip()
                                .clickable {
                                    vm.timeTypeSelectedStateOb.value = when (index) {
                                        0 -> StatisticsUseCase.TimeType.Month
                                        1 -> StatisticsUseCase.TimeType.Year
                                        else -> notSupportError()
                                    }
                                }
                                .background(
                                    color = if (isSelected) {
                                        MaterialTheme.colorScheme.primary
                                    } else {
                                        Color.Transparent
                                    }
                                )
                                .padding(horizontal = 0.dp, vertical = 6.dp)
                                .nothing(),
                            text = when (index) {
                                0 -> "月"
                                1 -> "年"
                                else -> notSupportError()
                            },
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = if (isSelected) {
                                    MaterialTheme.colorScheme.onPrimary
                                } else {
                                    MaterialTheme.colorScheme.onSurface
                                }
                            ),
                            textAlign = TextAlign.Center,
                        )
                    }

                }

            }

            AppHeightSpace()

            HorizontalPager(
                modifier = Modifier
                    .fillMaxSize()
                    .nothing(),
                state = pageState,
            ) { pageIndex ->
                when (pageIndex) {
                    0 -> StatisticsPageView(
                        vm = vm,
                        type = StatisticsUseCase.StatisticsType.Spending,
                        amountStatistics = spendingStatistics,
                        amountDayAverageStatistics = spendingDayAverageStatistics,
                        categoryStatisticsList = categorySpendingStatisticsList,
                        labelStatisticsList = labelSpendingStatisticsList,
                        tendencyChatVo = tendencyChatSpendingVo,
                        categoryPieChartVo = categorySpendingPieChart,
                        labelPieChartVo = labelSpendingPieChart,
                    )

                    1 -> StatisticsPageView(
                        vm = vm,
                        type = StatisticsUseCase.StatisticsType.Income,
                        amountStatistics = incomeStatistics,
                        amountDayAverageStatistics = incomeDayAverageStatistics,
                        categoryStatisticsList = categoryIncomeStatisticsList,
                        labelStatisticsList = labelIncomeStatisticsList,
                        tendencyChatVo = tendencyChatIncomeVo,
                        categoryPieChartVo = categoryIncomePieChart,
                        labelPieChartVo = labelIncomePieChart,
                    )
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
fun StatisticsViewWrap() {
    StatisticsView()
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun StatisticsViewPreview() {
    StatisticsView(
        needInit = false,
    )
}