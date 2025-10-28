package com.xiaojinzi.tally.module.user.module.user_info.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.bean.StringItemDto
import com.xiaojinzi.support.compose.util.clickableNoRipple
import com.xiaojinzi.support.compose.util.contentWithComposable
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.support.ktx.toStringItemDto
import com.xiaojinzi.tally.lib.res.model.support.LocalImageItemDto
import com.xiaojinzi.tally.lib.res.model.support.rememberPainter
import com.xiaojinzi.tally.lib.res.model.support.toLocalImageItemDto
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_LARGE
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_NORMAL
import com.xiaojinzi.tally.lib.res.ui.AppBackgroundColor
import com.xiaojinzi.tally.lib.res.ui.AppHeightSpace
import com.xiaojinzi.tally.module.base.support.AppServices
import com.xiaojinzi.tally.module.base.view.compose.AppbarNormalM3
import kotlinx.coroutines.InternalCoroutinesApi

@Composable
private fun UserInfoActionView1(
    @SuppressLint("ModifierParameter")
    modifier: Modifier = Modifier
        .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 4.dp)
        .fillMaxWidth()
        .wrapContentHeight()
        .clip(
            shape = MaterialTheme.shapes.small,
        )
        .background(
            color = MaterialTheme.colorScheme.surface,
        )
        .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = APP_PADDING_LARGE.dp)
        .nothing(),
    title: StringItemDto,
    value: StringItemDto? = null,
    valueEndIcon: LocalImageItemDto? = null,
    onValueEndIconClick: () -> Unit = {},
    canEdit: Boolean = false,
    onclick: () -> Unit = {},
) {

    Row(
        modifier = Modifier
            .clickable(
                enabled = canEdit,
            ) {
                onclick.invoke()
            }
            .then(other = modifier),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title.contentWithComposable(),
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurface,
            ),
            textAlign = TextAlign.Start,
        )
        Spacer(modifier = Modifier.weight(weight = 1f, fill = true))
        Text(
            text = value?.contentWithComposable().orEmpty(),
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.onSurface.copy(
                    alpha = 0.8f,
                ),
            ),
            textAlign = TextAlign.Start,
        )
        valueEndIcon?.let {
            Image(
                modifier = Modifier
                    .clickableNoRipple {
                        onValueEndIconClick.invoke()
                    }
                    .padding(4.dp)
                    .size(size = 20.dp)
                    .nothing(),
                painter = valueEndIcon.rememberPainter(),
                contentDescription = null,
            )
        }
        if (canEdit) {
            Icon(
                modifier = Modifier
                    .size(size = 18.dp)
                    .nothing(),
                painter = rememberVectorPainter(image = Icons.Rounded.KeyboardArrowRight),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
            )
        }
    }

}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun UserInfoView(
    needInit: Boolean? = false,
) {
    val context = LocalContext.current
    val userInfo by AppServices.userSpi.userInfoStateOb.collectAsState(initial = null)
    BusinessContentView<UserInfoViewModel>(
        needInit = needInit,
    ) { vm ->
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

            UserInfoActionView1(
                title = "ID".toStringItemDto(),
                value = userInfo?.id?.toStringItemDto(),
                valueEndIcon = com.xiaojinzi.tally.lib.res.R.drawable.res_copy1.toLocalImageItemDto(),
                onValueEndIconClick = {
                    AppServices.systemSpi?.copyToClipboard(
                        content = userInfo?.id.orEmpty(),
                    )
                    Toast.makeText(context, "复制 ID 成功", Toast.LENGTH_SHORT).show()
                },
            )

            UserInfoActionView1(
                title = "昵称".toStringItemDto(),
                value = userInfo?.name?.toStringItemDto(),
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
fun UserInfoViewWrap() {
    Scaffold(
        topBar = {
            AppbarNormalM3(
                title = "账号信息".toStringItemDto(),
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(top = it.calculateTopPadding())
                .nothing(),
        ) {
            UserInfoView()
        }
    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun UserInfoViewPreview() {
    UserInfoView(
        needInit = false,
    )
}