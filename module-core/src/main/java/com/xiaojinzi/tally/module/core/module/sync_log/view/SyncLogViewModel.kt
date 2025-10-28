package com.xiaojinzi.tally.module.core.module.sync_log.view

import com.xiaojinzi.tally.module.core.module.sync_log.domain.SyncLogUseCase
import com.xiaojinzi.tally.module.core.module.sync_log.domain.SyncLogUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer

@ViewLayer
class SyncLogViewModel(
    private val useCase: SyncLogUseCase = SyncLogUseCaseImpl(),
): BaseViewModel(),
    SyncLogUseCase by useCase{
}