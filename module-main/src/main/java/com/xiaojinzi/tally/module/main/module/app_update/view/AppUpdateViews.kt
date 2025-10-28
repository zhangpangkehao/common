package com.xiaojinzi.tally.module.main.module.app_update.view

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.xiaojinzi.component.impl.Router
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.compose.util.circleClip
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.support.ktx.orNull
import com.xiaojinzi.support.ktx.tryFinishActivity
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_NORMAL
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_SMALL
import com.xiaojinzi.tally.lib.res.ui.AppHeightSpace
import com.xiaojinzi.tally.module.base.support.AppServices
import com.xiaojinzi.tally.module.main.module.app_update.domain.AppUpdateIntent
import kotlinx.coroutines.InternalCoroutinesApi

private val Shape1 = object : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val temp = 0.15f
        return Outline.Generic(
            path = Path().apply {
                this.reset()
                this.lineTo(
                    x = 0f, y = size.height * (1f - 1 * temp),
                )
                this.quadraticBezierTo(
                    x1 = size.width * 1f / 4f, y1 = size.height * (1f - 2 * temp),
                    x2 = size.width * 2f / 4f, y2 = size.height * (1f - 1 * temp),
                )
                this.quadraticBezierTo(
                    x1 = size.width * 3f / 4f, y1 = size.height * (1f - 0 * temp),
                    x2 = size.width * 4f / 4f, y2 = size.height * (1f - 1 * temp),
                )
                this.lineTo(
                    x = size.width, y = 0f,
                )
                this.close()
            },
        )
    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun AppUpdateView(
    needInit: Boolean? = false,
) {
    val context = LocalContext.current
    BusinessContentView<AppUpdateViewModel>(
        needInit = needInit,
    ) { vm ->
        val appInfo by vm.appInfoInitData.valueStateFlow.collectAsState(initial = null)
        Dialog(
            onDismissRequest = {
                // context.tryFinishActivity()
            }
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = (APP_PADDING_NORMAL * 2).dp, vertical = 0.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(
                        shape = MaterialTheme.shapes.medium,
                    )
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                    )
                    .nothing(),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .clip(
                            shape = Shape1,
                        )
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                        )
                        .nothing(),
                )
                Column(
                    modifier = Modifier
                        .padding(
                            horizontal = APP_PADDING_NORMAL.dp,
                            vertical = APP_PADDING_SMALL.dp
                        )
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .nothing(),
                ) {
                    AppHeightSpace()
                    Text(
                        text = if (appInfo?.isForce == true) {
                            "发现重要版本"
                        } else {
                            "发现新版本"
                        },
                        style = MaterialTheme.typography.titleLarge.copy(
                            // 斜体
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onPrimary,
                        ),
                        textAlign = TextAlign.Start,
                    )
                    AppHeightSpace()
                    Text(
                        text = appInfo?.versionName.orEmpty(),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onPrimary,
                        ),
                        textAlign = TextAlign.Start,
                    )
                    Spacer(
                        modifier = Modifier
                            .height(height = 40.dp)
                            .nothing()
                    )
                    Text(
                        modifier = Modifier
                            .padding(horizontal = APP_PADDING_SMALL.dp, vertical = 0.dp)
                            .fillMaxWidth()
                            .heightIn(max = 150.dp)
                            .verticalScroll(
                                state = rememberScrollState(),
                            )
                            .nothing(),
                        text = "${
                            if (appInfo?.isForce == true) {
                                "少爷公主请更新~~~"
                            } else {
                                ""
                            }
                        }\n${appInfo?.updateDesc.orEmpty()}",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurface,
                        ),
                        textAlign = TextAlign.Start,
                    )

                    Spacer(
                        modifier = Modifier
                            .height(height = 24.dp)
                            .nothing()
                    )

                    Button(
                        modifier = Modifier
                            .padding(horizontal = (APP_PADDING_NORMAL * 2).dp, vertical = 0.dp)
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .nothing(),
                        onClick = {
                            runCatching {
                                Uri.parse(
                                    appInfo?.downloadUrl.orNull()
                                        ?: AppServices.appInfoSpi.officialUrl
                                )
                            }.getOrNull()?.let {
                                Router.with(
                                    context = context,
                                ).url(
                                    url = it.toString(),
                                ).forward {
                                    if (appInfo?.isForce == false) {
                                        context.tryFinishActivity()
                                    }
                                }
                            }
                        },
                    ) {
                        Text(
                            text = "立即升级",
                            textAlign = TextAlign.Start,
                        )
                    }

                    if (appInfo?.isForce == false) {
                        Text(
                            modifier = Modifier
                                .align(
                                    alignment = Alignment.CenterHorizontally,
                                )
                                .padding(horizontal = (APP_PADDING_NORMAL * 2).dp, vertical = 0.dp)
                                .circleClip()
                                .clickable {
                                    vm.addIntent(
                                        intent = AppUpdateIntent.IgnoreThisVersion,
                                    )
                                }
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(horizontal = 0.dp, vertical = APP_PADDING_NORMAL.dp)
                                .nothing(),
                            text = "忽略此次更新",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onSurface,
                            ),
                            textAlign = TextAlign.Center,
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
fun AppUpdateViewWrap() {
    AppUpdateView()
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun AppUpdateViewPreview() {
    AppUpdateView(
        needInit = false,
    )
}