package com.xiaojinzi.tally.module.core.module.book_member.view

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.compose.util.clickableNoRipple
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.support.ktx.toStringItemDto
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_NORMAL
import com.xiaojinzi.tally.lib.res.ui.AppBackgroundColor
import com.xiaojinzi.tally.lib.res.ui.AppHeightSpace
import com.xiaojinzi.tally.lib.res.ui.AppWidthSpace
import com.xiaojinzi.tally.module.base.view.compose.AppbarNormalM3
import com.xiaojinzi.tally.module.core.module.book_member.domain.BookMemberIntent
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun BookMemberView(
    needInit: Boolean? = null,
) {
    val context = LocalContext.current
    BusinessContentView<BookMemberViewModel>(
        needInit = needInit,
    ) { vm ->
        val memberList by vm.memberListStateObVo.collectAsState(initial = emptyList())
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = AppBackgroundColor,
                )
                .nothing(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AppHeightSpace()
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .nothing(),
            ) {
                memberList.forEach { item ->
                    item {
                        Row(
                            modifier = Modifier
                                .padding(
                                    horizontal = 0.dp,
                                    vertical = 5.dp
                                )
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .background(
                                    color = MaterialTheme.colorScheme.surface,
                                )
                                .padding(
                                    horizontal = APP_PADDING_NORMAL.dp,
                                    vertical = APP_PADDING_NORMAL.dp
                                )
                                .nothing(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {

                            Text(
                                text = "成员：${item.userInfo.name}",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = MaterialTheme.colorScheme.onSurface,
                                ),
                                textAlign = TextAlign.Start,
                            )

                            AppWidthSpace()

                            if (item.isOwner) {
                                Text(
                                    text = "(拥有者)",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        color = MaterialTheme.colorScheme.outline,
                                    ),
                                    textAlign = TextAlign.Start,
                                )
                            }

                            Spacer(modifier = Modifier.weight(weight = 1f, fill = true))

                            if (item.canDelete) {
                                Text(
                                    modifier = Modifier
                                        .wrapContentSize()
                                        .clickableNoRipple {
                                            vm.addIntent(
                                                intent = BookMemberIntent.ToRemoveOther(
                                                    targetUserId = item.userInfo.id,
                                                )
                                            )
                                        }
                                        .nothing(),
                                    text = "移除",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        color = MaterialTheme.colorScheme.error,
                                    ),
                                    textAlign = TextAlign.Start,
                                )
                            }
                        }
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
fun BookMemberViewWrap() {
    val context = LocalContext.current
    val vm: BookMemberViewModel = viewModel()
    val canShare by vm.canShareStateOb.collectAsState(initial = false)
    Scaffold(
        topBar = {
            AppbarNormalM3(
                title = "账本成员".toStringItemDto(),
                menu1TextValue = if (canShare) {
                    "分享邀请".toStringItemDto()
                } else {
                    null
                },
                menu1ClickListener = {
                    if (canShare) {
                        vm.addIntent(
                            intent = BookMemberIntent.ToInvite(
                                context = context,
                            ),
                        )
                    }
                },
            )
        },
    ) {
        Box(
            modifier = Modifier
                .padding(top = it.calculateTopPadding())
                .nothing(),
        ) {
            BookMemberView()
        }
    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun BookMemberViewPreview() {
    BookMemberView(
        needInit = false,
    )
}