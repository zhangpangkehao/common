package com.xiaojinzi.tally.module.base.module.bottom_menu_select.view

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xiaojinzi.support.compose.BottomView
import com.xiaojinzi.support.compose.util.contentWithComposable
import com.xiaojinzi.support.ktx.getActivity
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.support.ktx.tryFinishActivity
import com.xiaojinzi.tally.lib.res.model.support.MenuItemLevel
import com.xiaojinzi.tally.lib.res.ui.AppBackgroundColor
import com.xiaojinzi.tally.lib.res.ui.AppDivider
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun BottomMenuSelectView() {
    val context = LocalContext.current
    val vm: BottomMenuSelectViewModel = viewModel()
    val menuList by vm.dataListObservableDto.collectAsState(initial = emptyList())
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .nothing(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 0.dp)
                .fillMaxWidth()
                .weight(weight = 1f, fill = false)
                .clip(
                    shape = RoundedCornerShape(
                        size = 10.dp,
                    )
                )
                .verticalScroll(
                    state = rememberScrollState(),
                )
                .background(color = AppBackgroundColor)
                .nothing(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            menuList.forEachIndexed { index, item ->
                val contentColor = when (item.level) {
                    MenuItemLevel.Danger -> MaterialTheme.colorScheme.error
                    else -> MaterialTheme.colorScheme.onSurface
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                        )
                        .clickable {
                            vm.returnData(
                                context = context,
                                index = index,
                            )
                        }
                        .padding(horizontal = 0.dp, vertical = 18.dp)
                        .nothing(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    item.svgIcon?.run {
                        Icon(
                            modifier = Modifier
                                .size(size = 16.dp)
                                .nothing(),
                            painter = painterResource(id = this),
                            contentDescription = null,
                            tint = contentColor,
                        )
                        Spacer(
                            modifier = Modifier
                                .width(width = 4.dp)
                                .nothing()
                        )
                    }

                    Text(
                        text = item.content.contentWithComposable(),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = contentColor,
                        ),
                        textAlign = TextAlign.Center,
                    )
                }
                AppDivider()
            }
        }
        Text(
            modifier = Modifier
                .navigationBarsPadding()
                .padding(horizontal = 12.dp, vertical = 4.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(
                    shape = RoundedCornerShape(
                        size = 10.dp,
                    )
                )
                .background(
                    color = MaterialTheme.colorScheme.surface,
                )
                .clickable {
                    context
                        .getActivity()
                        ?.setResult(Activity.RESULT_CANCELED)
                    context.tryFinishActivity()
                }
                .padding(horizontal = 0.dp, vertical = 17.dp)
                .navigationBarsPadding()
                .nothing(),
            text = "取消",
            style = MaterialTheme.typography.titleSmall.copy(
                color = MaterialTheme.colorScheme.onSurface,
            ),
            textAlign = TextAlign.Center,
        )
    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun BottomMenuSelectViewWrap() {
    BottomView(
        maxFraction = 0.7f,
    ) {
        BottomMenuSelectView()
    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun BottomMenuSelectViewPreview() {
    BottomMenuSelectView()
}