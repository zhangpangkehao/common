package com.xiaojinzi.tally.module.base.module.center_menu_select.view

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xiaojinzi.support.compose.util.contentWithComposable
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
private fun CenterMenuSelectView() {
    val context = LocalContext.current
    val vm: CenterMenuSelectViewModel = viewModel()
    val menuList by vm.dataListObservableDto.collectAsState(initial = emptyList())
    Dialog(
        onDismissRequest = {
            context.tryFinishActivity()
        },
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 0.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(
                    shape = RoundedCornerShape(
                        size = 10.dp,
                    )
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
                            fontWeight = FontWeight.Medium,
                            color = contentColor,
                        ),
                        textAlign = TextAlign.Center,
                    )
                }
                AppDivider()
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
fun CenterMenuSelectViewWrap() {
    CenterMenuSelectView()
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun CenterMenuSelectViewPreview() {
    CenterMenuSelectView()
}