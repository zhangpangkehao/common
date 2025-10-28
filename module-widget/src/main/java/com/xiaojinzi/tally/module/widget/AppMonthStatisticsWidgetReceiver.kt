package com.xiaojinzi.tally.module.widget

import androidx.glance.appwidget.GlanceAppWidget

class AppMonthStatisticsWidgetReceiver : BaseAppWidgetReceiver() {

    override val glanceAppWidget: GlanceAppWidget
        get() = AppMonthStatisticsWidget()

}