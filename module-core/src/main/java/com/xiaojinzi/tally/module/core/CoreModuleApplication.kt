package com.xiaojinzi.tally.module.core

import android.app.Application
import com.xiaojinzi.tally.module.base.support.AppServices
import com.xiaojinzi.component.anno.ModuleAppAnno
import com.xiaojinzi.component.application.IApplicationLifecycle
import com.xiaojinzi.component.application.IModuleNotifyChanged
import com.xiaojinzi.support.ktx.executeTaskInCoroutinesIgnoreError

@ModuleAppAnno
class CoreModuleApplication : IApplicationLifecycle, IModuleNotifyChanged {

    override fun onCreate(app: Application) {
    }

    override fun onDestroy() {
    }

    override fun onModuleChanged(app: Application) {
        /*executeTaskInCoroutinesIgnoreError {
            AppServices
                .tallyDataSourceSpi
                ?.insertTestDataOnce()
        }*/
    }

}