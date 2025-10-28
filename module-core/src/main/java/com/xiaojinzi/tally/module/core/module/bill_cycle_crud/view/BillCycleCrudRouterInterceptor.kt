package com.xiaojinzi.tally.module.core.module.bill_cycle_crud.view

import com.xiaojinzi.component.impl.RouterInterceptor
import com.xiaojinzi.component.impl.RouterResult
import com.xiaojinzi.tally.lib.res.model.exception.NotVipException
import com.xiaojinzi.tally.module.base.support.AppServices
import com.xiaojinzi.tally.module.base.view.compose.commonAppToast
import kotlinx.coroutines.flow.first

class BillCycleCrudRouterInterceptor : RouterInterceptor {

    override suspend fun intercept(chain: RouterInterceptor.Chain): RouterResult {
        val isVip = AppServices.userSpi.isVipStateOb.first()
        if (!isVip) {
            commonAppToast(
                content = "此功能需要会员才能使用",
            )
            throw NotVipException()
        }
        return chain.proceed(
            request = chain.request(),
        )
    }

}