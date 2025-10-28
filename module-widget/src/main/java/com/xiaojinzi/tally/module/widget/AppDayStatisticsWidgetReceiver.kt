package com.xiaojinzi.tally.module.widget

import androidx.glance.appwidget.GlanceAppWidget

class AppDayStatisticsWidgetReceiver : BaseAppWidgetReceiver() {

    override val glanceAppWidget: GlanceAppWidget
        get() = AppDayStatisticsWidget()

}