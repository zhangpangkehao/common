package com.xiaojinzi.tally.module.core.module.ai_bill_create.view

import com.xiaojinzi.tally.module.core.module.ai_bill_create.domain.AiBillCreateUseCase
import com.xiaojinzi.tally.module.core.module.ai_bill_create.domain.AiBillCreateUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer

@ViewLayer
class AiBillCreateViewModel(
    private val useCase: AiBillCreateUseCase = AiBillCreateUseCaseImpl(),
): BaseViewModel(),
    AiBillCreateUseCase by useCase{
}