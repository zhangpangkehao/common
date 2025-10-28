package com.xiaojinzi.tally.module.core.module.label_select.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.xiaojinzi.component.impl.routeApi
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.compose.util.circleClip
import com.xiaojinzi.support.compose.util.clickableNoRipple
import com.xiaojinzi.support.ktx.getActivity
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.support.ktx.toStringItemDto
import com.xiaojinzi.support.ktx.tryFinishActivity
import com.xiaojinzi.tally.lib.res.model.tally.TallyLabelDto
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_NORMAL
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_SMALL
import com.xiaojinzi.tally.module.base.support.AppRouterCoreApi
import com.xiaojinzi.tally.module.base.view.compose.AppCommonEmptyDataView
import com.xiaojinzi.tally.module.core.module.label_select.domain.LabelSelectIntent
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(ExperimentalLayoutApi::class)
@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun LabelSelectView(
    needInit: Boolean? = false,
    previewLabelList: List<TallyLabelDto> = emptyList(),
) {
    val context = LocalContext.current
    BusinessContentView<LabelSelectViewModel>(
        needInit = needInit,
    ) { vm ->
        val labelList by vm.labelListStateOb.collectAsState(initial = previewLabelList)
        val selectIdSet by vm.selectIdSetStateOb.collectAsState(initial = emptySet())
        Dialog(onDismissRequest = { context.tryFinishActivity() }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(
                        shape = MaterialTheme.shapes.medium,
                    )
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                    )
                    .nothing(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                /*Text(
                    modifier = Modifier
                        .padding(
                            horizontal = APP_PADDING_NORMAL.dp,
                            vertical = APP_PADDING_SMALL.dp
                        )
                        .nothing(),
                    text = "标签选择",
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                    ),
                    textAlign = TextAlign.Start,
                )*/
                if (labelList.isEmpty()) {
                    AppCommonEmptyDataView(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .clickableNoRipple {
                                AppRouterCoreApi::class
                                    .routeApi()
                                    .toLabelCrudView(
                                        context = context,
                                    )
                            }
                            .nothing(),
                        text = "点击去添加标签".toStringItemDto(),
                    )
                } else {
                    Spacer(
                        modifier = Modifier
                            .height(height = 30.dp)
                            .nothing()
                    )
                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 100.dp, max = 200.dp)
                            .padding(horizontal = APP_PADDING_SMALL.dp, vertical = 0.dp)
                            .verticalScroll(state = rememberScrollState())
                            .nothing(),
                        horizontalArrangement = Arrangement.spacedBy(APP_PADDING_SMALL.dp),
                        verticalArrangement = Arrangement.spacedBy(APP_PADDING_SMALL.dp),
                    ) {
                        labelList.forEachIndexed { _, item ->
                            val isSelected = selectIdSet.contains(element = item.id)
                            Text(
                                modifier = Modifier
                                    .wrapContentSize()
                                    .circleClip()
                                    .clickableNoRipple {
                                        vm.addIntent(
                                            intent = LabelSelectIntent.ItemSelect(
                                                id = item.id,
                                            )
                                        )
                                    }
                                    .run {
                                        this.background(
                                            color = if (isSelected) {
                                                MaterialTheme.colorScheme.secondary
                                            } else {
                                                MaterialTheme.colorScheme.surfaceColorAtElevation(
                                                    elevation = 2.dp,
                                                )
                                            }
                                        )
                                    }
                                    .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 6.dp)
                                    .nothing(),
                                text = item.name.orEmpty(),
                                style = MaterialTheme.typography.labelMedium.copy(
                                    color = if (isSelected) {
                                        MaterialTheme.colorScheme.onSecondary
                                    } else {
                                        MaterialTheme.colorScheme.onSurface
                                    }
                                ),
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                }
                if (labelList.isNotEmpty()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .nothing(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            modifier = Modifier
                                .weight(weight = 1f, fill = true)
                                .wrapContentHeight()
                                .clickable {
                                    AppRouterCoreApi::class
                                        .routeApi()
                                        .toLabelCrudView(
                                            context = context,
                                        )
                                }
                                .padding(
                                    horizontal = 0.dp,
                                    vertical = APP_PADDING_NORMAL.dp,
                                )
                                .nothing(),
                            text = "创建标签",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.primary,
                            ),
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            modifier = Modifier
                                .weight(weight = 1f, fill = true)
                                .wrapContentHeight()
                                .clickable {
                                    context
                                        .getActivity()
                                        ?.apply {
                                            this.setResult(
                                                Activity.RESULT_OK,
                                                Intent().apply {
                                                    this.putExtra(
                                                        "data",
                                                        ArrayList(
                                                            selectIdSet,
                                                        ),
                                                    )
                                                }
                                            )
                                            this.finish()
                                        }
                                }
                                .padding(
                                    horizontal = 0.dp,
                                    vertical = APP_PADDING_NORMAL.dp,
                                )
                                .nothing(),
                            text = "完成",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.primary,
                            ),
                            textAlign = TextAlign.Center,
                        )
                    }
                } else {
                    Spacer(
                        modifier = Modifier
                            .height(height = 30.dp)
                            .nothing()
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
fun LabelSelectViewWrap() {
    LabelSelectView()
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun LabelSelectViewPreview() {
    LabelSelectView(
        needInit = false,
        previewLabelList = listOf(
            "123", "22434",
        ).map {
            TallyLabelDto(
                id = it,
                userId = "123",
                bookId = "123",
                name = "标签",
                timeCreate = System.currentTimeMillis(),
                timeModify = System.currentTimeMillis(),
                isDeleted = false,
                isSync = false,
            )
        }
    )
}