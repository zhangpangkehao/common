package com.xiaojinzi.tally.module.user.module.vip_expire_remind.view

import com.xiaojinzi.tally.module.user.module.vip_expire_remind.domain.VipExpireRemindUseCase
import com.xiaojinzi.tally.module.user.module.vip_expire_remind.domain.VipExpireRemindUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer

@ViewLayer
class VipExpireRemindViewModel(
    private val useCase: VipExpireRemindUseCase = VipExpireRemindUseCaseImpl(),
): BaseViewModel(),
    VipExpireRemindUseCase by useCase{
}