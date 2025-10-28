package com.xiaojinzi.tally.module.base.module.date_time_select.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.compose.util.clickableNoRipple
import com.xiaojinzi.support.ktx.getActivity
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.support.ktx.tryFinishActivity
import com.xiaojinzi.tally.lib.res.model.support.DateTimeModel
import com.xiaojinzi.tally.lib.res.model.support.DateTimeType
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_NORMAL
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_SMALL
import com.xiaojinzi.tally.module.base.view.compose.date.DateWheelView
import com.xiaojinzi.tally.module.base.view.compose.date.TimeWheelView
import com.xiaojinzi.tally.module.base.view.compose.date.rememberDateTimeWheelState
import kotlinx.coroutines.InternalCoroutinesApi
import java.util.Calendar

@OptIn(ExperimentalMaterialApi::class)
@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun DateTimeSelectView(
    needInit: Boolean? = false,
    time: Long? = null,
    type: DateTimeType,
    model: DateTimeModel,
) {
    val context = LocalContext.current
    val dateTimeTimeState = rememberDateTimeWheelState(
        initTime = time ?: System.currentTimeMillis(),
    )
    BusinessContentView<DateTimeSelectViewModel>(
        needInit = needInit,
    ) { vm ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color.Black.copy(
                        alpha = 0.5f,
                    )
                )
                .nothing(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(weight = 1f, fill = true)
                    .clickableNoRipple {
                        context.tryFinishActivity()
                    }
                    .nothing(),
            )
            ProvideTextStyle(
                value = MaterialTheme.typography.titleSmall.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                        )
                        .nothing(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        modifier = Modifier
                            .clickableNoRipple {
                                context.tryFinishActivity()
                            }
                            .padding(
                                horizontal = APP_PADDING_NORMAL.dp,
                                vertical = APP_PADDING_NORMAL.dp,
                            )
                            .wrapContentSize()
                            .nothing(),
                        text = "取消",
                        textAlign = TextAlign.Start,
                    )
                    Text(
                        modifier = Modifier
                            .padding(
                                horizontal = APP_PADDING_NORMAL.dp,
                                vertical = APP_PADDING_NORMAL.dp,
                            )
                            .wrapContentSize()
                            .nothing(),
                        text = when {
                            type.flag == DateTimeType.Time.flag -> "日期/时间"
                            else -> "日期"
                        },
                        textAlign = TextAlign.Start,
                    )
                    Text(
                        modifier = Modifier
                            .clickableNoRipple {
                                context
                                    .getActivity()
                                    ?.run {
                                        var selectTime = dateTimeTimeState.currentTime
                                        selectTime = when (model) {
                                            DateTimeModel.Current -> selectTime
                                            DateTimeModel.DropLast -> {
                                                val calendar = Calendar.getInstance()
                                                calendar.timeInMillis = selectTime
                                                calendar.set(Calendar.MILLISECOND, 0)
                                                if (type.flag <= DateTimeType.Day.flag) {
                                                    calendar.set(Calendar.SECOND, 0)
                                                    calendar.set(Calendar.MINUTE, 0)
                                                    calendar.set(Calendar.HOUR_OF_DAY, 0)
                                                }
                                                if (type.flag <= DateTimeType.Month.flag) {
                                                    calendar.set(Calendar.DAY_OF_MONTH, 1)
                                                }
                                                if (type.flag <= DateTimeType.Year.flag) {
                                                    calendar.set(Calendar.MONTH, 0)
                                                }
                                                calendar.timeInMillis
                                            }
                                        }
                                        this.setResult(
                                            Activity.RESULT_OK,
                                            Intent().apply {
                                                this.putExtra("data", selectTime)
                                            },
                                        )
                                        this.finish()
                                    }
                            }
                            .padding(
                                horizontal = APP_PADDING_NORMAL.dp,
                                vertical = APP_PADDING_NORMAL.dp
                            )
                            .wrapContentSize()
                            .nothing(),
                        text = "确定",
                        textAlign = TextAlign.Start,
                    )
                }
            }
            ProvideTextStyle(
                value = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .nothing(),
                ) {
                    DateWheelView(
                        modifier = Modifier
                            .weight(weight = 3f, fill = true)
                            .background(
                                color = MaterialTheme.colorScheme.surface,
                            )
                            .navigationBarsPadding()
                            .nothing(),
                        showMonth = type.flag > DateTimeType.Year.flag,
                        showDay = type.flag > DateTimeType.Month.flag,
                        dateTimeTimeState = dateTimeTimeState,
                        lineWidthPercent = 0.5f,
                        itemHeight = (48 + APP_PADDING_SMALL * 2).dp,
                    )
                    if (type == DateTimeType.Time) {
                        TimeWheelView(
                            modifier = Modifier
                                .weight(weight = 2f, fill = true)
                                .background(
                                    color = MaterialTheme.colorScheme.surface,
                                )
                                .navigationBarsPadding()
                                .nothing(),
                            dateTimeTimeState = dateTimeTimeState,
                            lineWidthPercent = 0.5f,
                            itemHeight = (48 + APP_PADDING_SMALL * 2).dp,
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
fun DateTimeSelectViewWrap(
    time: Long? = null,
    type: DateTimeType,
    model: DateTimeModel,
) {
    DateTimeSelectView(
        time = time,
        type = type,
        model = model,
    )
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun DateTimeSelectViewPreview() {
    DateTimeSelectView(
        needInit = false,
        type = DateTimeType.Day,
        model = DateTimeModel.Current,
    )
}