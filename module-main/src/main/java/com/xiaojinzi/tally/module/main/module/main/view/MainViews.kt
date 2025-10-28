package com.xiaojinzi.tally.module.main.module.main.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.xiaojinzi.component.impl.routeApi
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.compose.util.clickPlaceholder
import com.xiaojinzi.support.compose.util.clickableNoRipple
import com.xiaojinzi.support.compose.util.contentWithComposable
import com.xiaojinzi.support.ktx.awaitIgnoreException
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.support.ktx.suspendAction0
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_LARGE
import com.xiaojinzi.tally.lib.res.ui.AppBackgroundColor
import com.xiaojinzi.tally.module.base.support.AppRouterCoreApi
import com.xiaojinzi.tally.module.base.support.AppServices
import com.xiaojinzi.tally.module.base.support.finishAppAllTask
import com.xiaojinzi.tally.module.main.module.main.bill.view.BillView
import com.xiaojinzi.tally.module.main.module.main.calendar.view.CalendarViewWrap
import com.xiaojinzi.tally.module.main.module.main.domain.MainIntent
import com.xiaojinzi.tally.module.main.module.main.domain.MainTabDto
import com.xiaojinzi.tally.module.main.module.main.statistics.view.StatisticsViewWrap
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun MainView(
    needInit: Boolean? = false,
) {
    val context = LocalContext.current
    val isShowedGuide1 by AppServices
        .appConfigSpi
        .isShowedGuide1StateOb
        .collectAsState(
            initial = true,
        )
    val isAiBillFirst by AppServices
        .appConfigSpi
        .isAiBillFirstStateOb
        .collectAsState(
            initial = false
        )
    BusinessContentView<MainViewModel>(
        needInit = needInit,
    ) { vm ->
        val tabList by vm.tabListStateOb.collectAsState(initial = emptyList())
        val tabSelected by vm.tabSelectedStateOb.collectAsState(initial = MainTabDto.Bill)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = AppBackgroundColor,
                )
                .nothing(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(weight = 1f, fill = true)
                    .nothing(),
            ) {
                val pagerState = rememberPagerState {
                    tabList.size
                }
                LaunchedEffect(key1 = pagerState) {
                    vm
                        .tabSelectedStateOb
                        .onEach {
                            suspendAction0 {
                                pagerState.animateScrollToPage(
                                    page = tabList.indexOf(element = it),
                                )
                            }.awaitIgnoreException()
                        }
                        .launchIn(scope = this)
                }
                HorizontalPager(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .nothing(),
                    beyondViewportPageCount = 3,
                    userScrollEnabled = false,
                    state = pagerState,
                ) { pageIndex ->
                    when (tabList[pageIndex]) {

                        MainTabDto.Bill -> {
                            BillView()
                        }

                        MainTabDto.Calendar -> {
                            CalendarViewWrap()
                        }

                        MainTabDto.Assets -> {
                            AppServices
                                .coreSpi
                                ?.AssetsViewShared()
                        }

                        MainTabDto.Statistics -> {
                            StatisticsViewWrap()
                        }

                        MainTabDto.My -> {
                            AppServices
                                .userSpi
                                .MyViewShared()
                        }
                    }
                }
                androidx.compose.animation.AnimatedVisibility(
                    modifier = Modifier
                        .align(alignment = Alignment.BottomEnd)
                        .nothing(),
                    visible = tabSelected in listOf(
                        MainTabDto.Bill,
                    ),
                    enter = scaleIn(),
                    exit = scaleOut(),
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(all = APP_PADDING_LARGE.dp)
                            /*.clip(
                                shape = MaterialTheme.shapes.medium,
                            )*/
                            .shadow(
                                elevation = 10.dp,
                                shape = MaterialTheme.shapes.medium,
                            )
                            .background(
                                color = MaterialTheme.colorScheme.primaryContainer,
                            )
                            .combinedClickable(
                                onLongClick = {
                                    if (isAiBillFirst) {
                                        AppRouterCoreApi::class
                                            .routeApi()
                                            .toBillCrudView(
                                                context = context,
                                            )
                                    } else {
                                        AppRouterCoreApi::class
                                            .routeApi()
                                            .toAiBillChatView(
                                                context = context,
                                            )
                                    }
                                },
                            ) {
                                if (isAiBillFirst) {
                                    AppRouterCoreApi::class
                                        .routeApi()
                                        .toAiBillChatView(
                                            context = context,
                                        )
                                } else {
                                    AppRouterCoreApi::class
                                        .routeApi()
                                        .toBillCrudView(
                                            context = context,
                                        )
                                }
                            }
                            .padding(all = 16.dp)
                            .size(size = 24.dp)
                            .nothing(),
                        painter = if (isAiBillFirst) {
                            painterResource(id = com.xiaojinzi.tally.lib.res.R.drawable.res_ai1)
                        } else {
                            rememberVectorPainter(image = Icons.Sharp.Add)
                        },
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                    )
                    .navigationBarsPadding()
                    .padding(horizontal = 0.dp, vertical = 12.dp)
                    .nothing(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                tabList.forEachIndexed { index, tabItem ->
                    val isSelected = tabSelected == tabItem
                    Column(
                        modifier = Modifier
                            .wrapContentSize()
                            .clickableNoRipple {
                                vm.addIntent(
                                    intent = MainIntent.TabChanged(
                                        value = tabItem,
                                    )
                                )
                            }
                            .nothing(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(size = 18.dp)
                                .nothing(),
                            painter = painterResource(id = tabItem.iconRsd),
                            contentDescription = null,
                            tint = if (isSelected) {
                                MaterialTheme.colorScheme.primary
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
                            text = tabItem.nameStringItem.contentWithComposable(),
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = if (isSelected) {
                                    MaterialTheme.colorScheme.primary
                                } else {
                                    MaterialTheme.colorScheme.onSurface
                                },
                            ),
                            textAlign = TextAlign.Start,
                        )
                    }
                }
            }
        }
        if (!isShowedGuide1) {
            var showIndex by remember {
                mutableIntStateOf(value = 0)
            }
            val showComplete by rememberUpdatedState(newValue = showIndex >= 1)
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = Color.Black.copy(
                            alpha = 0.45f,
                        )
                    )
                    .clickPlaceholder()
                    .navigationBarsPadding()
                    .nothing(),
            ) {
                val (
                    tip1, tip2,
                    arrow1, arrow2,
                    textIKnow,
                ) = createRefs()
                if (showIndex == 0) {
                    Icon(
                        modifier = Modifier
                            .constrainAs(ref = arrow1) {
                                this.end.linkTo(anchor = parent.end, margin = 30.dp)
                                this.bottom.linkTo(
                                    anchor = parent.bottom,
                                    margin = 160.dp,
                                )
                            }
                            .rotate(degrees = 190f)
                            .size(size = 100.dp)
                            .nothing(),
                        painter = painterResource(
                            id = com.xiaojinzi.tally.lib.res.R.drawable.res_guide_line1,
                        ),
                        contentDescription = null,
                        tint = Color.White.copy(
                            alpha = 0.8f,
                        ),
                    )
                    Text(
                        modifier = Modifier
                            .constrainAs(ref = tip1) {
                                this.bottom.linkTo(
                                    anchor = arrow1.top,
                                    margin = 24.dp,
                                )
                                this.start.linkTo(anchor = parent.start, margin = 0.dp)
                                this.end.linkTo(anchor = parent.end, margin = 0.dp)
                            }
                            .wrapContentSize()
                            .nothing(),
                        text = "长按可进入普通或者 AI 记账\n设置中可配置优先的记账方式",
                        fontFamily = FontFamily(Font(com.xiaojinzi.tally.lib.res.R.font.res_font_xdks)),
                        fontWeight = FontWeight.Medium,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = Color.White.copy(
                                alpha = 0.8f,
                            ),
                        ),
                        textAlign = TextAlign.Center,
                    )
                } else if (showIndex == 1) {
                    Icon(
                        modifier = Modifier
                            .constrainAs(ref = arrow2) {
                                this.start.linkTo(anchor = parent.start, margin = 0.dp)
                                this.end.linkTo(anchor = parent.end, margin = 0.dp)
                                this.top.linkTo(
                                    anchor = parent.top,
                                    margin = 260.dp,
                                )
                            }
                            .rotate(degrees = 45f)
                            .size(size = 100.dp)
                            .nothing(),
                        painter = painterResource(
                            id = com.xiaojinzi.tally.lib.res.R.drawable.res_guide_line1,
                        ),
                        contentDescription = null,
                        tint = Color.White.copy(
                            alpha = 0.8f,
                        ),
                    )
                    Text(
                        modifier = Modifier
                            .constrainAs(ref = tip2) {
                                this.top.linkTo(
                                    anchor = arrow2.bottom,
                                    margin = 44.dp,
                                )
                                this.start.linkTo(anchor = parent.start, margin = 0.dp)
                                this.end.linkTo(anchor = parent.end, margin = 0.dp)
                            }
                            .wrapContentSize()
                            .nothing(),
                        text = "卡片左右滑动可切换月份\n点击时间可选择月份",
                        fontFamily = FontFamily(Font(com.xiaojinzi.tally.lib.res.R.font.res_font_xdks)),
                        fontWeight = FontWeight.Medium,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = Color.White.copy(
                                alpha = 0.8f,
                            ),
                        ),
                        textAlign = TextAlign.Center,
                    )
                }

                Text(
                    modifier = Modifier
                        .constrainAs(ref = textIKnow) {
                            this.bottom.linkTo(
                                anchor = parent.bottom,
                                margin = 88.dp,
                            )
                            this.start.linkTo(anchor = parent.start, margin = 0.dp)
                            this.end.linkTo(anchor = parent.end, margin = 0.dp)
                        }
                        .border(
                            width = 1.dp,
                            color = Color.White.copy(
                                alpha = 0.8f,
                            ),
                            shape = MaterialTheme.shapes.small,
                        )
                        .wrapContentSize()
                        .clickable {
                            if (showComplete) {
                                AppServices
                                    .appConfigSpi
                                    .switchShowedGuide1(b = true)
                            } else {
                                showIndex++
                            }
                        }
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                        .nothing(),
                    text = if (showComplete) {
                        "我知道了"
                    } else {
                        "下一步"
                    },
                    fontFamily = FontFamily(Font(com.xiaojinzi.tally.lib.res.R.font.res_font_xdks)),
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White.copy(
                            alpha = 0.8f,
                        ),
                    ),
                    textAlign = TextAlign.Center,
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
fun MainViewWrap() {
    val context = LocalContext.current
    MainView()
    var lastPressBackTime by remember {
        mutableLongStateOf(
            value = 0L
        )
    }
    BackHandler {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastPressBackTime < 1500) {
            // 退出
            finishAppAllTask()
        } else {
            lastPressBackTime = currentTime
            Toast.makeText(
                context,
                "再按一次退出",
                Toast.LENGTH_SHORT,
            ).show()
        }
    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun MainViewPreview() {
    MainView(
        needInit = false,
    )
}