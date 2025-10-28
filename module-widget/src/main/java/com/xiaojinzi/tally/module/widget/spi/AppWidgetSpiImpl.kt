package com.xiaojinzi.tally.module.widget.spi

import com.xiaojinzi.component.anno.ServiceAnno
import com.xiaojinzi.support.ktx.awaitIgnoreException
import com.xiaojinzi.support.ktx.executeIgnoreException
import com.xiaojinzi.tally.module.base.spi.AppWidgetSpi
import com.xiaojinzi.tally.module.widget.ktx.updateAllWidgetAction
import com.xiaojinzi.tally.module.widget.service.AppWidgetUpdateService

@ServiceAnno(AppWidgetSpi::class)
class AppWidgetSpiImpl : AppWidgetSpi {

    override suspend fun startWidgetServiceIfUsed() {
        AppWidgetUpdateService
            .startWidgetServiceIfUsedAction()
            .awaitIgnoreException()
    }

    override fun tryUpdateWidget() {
        updateAllWidgetAction()
            .executeIgnoreException()
    }

}