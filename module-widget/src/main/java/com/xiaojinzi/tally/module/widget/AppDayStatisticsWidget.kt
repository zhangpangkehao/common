package com.xiaojinzi.tally.module.widget

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.ImageProvider
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxHeight
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.layout.wrapContentHeight
import androidx.glance.layout.wrapContentWidth
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextDefaults
import com.xiaojinzi.support.ktx.LogSupport
import com.xiaojinzi.support.ktx.format2f
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.tally.module.widget.ktx.isSystemInDarkTheme

/**
 * App 中的天统计的小部件
 */
class AppDayStatisticsWidget : GlanceAppWidget() {

    companion object {
        const val TAG = "AppDayStatisticsWidget"
    }

    /**
     * 为什么会有 id 参数.
     * 是因为虽然你写的是一个控件, 但是用户可能会创建 N 个, 所以这个 id 就是表示用户创建的小部件的对象的
     */
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        // In this method, load data needed to render the AppWidget.
        // Use `withContext` to switch to another thread for long running
        // operations.
        provideContent {

            val currentDaySpending by AppWidgetDataSource.currentDaySpendingStateOb.collectAsState(
                initial = null
            )
            val currentDayIncome by AppWidgetDataSource.currentDayIncomeStateOb.collectAsState(
                initial = null
            )
            // 获取 application 的 Configuration

            GlanceTheme {
                // 今日结余
                val currentDayBalance by remember {
                    derivedStateOf {
                        currentDayIncome?.let { income ->
                            currentDaySpending?.let { spending ->
                                income - spending
                            }
                        }
                    }
                }
                Column(
                    modifier = GlanceModifier
                        .fillMaxSize()
                        .background(
                            imageProvider = ImageProvider(
                                resId = if (isSystemInDarkTheme) {
                                    R.drawable.widget_bg_dark1
                                } else {
                                    R.drawable.widget_bg_light1
                                },
                            ),
                        )
                        // .background(colorProvider = GlanceTheme.colors.surface)
                        .clickable {
                            LogSupport.d(
                                tag = TAG,
                                content = "跳转到启动页 start",
                            )
                            // 启动启动页
                            context.startActivity(
                                Intent().apply {
                                    this.`package` = context.packageName
                                    this.action = "app_launch_20240723"
                                    this.addCategory(Intent.CATEGORY_DEFAULT)
                                    this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                }
                            )
                            LogSupport.d(
                                tag = TAG,
                                content = "跳转到启动页 end",
                            )
                        }
                        .padding(
                            horizontal = 12.dp, vertical = 8.dp,
                        )
                        .nothing(),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Row {
                        Column(
                            modifier = GlanceModifier
                                .nothing(),
                        ) {
                            Text(
                                modifier = GlanceModifier
                                    .nothing(),
                                text = "今日支出",
                                style = TextDefaults.defaultTextStyle.copy(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 12.sp,
                                    color = GlanceTheme.colors.outline,
                                ),
                            )
                            Spacer(
                                modifier = GlanceModifier
                                    .height(height = 4.dp)
                                    .nothing()
                            )
                            Text(
                                modifier = GlanceModifier
                                    .nothing(),
                                text = currentDaySpending?.toYuan()?.value?.format2f() ?: "---",
                                style = TextDefaults.defaultTextStyle.copy(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 14.sp,
                                    color = GlanceTheme.colors.onSurface,
                                ),
                            )
                        }
                    }
                    Row(
                        modifier = GlanceModifier
                            .wrapContentWidth()
                            .fillMaxHeight()
                            .nothing(),
                        verticalAlignment = Alignment.Bottom,
                    ) {
                        Column(
                            modifier = GlanceModifier
                                .nothing(),
                            horizontalAlignment = Alignment.Start,
                        ) {
                            Text(
                                modifier = GlanceModifier
                                    .nothing(),
                                text = "今日收入",
                                style = TextDefaults.defaultTextStyle.copy(
                                    fontSize = 10.sp,
                                    color = GlanceTheme.colors.outline,
                                ),
                            )
                            Text(
                                modifier = GlanceModifier
                                    .nothing(),
                                text = currentDayIncome?.toYuan()?.value?.format2f() ?: "---",
                                style = TextDefaults.defaultTextStyle.copy(
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 10.sp,
                                    color = GlanceTheme.colors.onSurface,
                                ),
                            )
                        }
                        Spacer(
                            modifier = GlanceModifier
                                .width(width = 12.dp)
                                .nothing()
                        )
                        Column(
                            modifier = GlanceModifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .nothing(),
                            horizontalAlignment = Alignment.End,
                        ) {
                            Text(
                                modifier = GlanceModifier
                                    .nothing(),
                                text = "今日结余",
                                style = TextDefaults.defaultTextStyle.copy(
                                    fontSize = 10.sp,
                                    color = GlanceTheme.colors.outline,
                                ),
                            )
                            Text(
                                modifier = GlanceModifier
                                    .nothing(),
                                text = currentDayBalance?.toYuan()?.value?.format2f() ?: "---",
                                style = TextDefaults.defaultTextStyle.copy(
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 10.sp,
                                    color = GlanceTheme.colors.onSurface,
                                ),
                            )
                        }
                    }
                }

            }

        }
    }

}