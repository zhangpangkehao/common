package com.xiaojinzi.tally.module.base.support.interceptor

import com.xiaojinzi.component.anno.MainThread
import com.xiaojinzi.component.impl.RouterInterceptor
import com.xiaojinzi.component.impl.RouterResult
import com.xiaojinzi.tally.lib.res.model.exception.CommonBusinessException
import com.xiaojinzi.tally.module.base.support.AppServices
import com.xiaojinzi.tally.module.base.view.compose.commonAppToast

@MainThread
class OpenSourceNotSupportRouterInterceptor : RouterInterceptor {

    override suspend fun intercept(chain: RouterInterceptor.Chain): RouterResult {
        val forOpenSource = AppServices
            .appInfoSpi
            .forOpenSource
        if (forOpenSource) {
            val tipMessage = "开源版本不支持此功能"
            commonAppToast(
                content = tipMessage,
            )
            throw CommonBusinessException(
                message = tipMessage,
            )
        }
        return chain.proceed(chain.request())
    }

}