package com.xiaojinzi.tally.module.base.support

import android.app.Activity
import android.app.ActivityManager
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import com.xiaojinzi.support.ktx.app

fun Activity.alphaOutWhenFinish() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
        overrideActivityTransition(
            Activity.OVERRIDE_TRANSITION_CLOSE,
            0,
            com.xiaojinzi.tally.lib.res.R.anim.alpha_out,
        )
    } else {
        @Suppress("DEPRECATION")
        overridePendingTransition(0, com.xiaojinzi.tally.lib.res.R.anim.alpha_out)
    }
}

fun finishAppAllTask() {
    (app.getSystemService(AppCompatActivity.ACTIVITY_SERVICE) as ActivityManager).appTasks?.forEach {
        it.finishAndRemoveTask()
    }
}

fun isInstallWx(): Boolean {
    return try {
        app.packageManager.getPackageInfo("com.tencent.mm", PackageManager.GET_ACTIVITIES)
        true
    } catch (e: Exception) {
        false
    }
}