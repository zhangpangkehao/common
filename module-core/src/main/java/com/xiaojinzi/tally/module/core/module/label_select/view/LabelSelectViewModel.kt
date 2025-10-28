package com.xiaojinzi.tally.module.core.module.label_select.view

import com.xiaojinzi.tally.module.core.module.label_select.domain.LabelSelectUseCase
import com.xiaojinzi.tally.module.core.module.label_select.domain.LabelSelectUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer

@ViewLayer
class LabelSelectViewModel(
    private val useCase: LabelSelectUseCase = LabelSelectUseCaseImpl(),
): BaseViewModel(),
    LabelSelectUseCase by useCase{
}