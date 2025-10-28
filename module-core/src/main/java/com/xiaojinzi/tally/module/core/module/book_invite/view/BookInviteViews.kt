package com.xiaojinzi.tally.module.core.module.book_invite.view

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.ktx.commonTimeFormat2
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.support.ktx.toStringItemDto
import com.xiaojinzi.tally.lib.res.ui.AppBackgroundColor
import com.xiaojinzi.tally.lib.res.ui.AppHeightSpace
import com.xiaojinzi.tally.module.base.view.compose.AppbarNormalM3
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun BookInviteView(
    needInit: Boolean? = null,
) {
    val context = LocalContext.current
    BusinessContentView<BookInviteViewModel>(
        needInit = needInit,
    ) { vm ->
        val qrcodeBitmap by vm.qrcodeBitmapStateOb.collectAsState(initial = null)
        val expiredTime by vm.expiredTimeStateOb.collectAsState(initial = null)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = AppBackgroundColor,
                )
                .nothing(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.weight(weight = 1f, fill = true))
            qrcodeBitmap?.let {
                Image(
                    modifier = Modifier
                        .fillMaxWidth(fraction = 0.6f)
                        .aspectRatio(ratio = 1f)
                        .nothing(),
                    painter = BitmapPainter(
                        image = it.asImageBitmap(),
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                )
            }
            expiredTime?.let {
                AppHeightSpace()
                Text(
                    text = "二维码过期时间：${it.commonTimeFormat2()}",
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                    ),
                    textAlign = TextAlign.Start,
                )
            }
            Spacer(modifier = Modifier.weight(weight = 1f, fill = true))
        }
    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun BookInviteViewWrap() {
    Scaffold(
        topBar = {
            AppbarNormalM3(
                title = "邀请".toStringItemDto(),
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(top = it.calculateTopPadding())
                .nothing(),
        ) {
            BookInviteView()
        }
    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun BookInviteViewPreview() {
    BookInviteView(
        needInit = false,
    )
}