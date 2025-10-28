package com.xiaojinzi.tally.module.core.module.bill_cycle_crud.sub_module.note.view

import com.xiaojinzi.tally.module.core.module.bill_cycle_crud.sub_module.note.domain.BillCycleNoteUseCase
import com.xiaojinzi.tally.module.core.module.bill_cycle_crud.sub_module.note.domain.BillCycleNoteUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer

@ViewLayer
class BillCycleNoteViewModel(
    private val useCase: BillCycleNoteUseCase = BillCycleNoteUseCaseImpl(),
): BaseViewModel(),
    BillCycleNoteUseCase by useCase{
}