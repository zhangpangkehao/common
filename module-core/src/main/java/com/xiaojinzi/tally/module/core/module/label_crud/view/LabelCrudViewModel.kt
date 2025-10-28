package com.xiaojinzi.tally.module.core.module.label_crud.view

import com.xiaojinzi.tally.module.core.module.label_crud.domain.LabelCrudUseCase
import com.xiaojinzi.tally.module.core.module.label_crud.domain.LabelCrudUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer

@ViewLayer
class LabelCrudViewModel(
    private val useCase: LabelCrudUseCase = LabelCrudUseCaseImpl(),
): BaseViewModel(),
    LabelCrudUseCase by useCase{
}