package com.xiaojinzi.tally.module.core.module.icon_select.view

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.compose.util.circleClip
import com.xiaojinzi.support.compose.util.clickableNoRipple
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.support.ktx.toStringItemDto
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_NORMAL
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_SMALL
import com.xiaojinzi.tally.lib.res.ui.AppBackgroundColor
import com.xiaojinzi.tally.lib.res.ui.AppHeightSpace
import com.xiaojinzi.tally.module.base.view.compose.AppbarNormalM3
import com.xiaojinzi.tally.module.core.module.icon_select.domain.IconSelectIntent
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun IconListContentView(
    modifier: Modifier = Modifier,
    vm: IconSelectViewModel = viewModel(),
    onScrollOrClick: () -> Unit = {},
) {
    val iconGroupList by vm.iconGroupListStateOb.collectAsState(initial = emptyList())
    val selectedItem by vm.itemSelectedStateOb.collectAsState(initial = null)
    val state = rememberLazyGridState()
    LaunchedEffect(key1 = state) {
        snapshotFlow { state.isScrollInProgress }
            .filter { it }
            .onEach {
                onScrollOrClick.invoke()
            }.launchIn(scope = this)
    }
    LazyVerticalGrid(
        modifier = modifier,
        state = state,
        columns = GridCells.Fixed(count = 5),
    ) {
        iconGroupList.forEach { groupItem ->
            // 占满五个的 title
            item(
                span = {
                    GridItemSpan(currentLineSpan = 5)
                },
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 0.dp, vertical = APP_PADDING_SMALL.dp)
                        .nothing(),
                    text = groupItem.name,
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                    ),
                    textAlign = TextAlign.Start,
                )
            }
            items(
                items = groupItem.list,
            ) { item ->
                val isSelected = selectedItem == item
                Box(
                    modifier = Modifier
                        .padding(horizontal = 0.dp, vertical = 8.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clickableNoRipple {
                            onScrollOrClick.invoke()
                            vm.addIntent(
                                intent = IconSelectIntent.ItemSelect(
                                    item = item,
                                )
                            )
                        }
                        .nothing(),
                    contentAlignment = Alignment.Center,
                ) {
                    Column(
                        modifier = Modifier
                            .wrapContentSize()
                            .nothing(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Icon(
                            modifier = Modifier
                                .circleClip()
                                .background(
                                    color = if (isSelected) {
                                        MaterialTheme.colorScheme.primary
                                    } else {
                                        AppBackgroundColor
                                    },
                                )
                                .padding(all = 6.dp)
                                .size(size = 20.dp)
                                .nothing(),
                            painter = painterResource(id = item.iconRsd),
                            contentDescription = null,
                            tint = if (isSelected) {
                                MaterialTheme.colorScheme.onPrimary
                            } else {
                                MaterialTheme.colorScheme.onSurface
                            },
                        )
                        Spacer(
                            modifier = Modifier
                                .height(height = 4.dp)
                                .nothing()
                        )
                        Text(
                            text = item.name,
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = MaterialTheme.colorScheme.onSurface,
                            ),
                            textAlign = TextAlign.Start,
                        )
                    }

                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun IconSelectView(
    needInit: Boolean? = false,
) {
    val focusManager = LocalFocusManager.current
    val focusRequesterForSearchKey = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current
    BusinessContentView<IconSelectViewModel>(
        needInit = needInit,
    ) { vm ->
        val searchKey by vm.searchKeyStateOb.collectAsState(initial = "")
        val selectedItem by vm.itemSelectedStateOb.collectAsState(initial = null)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = AppBackgroundColor,
                )
                .nothing(),
            horizontalAlignment = Alignment.Start,
        ) {
            AppHeightSpace()
            Box(
                modifier = Modifier
                    .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .circleClip()
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                    )
                    .clickableNoRipple {
                        focusRequesterForSearchKey.requestFocus()
                        keyboardController?.show()
                    }
                    .padding(horizontal = APP_PADDING_SMALL.dp, vertical = APP_PADDING_SMALL.dp)
                    .nothing(),
                contentAlignment = Alignment.Center,
            ) {
                if (searchKey.isEmpty()) {
                    Text(
                        modifier = Modifier
                            .nothing(),
                        text = "根据关键词搜索",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.outline,
                        ),
                        textAlign = TextAlign.Start,
                    )
                }
                BasicTextField(
                    modifier = Modifier
                        .focusRequester(
                            focusRequester = focusRequesterForSearchKey,
                        )
                        .width(intrinsicSize = IntrinsicSize.Min)
                        .widthIn(min = 2.dp)
                        .wrapContentHeight()
                        .nothing(),
                    value = searchKey,
                    onValueChange = {
                        vm.searchKeyStateOb.value = it.trim()
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done,
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                            focusManager.clearFocus(force = true)
                        },
                    ),
                    cursorBrush = SolidColor(
                        value = MaterialTheme.colorScheme.primary,
                    ),
                    textStyle = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                    ),
                )
            }

            IconListContentView(
                modifier = Modifier
                    .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = APP_PADDING_NORMAL.dp)
                    .fillMaxWidth()
                    .weight(weight = 1f, fill = true)
                    .clip(
                        shape = MaterialTheme.shapes.medium,
                    )
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                    )
                    .nothing(),
                vm = vm,
            ) {
                keyboardController?.hide()
                focusManager.clearFocus(force = true)
            }

            Button(
                modifier = Modifier
                    .navigationBarsPadding()
                    .padding(bottom = APP_PADDING_NORMAL.dp)
                    .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .nothing(),
                enabled = selectedItem != null,
                onClick = {
                    vm.addIntent(
                        intent = IconSelectIntent.Submit(
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

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun IconSelectViewWrap() {
    Scaffold(
        topBar = {
            AppbarNormalM3(
                title = "图标选择".toStringItemDto(),
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(top = it.calculateTopPadding())
                .nothing(),
        ) {
            IconSelectView()
        }
    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun IconSelectViewPreview() {
    IconSelectView(
        needInit = false,
    )
}