package com.xiaojinzi.tally.module.main

import android.app.Application
import com.xiaojinzi.component.anno.ModuleAppAnno
import com.xiaojinzi.component.application.IApplicationLifecycle

@ModuleAppAnno
class MainModuleApplication: IApplicationLifecycle {

    override fun onCreate(app: Application) {
    }

    override fun onDestroy() {
    }

}