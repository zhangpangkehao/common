package com.xiaojinzi.tally.module.user.module.log_off_confirm.view

import com.xiaojinzi.tally.module.user.module.log_off_confirm.domain.SignOutConfirmUseCase
import com.xiaojinzi.tally.module.user.module.log_off_confirm.domain.SignOutConfirmUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer

@ViewLayer
class LogOffConfirmViewModel(
    private val useCase: SignOutConfirmUseCase = SignOutConfirmUseCaseImpl(),
): BaseViewModel(),
    SignOutConfirmUseCase by useCase{
}