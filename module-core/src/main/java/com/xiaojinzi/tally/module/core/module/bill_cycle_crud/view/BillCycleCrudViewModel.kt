package com.xiaojinzi.tally.module.core.module.bill_cycle_crud.view

import com.xiaojinzi.tally.module.core.module.bill_cycle_crud.domain.BillCycleCrudUseCase
import com.xiaojinzi.tally.module.core.module.bill_cycle_crud.domain.BillCycleCrudUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer

@ViewLayer
class BillCycleCrudViewModel(
    private val useCase: BillCycleCrudUseCase = BillCycleCrudUseCaseImpl(),
): BaseViewModel(),
    BillCycleCrudUseCase by useCase{
}