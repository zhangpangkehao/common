package com.xiaojinzi.tally.module.qrcode

import android.app.Application
import com.xiaojinzi.component.anno.ModuleAppAnno
import com.xiaojinzi.component.application.IApplicationLifecycle

@ModuleAppAnno
class QrCodeModuleApplication: IApplicationLifecycle {

    override fun onCreate(app: Application) {
    }

    override fun onDestroy() {
    }

}