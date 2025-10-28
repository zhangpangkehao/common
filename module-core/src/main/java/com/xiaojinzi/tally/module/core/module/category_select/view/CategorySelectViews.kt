package com.xiaojinzi.tally.module.core.module.category_select.view

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.compose.BottomView
import com.xiaojinzi.support.ktx.launchIgnoreError
import com.xiaojinzi.support.ktx.notSupportError
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.tally.lib.res.model.tally.TallyCategoryDto
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_NORMAL
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_SMALL
import com.xiaojinzi.tally.lib.res.ui.AppHeightSpace
import com.xiaojinzi.tally.lib.res.ui.NoInteractionSource
import com.xiaojinzi.tally.module.core.module.bill_crud.view.BillCrudCategorySelectView
import com.xiaojinzi.tally.module.core.module.category_select.domain.CategorySelectIntent
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun CategorySelectView(
    needInit: Boolean? = false,
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    BusinessContentView<CategorySelectViewModel>(
        needInit = needInit,
    ) { vm ->
        val categorySpendingMap by vm.categorySpendingMapStateObVo.collectAsState(
            initial = emptyMap(),
        )
        val categoryIncomeMap by vm.categoryIncomeMapStateObVo.collectAsState(
            initial = emptyMap(),
        )
        val categorySpendingGroupList by vm.spendingCategoryGroupListStateObVo.collectAsState(
            initial = emptyList(),
        )
        val categoryIncomeGroupList by vm.incomeCategoryGroupListStateObVo.collectAsState(
            initial = emptyList(),
        )
        val categorySpendingGroupHasSublistMap by vm.spendingCategoryGroupHasSublistMapStateVo.collectAsState(
            initial = emptyMap(),
        )
        val categoryIncomeGroupHasSublistMap by vm.incomeCategoryGroupHasSublistMapStateVo.collectAsState(
            initial = emptyMap(),
        )
        val categoryGroupSelected by vm.categoryGroupSelectedStateObVo.collectAsState(
            initial = null,
        )
        val categoryItemSelected by vm.categoryItemSelectedStateObVo.collectAsState(
            initial = null
        )
        val subSpendingCategoryList = categorySpendingMap[categoryGroupSelected]
        val subIncomeCategoryList = categoryIncomeMap[categoryGroupSelected]
        val categoryPageState = rememberPagerState {
            2
        }
        BottomView(
            maxFraction = 0.6f,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(
                        shape = MaterialTheme.shapes.small,
                    )
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                    )
                    .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = APP_PADDING_NORMAL.dp)
                    .navigationBarsPadding()
                    .nothing(),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 0.dp, vertical = APP_PADDING_NORMAL.dp)
                        .nothing(),
                ) {
                    TabRow(
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight()
                            .nothing(),
                        selectedTabIndex = 0,
                        indicator = @Composable { tabPositions ->
                            if (categoryPageState.currentPage < tabPositions.size) {
                                TabRowDefaults.Indicator(
                                    modifier = Modifier
                                        .tabIndicatorOffset(tabPositions[categoryPageState.currentPage])
                                        .width(16.dp)
                                        .requiredWidth(16.dp)
                                        .nothing()
                                )
                            }
                        },
                        divider = {
                        },
                    ) {
                        (0..1).forEach { index ->
                            val isSelected = categoryPageState.currentPage == index
                            Tab(
                                selected = isSelected,
                                interactionSource = NoInteractionSource,
                                onClick = {
                                    scope.launchIgnoreError {
                                        categoryPageState.animateScrollToPage(
                                            page = index,
                                        )
                                    }
                                },
                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(bottom = APP_PADDING_SMALL.dp)
                                        .nothing(),
                                    text = when (index) {
                                        0 -> "支出"
                                        1 -> "收入"
                                        else -> notSupportError()
                                    },
                                    textAlign = TextAlign.Start,
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        color = if (isSelected) {
                                            MaterialTheme.colorScheme.primary
                                        } else {
                                            MaterialTheme.colorScheme.inversePrimary
                                        },
                                    ),
                                )
                            }
                        }
                    }
                }
                HorizontalPager(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(weight = 1f, fill = true)
                        .nothing(),
                    state = categoryPageState,
                    beyondViewportPageCount = 1,
                    verticalAlignment = Alignment.Top,
                ) { pageIndex ->
                    when (pageIndex) {
                        0, 1 -> {
                            val targetCategoryGroupList = when (pageIndex) {
                                0 -> categorySpendingGroupList
                                1 -> categoryIncomeGroupList
                                else -> notSupportError()
                            }
                            val targetSubCategoryGroupList = when (pageIndex) {
                                0 -> subSpendingCategoryList
                                1 -> subIncomeCategoryList
                                else -> notSupportError()
                            }
                            val targetCategoryGroupHasSublistMap = when (pageIndex) {
                                0 -> categorySpendingGroupHasSublistMap
                                1 -> categoryIncomeGroupHasSublistMap
                                else -> notSupportError()
                            }
                            BillCrudCategorySelectView(
                                modifier = Modifier
                                    .animateContentSize()
                                    .padding(horizontal = 0.dp, vertical = 0.dp)
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .verticalScroll(
                                        state = rememberScrollState(),
                                    )
                                    .nothing(),
                                targetCategoryGroupList = targetCategoryGroupList,
                                categoryGroupSelected = categoryGroupSelected,
                                targetSubCategoryGroupList = targetSubCategoryGroupList,
                                categoryItemSelected = categoryItemSelected,
                                categoryType = when (pageIndex) {
                                    0 -> TallyCategoryDto.Companion.TallyCategoryType.SPENDING
                                    1 -> TallyCategoryDto.Companion.TallyCategoryType.INCOME
                                    else -> null
                                },
                                targetCategoryGroupHasSublistMap = targetCategoryGroupHasSublistMap,
                                itemNullClick = {
                                    vm.addIntent(
                                        intent = CategorySelectIntent.CategorySetNull,
                                    )
                                },
                                itemGroupClick = { categoryId ->
                                    vm.addIntent(
                                        intent = CategorySelectIntent.CategoryGroupSelect(
                                            id = categoryId,
                                        )
                                    )
                                },
                                itemClick = { categoryId ->
                                    vm.addIntent(
                                        intent = CategorySelectIntent.CategoryItemSelect(
                                            id = categoryId,
                                        )
                                    )
                                },
                            )
                        }
                    }
                }
                AppHeightSpace()
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .nothing(),
                    onClick = {
                        vm.addIntent(
                            intent = CategorySelectIntent.ReturnData(
                                context = context,
                            )
                        )
                    }) {
                    Text(
                        text = "保存",
                        textAlign = TextAlign.Start,
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
fun CategorySelectViewWrap() {
    CategorySelectView()
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun CategorySelectViewPreview() {
    CategorySelectView(
        needInit = false,
    )
}