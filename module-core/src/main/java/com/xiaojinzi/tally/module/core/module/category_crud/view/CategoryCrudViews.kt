package com.xiaojinzi.tally.module.core.module.category_crud.view

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.compose.util.circleClip
import com.xiaojinzi.support.compose.util.clickableNoRipple
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.support.ktx.toStringItemDto
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_LARGE
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_NORMAL
import com.xiaojinzi.tally.module.base.view.compose.AppbarNormalM3
import com.xiaojinzi.tally.module.core.module.category_crud.domain.CategoryCrudIntent
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(ExperimentalComposeUiApi::class)
@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun CategoryCrudView(
    needInit: Boolean? = false,
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val softwareKeyboardController = LocalSoftwareKeyboardController.current
    BusinessContentView<CategoryCrudViewModel>(
        needInit = needInit,
    ) { vm ->
        val canDelete by vm.canDeleteStateOb.collectAsState(initial = false)
        val isEdit by vm.isEditStateOb.collectAsState(initial = false)
        val iconRsd by vm.iconRsdStateOb.collectAsState(initial = null)
        val name by vm.nameStateOb.collectAsState(initial = TextFieldValue())
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickableNoRipple {
                    softwareKeyboardController?.hide()
                    focusManager.clearFocus(force = true)
                }
                .nothing(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Icon(
                modifier = Modifier
                    .clickableNoRipple {
                        vm.addIntent(
                            intent = CategoryCrudIntent.IconSelect(
                                context = context,
                            ),
                        )
                    }
                    .padding(horizontal = 0.dp, vertical = 24.dp)
                    .circleClip()
                    .background(
                        color = MaterialTheme.colorScheme.surfaceColorAtElevation(
                            elevation = 1.dp,
                        )
                    )
                    .padding(horizontal = 8.dp, vertical = 8.dp)
                    .size(size = 36.dp)
                    .nothing(),
                painter = iconRsd?.run {
                    painterResource(id = this)
                } ?: ColorPainter(color = Color.Transparent),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
            )

            val focusRequesterForAccountName = remember { FocusRequester() }
            Row(
                modifier = Modifier
                    .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(shape = MaterialTheme.shapes.small)
                    .background(color = MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp))
                    .clickableNoRipple {
                        focusRequesterForAccountName.requestFocus()
                    }
                    .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
                    .nothing(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "类别名称",
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Start,
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .nothing(),
                    contentAlignment = Alignment.CenterEnd,
                ) {
                    if (name.text.isEmpty()) {
                        Text(
                            text = "请输入类别名称",
                            style = MaterialTheme.typography.labelMedium.copy(
                                color = MaterialTheme.colorScheme.secondary,
                            ),
                            textAlign = TextAlign.Start,
                        )
                    }
                    BasicTextField(
                        modifier = Modifier
                            .focusRequester(
                                focusRequester = focusRequesterForAccountName,
                            )
                            .width(intrinsicSize = IntrinsicSize.Min)
                            .widthIn(min = 2.dp)
                            .wrapContentHeight()
                            .padding(horizontal = 0.dp, vertical = APP_PADDING_LARGE.dp)
                            .nothing(),
                        enabled = true,
                        value = name,
                        onValueChange = {
                            vm.nameStateOb.value = it.copy(
                                text = it.text.trim().take(n = 8),
                            )
                        },
                        cursorBrush = SolidColor(
                            value = MaterialTheme.colorScheme.primary,
                        ),
                        textStyle = MaterialTheme.typography.labelMedium.copy(
                            color = MaterialTheme.colorScheme.secondary,
                        ),
                    )
                }
            }
            if (canDelete) {
                if (isEdit) {
                    Spacer(
                        modifier = Modifier
                            .height(height = 16.dp)
                            .nothing()
                    )
                    TextButton(
                        modifier = Modifier
                            .padding(horizontal = APP_PADDING_LARGE.dp, vertical = 0.dp)
                            .fillMaxWidth()
                            .nothing(),
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = MaterialTheme.colorScheme.error,
                            contentColor = MaterialTheme.colorScheme.onError,
                        ),
                        onClick = {
                            vm.addIntent(
                                intent = CategoryCrudIntent.Delete,
                            )
                        },
                    ) {
                        Text(
                            text = "删除",
                            textAlign = TextAlign.Start,
                        )
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
fun CategoryCrudViewWrap() {
    val vm: CategoryCrudViewModel = viewModel()
    val parentId by vm.parentIdStateOb.collectAsState(initial = null)
    val isEdit by vm.isEditStateOb.collectAsState(initial = false)
    Scaffold(
        topBar = {
            AppbarNormalM3(
                title = if (isEdit) {
                    "编辑类别"
                } else {
                    if (parentId.isNullOrEmpty()) {
                        "添加一级类别"
                    } else {
                        "添加二级类别"
                    }
                }.toStringItemDto(),
                menu1TextValue = "提交".toStringItemDto(),
                menu1ClickListener = {
                    vm.addIntent(
                        intent = CategoryCrudIntent.Submit,
                    )
                },
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(top = it.calculateTopPadding())
                .nothing(),
        ) {
            CategoryCrudView()
        }
    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun CategoryCrudViewPreview() {
    CategoryCrudView(
        needInit = false,
    )
}