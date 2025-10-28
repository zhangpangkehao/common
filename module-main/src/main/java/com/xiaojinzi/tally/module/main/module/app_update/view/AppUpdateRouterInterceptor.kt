package com.xiaojinzi.tally.module.main.module.app_update.view

import com.xiaojinzi.component.impl.RouterInterceptor
import com.xiaojinzi.component.impl.RouterResult
import com.xiaojinzi.component.support.ParameterSupport
import com.xiaojinzi.module.common.base.support.CommonServices
import com.xiaojinzi.tally.module.base.support.AppServices
import com.xiaojinzi.tally.module.base.view.compose.commonAppToast

class AppUpdateRouterInterceptor : RouterInterceptor {

    override suspend fun intercept(chain: RouterInterceptor.Chain): RouterResult {

        val isTip = ParameterSupport.getBoolean(
            bundle = chain.request().bundle,
            key = "isTip",
        ) ?: false

        val lastAppInfo = AppServices
            .appNetworkSpi
            .getNewUpdate()
            ?: run {
                if (isTip) {
                    commonAppToast(
                        content = "已是最新版本",
                    )
                }
                throw Exception("没有新版本需要更新")
            }

        if (!lastAppInfo.isForce) {
            val isIgnoreThisVersion = CommonServices
                .spService
                ?.getBool(
                    "appUpdateIgnore",
                    key = "version: ${lastAppInfo.versionCode}",
                    defValue = false,
                ) ?: false

            if (isIgnoreThisVersion) {
                throw Exception("此版本已被忽略更新")
            }
        }

        // 当前的版本号
        val currentAppCode = AppServices
            .appInfoSpi
            .appVersionCode

        return if (lastAppInfo.versionCode > currentAppCode) {
            chain.proceed(
                request = chain.request()
                    .toBuilder()
                    .putParcelable(
                        key = "appInfo", value = lastAppInfo,
                    )
                    .build(),
            )
        } else {
            if (isTip) {
                commonAppToast(
                    content = "已经是最新的版本了",
                )
            }
            throw Exception("已经是最新的版本了")
        }

    }

}