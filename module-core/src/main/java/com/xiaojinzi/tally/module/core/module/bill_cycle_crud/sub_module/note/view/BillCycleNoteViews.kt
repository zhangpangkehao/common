package com.xiaojinzi.tally.module.core.module.bill_cycle_crud.sub_module.repeat_count.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.ktx.getActivity
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_LARGE
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_NORMAL
import com.xiaojinzi.tally.lib.res.ui.topShape
import com.xiaojinzi.tally.module.core.module.bill_cycle_crud.sub_module.note.view.BillCycleNoteViewModel
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun BillCycleNoteView(
    needInit: Boolean? = false,
    initNote: String? = null,
) {
    val context = LocalContext.current
    var textStr by remember {
        mutableStateOf(
            value = TextFieldValue(
                text = initNote.orEmpty(),
                selection = TextRange(
                    index = initNote.orEmpty().length,
                ),
            )
        )
    }
    val returnAction = {
        context.getActivity()?.run {
            this.setResult(
                Activity.RESULT_OK,
                Intent().apply {
                    this.putExtra("data", textStr.text.trim())
                }
            )
            this.finish()
        }
    }
    BusinessContentView<BillCycleNoteViewModel>(
        needInit = needInit,
    ) { vm ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .nothing(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(weight = 1f, fill = true)
                    .background(
                        color = Color.Black.copy(
                            alpha = 0.44f,
                        )
                    )
                    .nothing()
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(
                        shape = MaterialTheme.shapes.medium.topShape(),
                    )
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                    )
                    .navigationBarsPadding()
                    .imePadding()
                    .padding(bottom = APP_PADDING_NORMAL.dp)
                    .nothing(),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(
                            horizontal = APP_PADDING_NORMAL.dp,
                            vertical = 0.dp
                        )
                        .nothing(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    val focusRequester = remember { FocusRequester() }
                    LaunchedEffect(key1 = focusRequester) {
                        focusRequester.requestFocus()
                    }
                    BasicTextField(
                        modifier = Modifier
                            .focusRequester(
                                focusRequester = focusRequester,
                            )
                            .weight(weight = 1f, fill = true)
                            .wrapContentHeight()
                            .padding(horizontal = APP_PADDING_LARGE.dp, vertical = 0.dp)
                            .nothing(),
                        value = textStr,
                        onValueChange = {
                            textStr = it
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done,
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                returnAction.invoke()
                            },
                        ),
                        cursorBrush = SolidColor(
                            value = MaterialTheme.colorScheme.primary,
                        ),
                        textStyle = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface,
                        ),
                    )
                    Button(
                        modifier = Modifier
                            .scale(scale = 0.8f)
                            .nothing(),
                        onClick = {
                            returnAction.invoke()
                        }) {
                        Text(
                            text = "保存",
                            textAlign = TextAlign.Start,
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
fun BillCycleNoteViewWrap(
    initNote: String? = null,
) {
    BillCycleNoteView(
        initNote = initNote,
    )
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun BillCycleNoteViewPreview() {
    BillCycleNoteView(
        needInit = false,
    )
}