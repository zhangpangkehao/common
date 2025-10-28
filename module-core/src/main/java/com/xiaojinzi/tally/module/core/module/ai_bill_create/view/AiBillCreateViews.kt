package com.xiaojinzi.tally.module.core.module.ai_bill_create.view

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.compose.BottomView
import com.xiaojinzi.support.compose.util.clickableNoRipple
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_LARGE
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_NORMAL
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_SMALL
import com.xiaojinzi.tally.module.core.module.ai_bill_create.domain.AiBillCreateIntent
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(ExperimentalComposeUiApi::class)
@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun AiBillCreateView(
    needInit: Boolean? = false,
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    BusinessContentView<AiBillCreateViewModel>(
        needInit = needInit,
    ) { vm ->
        val content by vm.contentStateOb.collectAsState(initial = "")
        BottomView(
            maxFraction = 0.8f,
        ) {

            val focusRequesterForContent = remember { FocusRequester() }
            LaunchedEffect(key1 = focusRequesterForContent) {
                focusRequesterForContent.requestFocus()
                keyboardController?.show()
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                    )
                    .navigationBarsPadding()
                    .imePadding()
                    .nothing(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .weight(weight = 1f, fill = true)
                        .wrapContentHeight()
                        .clickableNoRipple {
                            focusRequesterForContent.requestFocus()
                        }
                        .padding(
                            horizontal = APP_PADDING_LARGE.dp,
                            vertical = APP_PADDING_NORMAL.dp
                        )
                        .nothing(),
                    contentAlignment = Alignment.CenterStart,
                ) {
                    if (content.isEmpty()) {
                        Text(
                            modifier = Modifier
                                .nothing(),
                            text = "比如：中饭支付宝花费50",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.outline,
                            ),
                            textAlign = TextAlign.Start,
                        )
                    }
                    BasicTextField(
                        modifier = Modifier
                            .focusRequester(
                                focusRequester = focusRequesterForContent,
                            )
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .nothing(),
                        value = content,
                        onValueChange = {
                            vm.contentStateOb.value = it.trim()
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done,
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                vm.addIntent(
                                    intent = AiBillCreateIntent.Submit,
                                )
                            },
                        ),
                        cursorBrush = SolidColor(
                            value = MaterialTheme.colorScheme.primary,
                        ),
                        textStyle = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface,
                        ),
                    )
                }

                TextButton(
                    onClick = {
                        vm.addIntent(
                            intent = AiBillCreateIntent.Submit,
                        )
                    }
                ) {
                    Text(
                        text = "确定",
                        textAlign = TextAlign.Start,
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
fun AiBillCreateViewWrap() {
    AiBillCreateView()
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun AiBillCreateViewPreview() {
    AiBillCreateView(
        needInit = false,
    )
}