package com.xiaojinzi.tally.module.main.module.main.calendar.view

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xiaojinzi.component.impl.routeApi
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.compose.GridView
import com.xiaojinzi.support.compose.util.clickPlaceholder
import com.xiaojinzi.support.compose.util.clickableNoRipple
import com.xiaojinzi.support.ktx.NormalMutableSharedFlow
import com.xiaojinzi.support.ktx.format2f
import com.xiaojinzi.support.ktx.getDayInterval
import com.xiaojinzi.support.ktx.getDayOfMonth
import com.xiaojinzi.support.ktx.getMonthByTimeStamp
import com.xiaojinzi.support.ktx.getYearByTimeStamp
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.support.ktx.toStringItemDto
import com.xiaojinzi.tally.lib.res.model.support.DateTimeType
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_NORMAL
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_SMALL
import com.xiaojinzi.tally.lib.res.ui.AppBackgroundColor
import com.xiaojinzi.tally.lib.res.ui.AppHeightSpace
import com.xiaojinzi.tally.lib.res.ui.AppWidthSpace
import com.xiaojinzi.tally.module.base.module.common_bill_list.view.CommonBillListView
import com.xiaojinzi.tally.module.base.spi.TallyDataSourceSpi
import com.xiaojinzi.tally.module.base.support.AppRouterCoreApi
import com.xiaojinzi.tally.module.base.support.AppServices
import com.xiaojinzi.tally.module.base.usecase.TimeSelectUseCase
import com.xiaojinzi.tally.module.base.view.compose.AppCommonVipButton
import com.xiaojinzi.tally.module.main.module.main.calendar.domain.CalendarUseCase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.util.Calendar

private val TodayClickEventOb = NormalMutableSharedFlow<Unit>()

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun CalendarView(
    needInit: Boolean? = null,
) {
    val context = LocalContext.current
    val bookSelected by AppServices.tallyDataSourceSpi.selectedBookStateOb.collectAsState(initial = null)
    val isVip by AppServices.userSpi.isVipStateOb.collectAsState(initial = false)
    BusinessContentView<CalendarViewModel>(
        needInit = needInit,
    ) { vm ->
        val monthPageListData by vm.monthPageListDataStateOb.collectAsState(initial = null)
        val pageYear by vm.timeSelectUseCase.selectedYearStateOb.collectAsState(
            initial = null
        )
        val pageMonth by vm.timeSelectUseCase.selectedMonthStateOb.collectAsState(
            initial = null
        )
        var selectedYear by remember {
            mutableIntStateOf(
                value = getYearByTimeStamp(
                    timeStamp = System.currentTimeMillis(),
                )
            )
        }
        var selectedMonth by remember {
            mutableIntStateOf(
                value = getMonthByTimeStamp(
                    timeStamp = System.currentTimeMillis(),
                )
            )
        }
        var selectedDayOfMonth by remember {
            mutableIntStateOf(
                value = getDayOfMonth(
                    timeStamp = System.currentTimeMillis(),
                )
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = AppBackgroundColor,
                )
                .nothing(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .statusBarsPadding()
                    .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = APP_PADDING_NORMAL.dp)
                    .nothing(),
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Text(
                    modifier = Modifier
                        .wrapContentSize()
                        .clickableNoRipple {
                            vm.timeSelectUseCase.addIntent(
                                intent = TimeSelectUseCase.Intent.DateTimeSelect(
                                    context = context,
                                    dateTimeType = DateTimeType.Month,
                                )
                            )
                        }
                        .nothing(),
                    text = "${pageYear}年${pageMonth?.plus(1)}月",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface,
                    ),
                    textAlign = TextAlign.Start,
                )

                AppWidthSpace()

                Icon(
                    modifier = Modifier
                        .clickableNoRipple {
                            TodayClickEventOb.add(
                                value = Unit,
                            )
                        }
                        .padding(horizontal = 4.dp, vertical = 4.dp)
                        .size(size = 16.dp)
                        .nothing(),
                    painter = painterResource(id = com.xiaojinzi.tally.lib.res.R.drawable.res_today1),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                )

            }

            AppHeightSpace()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .nothing(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                CalendarUseCase.CalendarTitleList.forEach { item ->
                    Text(
                        modifier = Modifier
                            .weight(weight = 1f, fill = true)
                            .wrapContentHeight()
                            .nothing(),
                        text = item,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurface,
                        ),
                        textAlign = TextAlign.Center,
                    )
                }
            }

            AppHeightSpace()

            monthPageListData?.let { monthPageListData_ ->
                if (monthPageListData_.monthPageList.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .nothing(),
                        contentAlignment = Alignment.TopCenter,
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .nothing(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            val pageState = rememberPagerState(
                                initialPage = monthPageListData_.defaultMonthIndex,
                            ) {
                                monthPageListData_.monthPageList.size
                            }
                            LaunchedEffect(key1 = pageState) {
                                snapshotFlow { pageState.currentPage }
                                    .map {
                                        monthPageListData_.monthPageList.getOrNull(
                                            index = it,
                                        )
                                    }
                                    .filterNotNull()
                                    .onEach {
                                        vm.timeSelectUseCase.addIntent(
                                            intent = TimeSelectUseCase.Intent.YearAndMonthSet(
                                                year = it.year,
                                                month = it.month,
                                            )
                                        )
                                    }
                                    .launchIn(scope = this)
                                // 同步用户选择结果和 ViewPager
                                snapshotFlow { pageYear to pageMonth }
                                    .map { pairItem ->
                                        monthPageListData_
                                            .monthPageList
                                            .indexOfFirst {
                                                it.year == pairItem.first && it.month == pairItem.second
                                            }
                                    }
                                    .filter { it > -1 }
                                    .onEach {
                                        if (pageState.currentPage != it) {
                                            pageState.scrollToPage(
                                                page = it,
                                            )
                                        }
                                    }
                                    .launchIn(scope = this)
                                TodayClickEventOb
                                    .onEach {
                                        val currentTime = System.currentTimeMillis()
                                        val yearNow = getYearByTimeStamp(
                                            timeStamp = currentTime,
                                        )
                                        val monthNow = getMonthByTimeStamp(
                                            timeStamp = currentTime,
                                        )
                                        val dayOfMonthNow = getDayOfMonth(
                                            timeStamp = currentTime,
                                        )
                                        selectedYear = yearNow
                                        selectedMonth = monthNow
                                        selectedDayOfMonth = dayOfMonthNow
                                        pageState.scrollToPage(
                                            page = monthPageListData_.defaultMonthIndex,
                                        )
                                        vm.timeSelectUseCase.addIntent(
                                            intent = TimeSelectUseCase.Intent.ResetTime,
                                        )
                                    }
                                    .launchIn(scope = this)
                            }
                            HorizontalPager(
                                modifier = Modifier
                                    .animateContentSize()
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .nothing(),
                                state = pageState,
                                beyondViewportPageCount = 1,
                                verticalAlignment = Alignment.Top,
                            ) { pageIndex ->
                                monthPageListData_.monthPageList.getOrNull(
                                    index = pageIndex,
                                )?.let { monthPageListItemData ->
                                    val dayInMonthItemDataList by vm.subscribeMonthDayList(
                                        year = monthPageListItemData.year,
                                        month = monthPageListItemData.month,
                                    ).collectAsState(initial = emptyList())
                                    GridView(
                                        items = dayInMonthItemDataList,
                                        columnNumber = 7,
                                        verticalSpace = APP_PADDING_SMALL.dp,
                                    ) { item ->
                                        item?.let {
                                            val isSelected =
                                                (item.year == selectedYear && item.month == selectedMonth && item.dayOfMonth == selectedDayOfMonth)
                                            Column(
                                                modifier = Modifier
                                                    .align(alignment = Alignment.Center)
                                                    .padding(
                                                        horizontal = 4.dp,
                                                        vertical = 0.dp
                                                    )
                                                    .fillMaxWidth()
                                                    .wrapContentHeight()
                                                    .clip(shape = MaterialTheme.shapes.small)
                                                    .combinedClickable(
                                                        onLongClick = {
                                                            val calendar =
                                                                Calendar.getInstance()
                                                            calendar[Calendar.YEAR] =
                                                                item.year
                                                            calendar[Calendar.MONTH] =
                                                                item.month
                                                            calendar[Calendar.DAY_OF_MONTH] =
                                                                item.dayOfMonth
                                                            AppRouterCoreApi::class
                                                                .routeApi()
                                                                .toBillCrudView(
                                                                    context = context,
                                                                    initTime = calendar.timeInMillis,
                                                                )
                                                        },
                                                    ) {
                                                        selectedYear = item.year
                                                        selectedMonth = item.month
                                                        selectedDayOfMonth = item.dayOfMonth
                                                    }
                                                    .run {
                                                        if (isSelected) {
                                                            this.background(
                                                                color = MaterialTheme.colorScheme.primary,
                                                            )
                                                        } else if (item.isToday) {
                                                            this.background(
                                                                color = MaterialTheme.colorScheme.primary.copy(
                                                                    alpha = 0.6f,
                                                                )
                                                            )
                                                        } else {
                                                            this
                                                        }
                                                    }
                                                    .padding(
                                                        horizontal = 0.dp,
                                                        vertical = 4.dp
                                                    )
                                                    .nothing(),
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                            ) {
                                                Text(
                                                    modifier = Modifier
                                                        .wrapContentSize()
                                                        .nothing(),
                                                    text = "${item.dayOfMonth}",
                                                    style = MaterialTheme.typography.bodyMedium.copy(
                                                        fontWeight = FontWeight.Medium,
                                                        color = if (item.isToday || isSelected) {
                                                            MaterialTheme.colorScheme.onPrimary
                                                        } else {
                                                            MaterialTheme.colorScheme.onSurface
                                                        },
                                                    ),
                                                    textAlign = TextAlign.Center,
                                                )
                                                Text(
                                                    modifier = Modifier
                                                        .wrapContentSize()
                                                        .nothing(),
                                                    text = item.amount.value.format2f(),
                                                    style = MaterialTheme.typography.labelSmall.copy(
                                                        fontSize = MaterialTheme.typography.labelSmall.fontSize * 0.7f,
                                                        fontWeight = FontWeight.Medium,
                                                        color = if (item.isToday || isSelected) {
                                                            MaterialTheme.colorScheme.onPrimary
                                                        } else {
                                                            MaterialTheme.colorScheme.error.copy(
                                                                alpha = 0.5f,
                                                            )
                                                        },
                                                    ),
                                                    textAlign = TextAlign.Center,
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (!isVip) {
                            Box(
                                modifier = Modifier
                                    .matchParentSize()
                                    .background(
                                        color = MaterialTheme.colorScheme.surface.copy(
                                            alpha = 0.9f,
                                        )
                                    )
                                    .clickPlaceholder()
                                    .nothing(),
                                contentAlignment = Alignment.Center,
                            ) {
                                Column(
                                    modifier = Modifier
                                        .wrapContentSize()
                                        .nothing(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    Text(
                                        text = "日历视图\n开通会员立即解锁",
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            color = MaterialTheme.colorScheme.onSurface.copy(
                                                alpha = 0.8f,
                                            ),
                                        ),
                                        textAlign = TextAlign.Center,
                                    )
                                    Spacer(
                                        modifier = Modifier
                                            .height(height = 8.dp)
                                            .nothing()
                                    )
                                    AppCommonVipButton(
                                        modifier = Modifier
                                            .padding(
                                                horizontal = (APP_PADDING_NORMAL * 3).dp,
                                                vertical = 0.dp
                                            )
                                            .fillMaxWidth()
                                            .wrapContentHeight()
                                            .nothing(),
                                        text = "了解会员权益".toStringItemDto(),
                                        isAlertDialog = false,
                                    )
                                }
                            }
                        }
                    }
                    CommonBillListView(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .nothing(),
                        commonBillListViewUseCase = vm.commonBillListViewUseCase,
                    )
                    LaunchedEffect(key1 = Unit) {
                        snapshotFlow {
                            val calendar = Calendar.getInstance()
                            calendar.timeInMillis = 0
                            calendar[Calendar.HOUR_OF_DAY] = 0
                            calendar[Calendar.YEAR] = selectedYear
                            calendar[Calendar.MONTH] = selectedMonth
                            calendar[Calendar.DAY_OF_MONTH] = selectedDayOfMonth
                            calendar.timeInMillis to bookSelected
                        }.onEach { pair ->
                            val (dayStartTime, dayEndTime) = getDayInterval(
                                timeStamp = pair.first,
                            )
                            vm.billQueryConditionUseCase.queryConditionStateOb.emit(
                                value = TallyDataSourceSpi.Companion.BillQueryConditionDto(
                                    bookIdList = pair.second?.let {
                                        listOf(
                                            it.id
                                        )
                                    } ?: emptyList(),
                                    startTimeInclude = dayStartTime,
                                    endTimeInclude = dayEndTime,
                                ),
                            )
                        }.launchIn(scope = this)
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
fun CalendarViewWrap() {
    CalendarView()
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun CalendarViewPreview() {
    CalendarView(
        needInit = false,
    )
}