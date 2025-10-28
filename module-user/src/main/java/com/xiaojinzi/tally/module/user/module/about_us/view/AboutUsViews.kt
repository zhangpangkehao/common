package com.xiaojinzi.tally.module.user.module.about_us.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.xiaojinzi.component.impl.Router
import com.xiaojinzi.component.impl.routeApi
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.compose.util.circleClip
import com.xiaojinzi.support.ktx.app
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.support.ktx.toStringItemDto
import com.xiaojinzi.tally.lib.res.QQ_GROUP_LINK
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_NORMAL
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_SMALL
import com.xiaojinzi.tally.lib.res.ui.AppHeightSpace
import com.xiaojinzi.tally.module.base.support.AppRouterBaseApi
import com.xiaojinzi.tally.module.base.support.AppServices
import com.xiaojinzi.tally.module.base.view.compose.AppbarNormalM3
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun AboutUsView(
    needInit: Boolean? = false,
) {
    val context = LocalContext.current
    BusinessContentView<AboutUsViewModel>(
        needInit = needInit,
    ) { vm ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.surface,
                )
                .nothing(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Spacer(
                modifier = Modifier
                    .height(height = 80.dp)
                    .nothing()
            )

            Image(
                modifier = Modifier
                    .size(size = 60.dp)
                    .nothing(),
                painter = rememberAsyncImagePainter(
                    model = AppServices.appInfoSpi.appLauncherIconRsd,
                ),
                contentDescription = null,
            )

            AppHeightSpace()

            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .nothing(),
                text = "一刻记账",
                fontFamily = FontFamily(Font(com.xiaojinzi.tally.lib.res.R.font.res_font_xdks)),
                style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                ),
                textAlign = TextAlign.Start,
            )

            AppHeightSpace()
            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .circleClip()
                    .background(
                        color = MaterialTheme.colorScheme.surfaceColorAtElevation(
                            elevation = 1.dp,
                        ),
                    )
                    .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 6.dp)
                    .nothing(),
                text = "v${AppServices.appInfoSpi.appVersionName}",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                ),
                textAlign = TextAlign.Start,
            )

            AppHeightSpace()
            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .circleClip()
                    .background(
                        color = MaterialTheme.colorScheme.surfaceColorAtElevation(
                            elevation = 1.dp,
                        ),
                    )
                    .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 6.dp)
                    .nothing(),
                text = AppServices.appInfoSpi.appPackageName,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                ),
                textAlign = TextAlign.Start,
            )

            AppHeightSpace()
            AppHeightSpace()

            OutlinedButton(
                modifier = Modifier
                    .padding(horizontal = (APP_PADDING_NORMAL * 3).dp, vertical = 0.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .nothing(),
                onClick = {
                    AppServices
                        .systemSpi
                        ?.copyToClipboard(
                            content = "xiaojinzi6666@gmail.com",
                        )
                    Toast.makeText(app, "复制成功", Toast.LENGTH_SHORT).show()
                },
            ) {
                Text(
                    text = "邮箱：xiaojinzi6666@gmail.com",
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.Start,
                )
                Spacer(
                    modifier = Modifier
                        .width(width = 4.dp)
                        .nothing()
                )
                Icon(
                    modifier = Modifier
                        .size(size = 20.dp)
                        .nothing(),
                    painter = painterResource(
                        id = com.xiaojinzi.tally.lib.res.R.drawable.res_copy1,
                    ),
                    contentDescription = null,
                )
            }

            AppHeightSpace()

            OutlinedButton(
                modifier = Modifier
                    .padding(horizontal = (APP_PADDING_NORMAL * 3).dp, vertical = 0.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .nothing(),
                onClick = {
                    Router.with(
                        context = context,
                    ).url(
                        url = QQ_GROUP_LINK,
                    ).forward()
                },
            ) {
                Text(
                    text = "进入 QQ 反馈群",
                    textAlign = TextAlign.Start,
                )
            }

            Spacer(modifier = Modifier.weight(weight = 1f, fill = true))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .nothing(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier
                        .wrapContentSize()
                        .circleClip()
                        .background(
                            color = MaterialTheme.colorScheme.surfaceColorAtElevation(
                                elevation = 1.dp,
                            )
                        )
                        .clickable {
                            AppRouterBaseApi::class
                                .routeApi()
                                .toWebView(
                                    context = context,
                                    url = AppServices.appInfoSpi.userAgreementUrl,
                                )
                        }
                        .padding(
                            horizontal = APP_PADDING_NORMAL.dp,
                            vertical = APP_PADDING_SMALL.dp
                        )
                        .nothing(),
                    text = "用户协议",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                    ),
                    textAlign = TextAlign.Start,
                )
                Spacer(
                    modifier = Modifier
                        .width(width = 24.dp)
                        .nothing()
                )
                Text(
                    modifier = Modifier
                        .wrapContentSize()
                        .circleClip()
                        .background(
                            color = MaterialTheme.colorScheme.surfaceColorAtElevation(
                                elevation = 1.dp,
                            )
                        )
                        .clickable {
                            AppRouterBaseApi::class
                                .routeApi()
                                .toWebView(
                                    context = context,
                                    url = AppServices.appInfoSpi.privacyPolicyUrl,
                                )
                        }
                        .padding(
                            horizontal = APP_PADDING_NORMAL.dp,
                            vertical = APP_PADDING_SMALL.dp
                        )
                        .nothing(),
                    text = "隐私政策",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                    ),
                    textAlign = TextAlign.Start,
                )
            }

            Text(
                modifier = Modifier
                    .navigationBarsPadding()
                    .padding(top = 6.dp, bottom = 12.dp)
                    .nothing(),
                text = AppServices.appInfoSpi.appRecordInfo,
                style = MaterialTheme.typography.labelSmall.copy(
                    color = MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 0.8f,
                    ),
                ),
                textAlign = TextAlign.Center,
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
fun AboutUsViewWrap() {
    Scaffold(
        topBar = {
            AppbarNormalM3(
                title = "关于我们".toStringItemDto(),
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(top = it.calculateTopPadding())
                .nothing(),
        ) {
            AboutUsView()
        }
    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun AboutUsViewPreview() {
    AboutUsView(
        needInit = false,
    )
}