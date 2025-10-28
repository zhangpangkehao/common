package com.xiaojinzi.tally.module.main.module.main.view

import com.xiaojinzi.component.impl.RouterInterceptor
import com.xiaojinzi.component.impl.RouterResult
import com.xiaojinzi.support.ktx.awaitIgnoreException
import com.xiaojinzi.tally.lib.res.model.exception.NotLoggedInException
import com.xiaojinzi.tally.module.base.support.AppRouterConfig
import com.xiaojinzi.tally.module.base.support.AppServices
import kotlinx.coroutines.flow.firstOrNull

/**
 * 进入主界面有可能是没有登录状态的
 * 如果没同步数据, 就会去同步数据
 */
class MainRouterInterceptor : RouterInterceptor {

    override suspend fun intercept(chain: RouterInterceptor.Chain): RouterResult {
        val isInitData = AppServices
            .tallyDataSourceSpi
            .isInitData()
        val userInfo = AppServices
            .userSpi
            .userInfoStateOb
            .firstOrNull()
        return if (isInitData) {
            if (userInfo != null) {
                // 更新账本
                AppServices
                    .tallyDataSourceSpi
                    .tryRefreshBookList(
                        userId = userInfo.id,
                    )
                    .awaitIgnoreException()
            }
            chain.proceed(
                request = chain.request(),
            )
        } else {
            if (userInfo == null) {
                throw NotLoggedInException()
            } else {
                chain.proceed(
                    request = chain.request()
                        .toBuilder()
                        .hostAndPath(
                            hostAndPath = AppRouterConfig.CORE_FIRST_SYNC,
                        )
                        .build(),
                )
            }
        }
    }

}