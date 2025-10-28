package com.xiaojinzi.tally.module.core.module.icon_select.view

import com.xiaojinzi.tally.module.core.module.icon_select.domain.IconSelectUseCase
import com.xiaojinzi.tally.module.core.module.icon_select.domain.IconSelectUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer

@ViewLayer
class IconSelectViewModel(
    private val useCase: IconSelectUseCase = IconSelectUseCaseImpl(),
): BaseViewModel(),
    IconSelectUseCase by useCase{
}