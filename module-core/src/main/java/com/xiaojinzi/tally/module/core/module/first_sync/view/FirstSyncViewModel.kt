package com.xiaojinzi.tally.module.core.module.first_sync.view

import com.xiaojinzi.tally.module.core.module.first_sync.domain.FirstSyncUseCase
import com.xiaojinzi.tally.module.core.module.first_sync.domain.FirstSyncUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer

@ViewLayer
class FirstSyncViewModel(
    private val useCase: FirstSyncUseCase = FirstSyncUseCaseImpl(),
): BaseViewModel(),
    FirstSyncUseCase by useCase{
}