package com.xiaojinzi.tally.module.core.module.label_info.view

import com.xiaojinzi.tally.module.core.module.label_info.domain.LabelInfoUseCase
import com.xiaojinzi.tally.module.core.module.label_info.domain.LabelInfoUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer

@ViewLayer
class LabelInfoViewModel(
    private val useCase: LabelInfoUseCase = LabelInfoUseCaseImpl(),
): BaseViewModel(),
    LabelInfoUseCase by useCase{
}