package com.xiaojinzi.tally.module.user.module.vip_buy.view

import com.xiaojinzi.tally.module.user.module.vip_buy.domain.VipBuyUseCase
import com.xiaojinzi.tally.module.user.module.vip_buy.domain.VipBuyUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer

@ViewLayer
class VipBuyViewModel(
    private val useCase: VipBuyUseCase = VipBuyUseCaseImpl(),
): BaseViewModel(),
    VipBuyUseCase by useCase{
}