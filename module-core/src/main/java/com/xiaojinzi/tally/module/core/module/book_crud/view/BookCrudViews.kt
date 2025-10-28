package com.xiaojinzi.tally.module.core.module.book_crud.view

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.compose.util.circleClip
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.support.ktx.toStringItemDto
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_NORMAL
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_SMALL
import com.xiaojinzi.tally.lib.res.ui.AppBackgroundColor
import com.xiaojinzi.tally.lib.res.ui.AppHeightSpace
import com.xiaojinzi.tally.module.base.support.AppServices
import com.xiaojinzi.tally.module.base.view.compose.AppCommonVipButton
import com.xiaojinzi.tally.module.base.view.compose.AppbarNormalM3
import com.xiaojinzi.tally.module.core.module.book_crud.domain.BookCrudIntent
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun BookCrudView(
    needInit: Boolean? = null,
) {
    val context = LocalContext.current
    BusinessContentView<BookCrudViewModel>(
        needInit = needInit,
    ) { vm ->

        val bookName by vm.bookNameStateOb.collectAsState(initial = "")
        val bookTypeList by vm.bookTypeListStateOb.collectAsState(initial = emptyList())
        val bookTypeSelected by vm.bookTypeSelectedStateOb.collectAsState(initial = null)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = AppBackgroundColor,
                )
                .nothing(),
        ) {

            AppHeightSpace()

            Text(
                modifier = Modifier
                    .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
                    .wrapContentSize()
                    .nothing(),
                text = "账本名称",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                ),
                textAlign = TextAlign.Start,
            )

            AppHeightSpace()

            BasicTextField(
                modifier = Modifier
                    .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(
                        shape = MaterialTheme.shapes.small,
                    )
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                    )
                    .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = APP_PADDING_NORMAL.dp)
                    .nothing(),
                value = bookName,
                cursorBrush = SolidColor(
                    value = MaterialTheme.colorScheme.primary,
                ),
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                ),
                onValueChange = {
                    vm.bookNameStateOb.value = it.trim().take(6)
                },
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .nothing(),
                ) {
                    if (bookName.isEmpty()) {
                        Text(
                            modifier = Modifier
                                .align(alignment = Alignment.CenterStart)
                                .nothing(),
                            text = "请输入账本名称",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.onSurface.copy(
                                    alpha = 0.5f,
                                )
                            ),
                            textAlign = TextAlign.Start,
                        )
                    }
                    Text(
                        modifier = Modifier
                            .align(alignment = Alignment.CenterEnd)
                            .nothing(),
                        text = "${bookName.length}/6",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface.copy(
                                alpha = 0.5f,
                            )
                        ),
                        textAlign = TextAlign.Start,
                    )
                    it.invoke()
                }

            }

            AppHeightSpace()

            Text(
                modifier = Modifier
                    .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
                    .wrapContentSize()
                    .nothing(),
                text = "账本类型",
                style = MaterialTheme.typography.titleSmall.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                ),
                textAlign = TextAlign.Start,
            )

            AppHeightSpace()

            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
                    .fillMaxWidth()
                    .weight(weight = 1f, fill = true)
                    .nothing(),
            ) {
                bookTypeList.forEachIndexed { index, item ->
                    val isSelected = item == bookTypeSelected
                    item {
                        ConstraintLayout(
                            modifier = Modifier
                                .padding(horizontal = 0.dp, vertical = APP_PADDING_SMALL.dp)
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .clip(
                                    shape = MaterialTheme.shapes.small,
                                )
                                .run {
                                    if (isSelected) {
                                        this.border(
                                            width = 1.5.dp,
                                            color = MaterialTheme.colorScheme.primary.copy(
                                                alpha = 0.5f,
                                            ),
                                            shape = MaterialTheme.shapes.small,
                                        )
                                    } else {
                                        this
                                    }
                                }
                                .clickable {
                                    vm.addIntent(
                                        intent = BookCrudIntent.ItemSelect(
                                            item = item,
                                        )
                                    )
                                }
                                .background(
                                    color = MaterialTheme.colorScheme.surface,
                                )
                                .padding(bottom = APP_PADDING_SMALL.dp)
                                .nothing(),
                        ) {
                            val (
                                icon, name, desc
                            ) = createRefs()
                            Icon(
                                modifier = Modifier
                                    .constrainAs(ref = icon) {
                                        this.top.linkTo(
                                            anchor = parent.top,
                                            margin = APP_PADDING_NORMAL.dp,
                                        )
                                        this.start.linkTo(
                                            anchor = parent.start,
                                            margin = APP_PADDING_NORMAL.dp
                                        )
                                    }
                                    .circleClip()
                                    .background(
                                        color = MaterialTheme.colorScheme.surfaceColorAtElevation(
                                            elevation = 1.dp,
                                        )
                                    )
                                    .padding(all = 6.dp)
                                    .size(size = 24.dp)
                                    .nothing(),
                                painter = AppServices.iconMappingSpi[item.iconName]?.let {
                                    painterResource(
                                        id = it,
                                    )
                                } ?: ColorPainter(
                                    color = Color.Transparent,
                                ),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurface,
                            )
                            Text(
                                modifier = Modifier
                                    .constrainAs(ref = name) {
                                        this.start.linkTo(
                                            anchor = icon.end,
                                            margin = APP_PADDING_NORMAL.dp
                                        )
                                        this.top.linkTo(
                                            anchor = icon.top,
                                            margin = 4.dp
                                        )
                                    }
                                    .nothing(),
                                text = item.name.orEmpty(),
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = MaterialTheme.colorScheme.onSurface,
                                ),
                                textAlign = TextAlign.Start,
                            )
                            Text(
                                modifier = Modifier
                                    .constrainAs(ref = desc) {
                                        this.start.linkTo(
                                            anchor = name.start,
                                            margin = 0.dp
                                        )
                                        this.top.linkTo(
                                            anchor = name.bottom,
                                            margin = APP_PADDING_NORMAL.dp
                                        )
                                    }
                                    .nothing(),
                                text = item.desc.orEmpty(),
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = MaterialTheme.colorScheme.surfaceVariant,
                                ),
                                textAlign = TextAlign.Start,
                            )
                        }
                    }
                }
            }

            AppCommonVipButton(
                modifier = Modifier
                    .navigationBarsPadding()
                    .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = APP_PADDING_NORMAL.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .nothing(),
                text = "创建".toStringItemDto(),
                onClick = {
                    vm.addIntent(
                        intent = BookCrudIntent.Submit,
                    )
                },
            )

        }
    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun BookCrudViewWrap() {
    Scaffold(
        topBar = {
            AppbarNormalM3(
                title = "创建账本".toStringItemDto(),
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(top = it.calculateTopPadding())
                .nothing(),
        ) {
            BookCrudView()
        }
    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun BookCrudViewPreview() {
    BookCrudView(
        needInit = false,
    )
}