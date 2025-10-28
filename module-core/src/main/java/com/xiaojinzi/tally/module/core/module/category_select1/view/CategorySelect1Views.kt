package com.xiaojinzi.tally.module.core.module.category_select1.view

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.compose.BottomView
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.support.ktx.tryFinishActivity
import com.xiaojinzi.tally.lib.res.model.tally.TallyCategoryDto
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_NORMAL
import com.xiaojinzi.tally.lib.res.ui.AppBackgroundColor
import com.xiaojinzi.tally.lib.res.ui.AppWidthSpace
import com.xiaojinzi.tally.module.core.module.category_select1.domain.CategorySelect1Intent
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun CategorySelect1ItemView(
    modifier: Modifier = Modifier,
    item: TallyCategoryDto?,
    isSelected: Boolean,
    isExpanded: Boolean,
    isSub: Boolean = false,
    onCheckBoxClick: () -> Unit = {},
    onArrowClick: () -> Unit = {},
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (isSub) {
            AppWidthSpace()
        }
        Checkbox(
            checked = isSelected,
            onCheckedChange = {
                onCheckBoxClick.invoke()
            },
        )
        AppWidthSpace()
        Text(
            text = item?.name.orEmpty(),
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurface,
            ),
            textAlign = TextAlign.Start,
        )
        Spacer(modifier = Modifier.weight(weight = 1f, fill = true))
        if (!isSub) {
            IconButton(
                onClick = { onArrowClick.invoke() },
            ) {
                Icon(
                    modifier = Modifier
                        .rotate(
                            degrees = if (isExpanded) {
                                90f
                            } else {
                                0f
                            }
                        )
                        .size(size = 20.dp)
                        .nothing(),
                    painter = rememberVectorPainter(image = Icons.Default.KeyboardArrowRight),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.outline,
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
private fun CategorySelect1View(
    needInit: Boolean? = false,
) {
    val context = LocalContext.current
    BusinessContentView<CategorySelect1ViewModel>(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .nothing(),
        needInit = needInit,
    ) { vm ->
        val categoryList by vm.categoryListStateOb.collectAsState(initial = emptyList())
        val categoryIdSelectSet by vm.categoryIdSelectSetStateOb.collectAsState(initial = emptySet())
        var isExpandedSet by remember {
            mutableStateOf(
                value = emptySet<String>()
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(
                    shape = MaterialTheme.shapes.medium,
                )
                .background(
                    color = AppBackgroundColor,
                )
                .nothing(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(weight = 1f, fill = false)
                    .nothing(),
            ) {
                categoryList.forEach { parentItem ->
                    val isExpanded = isExpandedSet.contains(
                        element = parentItem.first.id,
                    )
                    item {
                        CategorySelect1ItemView(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
                                .nothing(),
                            item = parentItem.first,
                            isSelected = categoryIdSelectSet.contains(
                                element = parentItem.first.id,
                            ),
                            isExpanded = isExpanded,
                            onCheckBoxClick = {
                                vm.addIntent(
                                    intent = CategorySelect1Intent.CheckToggle(
                                        categoryId = parentItem.first.id,
                                    )
                                )
                            },
                            onArrowClick = {
                                isExpandedSet = isExpandedSet.toMutableSet().apply {
                                    if (this.contains(element = parentItem.first.id)) {
                                        remove(element = parentItem.first.id)
                                    } else {
                                        add(element = parentItem.first.id)
                                    }
                                }
                            },
                        )
                    }
                    if (isExpanded) {
                        parentItem.second.forEach { item ->
                            item {
                                CategorySelect1ItemView(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight()
                                        .padding(
                                            horizontal = APP_PADDING_NORMAL.dp,
                                            vertical = 0.dp
                                        )
                                        .nothing(),
                                    item = item,
                                    isSelected = categoryIdSelectSet.contains(
                                        element = item.id,
                                    ),
                                    isExpanded = false,
                                    isSub = true,
                                    onCheckBoxClick = {
                                        vm.addIntent(
                                            intent = CategorySelect1Intent.CheckToggle(
                                                categoryId = item.id,
                                            )
                                        )
                                    },
                                    onArrowClick = {
                                    },
                                )
                            }
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .navigationBarsPadding()
                    .padding(horizontal = 0.dp, vertical = 6.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .nothing(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                TextButton(
                    modifier = Modifier
                        .weight(weight = 1f, fill = true)
                        .wrapContentHeight()
                        .nothing(),
                    onClick = {
                        context.tryFinishActivity()
                    },
                ) {
                    Text(
                        text = "取消",
                        textAlign = TextAlign.Start,
                    )
                }
                TextButton(
                    modifier = Modifier
                        .weight(weight = 1f, fill = true)
                        .wrapContentHeight()
                        .nothing(),
                    onClick = {
                        vm.addIntent(
                            intent = CategorySelect1Intent.ReturnData(
                                context = context,
                            )
                        )
                    },
                ) {
                    Text(
                        text = "确定",
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
fun CategorySelect1ViewWrap() {
    BottomView(
        maxFraction = 0.8f,
    ) {
        CategorySelect1View()
    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun CategorySelect1ViewPreview() {
    CategorySelect1View(
        needInit = false,
    )
}