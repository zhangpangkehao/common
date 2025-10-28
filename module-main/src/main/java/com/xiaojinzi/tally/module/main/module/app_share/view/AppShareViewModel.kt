package com.xiaojinzi.tally.module.main.module.app_share.view

import com.xiaojinzi.tally.module.main.module.app_share.domain.AppShareUseCase
import com.xiaojinzi.tally.module.main.module.app_share.domain.AppShareUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer

@ViewLayer
class AppShareViewModel(
    private val useCase: AppShareUseCase = AppShareUseCaseImpl(),
): BaseViewModel(),
    AppShareUseCase by useCase{
}