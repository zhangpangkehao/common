package com.xiaojinzi.tally.module.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.xiaojinzi.support.ktx.executeIgnoreException
import com.xiaojinzi.tally.module.widget.service.AppWidgetUpdateService

abstract class BaseAppWidgetReceiver : GlanceAppWidgetReceiver() {

    override fun onEnabled(context: Context?) {
        super.onEnabled(context)
        AppWidgetUpdateService
            .startWidgetServiceIfUsedAction()
            .executeIgnoreException()
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        AppWidgetUpdateService
            .startWidgetServiceIfUsedAction()
            .executeIgnoreException()
    }

}