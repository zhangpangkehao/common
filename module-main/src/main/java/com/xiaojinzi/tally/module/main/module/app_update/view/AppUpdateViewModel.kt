package com.xiaojinzi.tally.module.main.module.app_update.view

import com.xiaojinzi.tally.module.main.module.app_update.domain.AppUpdateUseCase
import com.xiaojinzi.tally.module.main.module.app_update.domain.AppUpdateUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer

@ViewLayer
class AppUpdateViewModel(
    private val useCase: AppUpdateUseCase = AppUpdateUseCaseImpl(),
): BaseViewModel(),
    AppUpdateUseCase by useCase{
}