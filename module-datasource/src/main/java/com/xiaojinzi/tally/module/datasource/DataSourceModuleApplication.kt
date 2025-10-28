package com.xiaojinzi.tally.module.datasource

import android.app.Application
import com.xiaojinzi.component.anno.ModuleAppAnno
import com.xiaojinzi.component.application.IApplicationLifecycle
import com.xiaojinzi.component.application.IModuleNotifyChanged

@ModuleAppAnno
class DataSourceModuleApplication : IApplicationLifecycle, IModuleNotifyChanged {

    override fun onCreate(app: Application) {
    }

    override fun onDestroy() {
    }

    override fun onModuleChanged(app: Application) {
    }

}