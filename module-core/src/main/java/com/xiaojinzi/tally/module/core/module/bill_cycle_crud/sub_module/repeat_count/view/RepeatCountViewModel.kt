package com.xiaojinzi.tally.module.core.module.bill_cycle_crud.sub_module.repeat_count.view

import com.xiaojinzi.tally.module.core.module.bill_cycle_crud.sub_module.repeat_count.domain.RepeatCountUseCase
import com.xiaojinzi.tally.module.core.module.bill_cycle_crud.sub_module.repeat_count.domain.RepeatCountUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer

@ViewLayer
class RepeatCountViewModel(
    private val useCase: RepeatCountUseCase = RepeatCountUseCaseImpl(),
): BaseViewModel(),
    RepeatCountUseCase by useCase{
}