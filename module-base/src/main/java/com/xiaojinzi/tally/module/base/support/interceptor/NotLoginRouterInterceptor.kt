package com.xiaojinzi.tally.module.base.support.interceptor

import androidx.appcompat.app.AlertDialog
import com.xiaojinzi.component.anno.MainThread
import com.xiaojinzi.component.impl.RouterInterceptor
import com.xiaojinzi.component.impl.RouterResult
import com.xiaojinzi.component.impl.routeApi
import com.xiaojinzi.support.ktx.notSupportError
import com.xiaojinzi.support.ktx.resumeExceptionIgnoreException
import com.xiaojinzi.support.ktx.resumeIgnoreException
import com.xiaojinzi.tally.lib.res.model.exception.NotLoggedInException
import com.xiaojinzi.tally.module.base.support.AppRouterUserApi
import com.xiaojinzi.tally.module.base.support.AppServices
import kotlinx.coroutines.flow.firstOrNull
import kotlin.coroutines.suspendCoroutine

/**
 * 没有登录的一个拦截器
 */
@MainThread
class NotLoginRouterInterceptor : RouterInterceptor {

    override suspend fun intercept(chain: RouterInterceptor.Chain): RouterResult {
        val userInfo = AppServices
            .userSpi
            .userInfoStateOb
            .firstOrNull()
        return if (userInfo == null) {
            chain.request().rawAliveContext?.run {
                suspendCoroutine<Unit> { cot ->
                    AlertDialog
                        .Builder(this)
                        .setMessage("帅哥美女, 请先登录哦~")
                        .setNegativeButton(
                            "取消",
                        ) { dialog, _ ->
                            dialog.dismiss()
                            cot.resumeExceptionIgnoreException(
                                exception = NotLoggedInException(),
                            )
                        }
                        .setPositiveButton(
                            "去登录",
                        ) { dialog, _ ->
                            dialog.dismiss()
                            cot.resumeIgnoreException(
                                value = Unit,
                            )
                        }
                        .create()
                        .show()
                }
                AppRouterUserApi::class
                    .routeApi()
                    .toLoginView(
                        context = this,
                    )
                throw NotLoggedInException()
            } ?: notSupportError("没有可用的 Context")
        } else {
            chain.proceed(
                request = chain.request(),
            )
        }
    }

}