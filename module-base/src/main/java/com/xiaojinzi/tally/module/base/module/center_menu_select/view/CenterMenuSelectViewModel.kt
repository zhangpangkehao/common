package com.xiaojinzi.tally.module.base.module.center_menu_select.view

import com.xiaojinzi.tally.module.base.module.center_menu_select.domain.CenterMenuSelectUseCase
import com.xiaojinzi.tally.module.base.module.center_menu_select.domain.CenterMenuSelectUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer

@ViewLayer
class CenterMenuSelectViewModel(
    private val useCase: CenterMenuSelectUseCase = CenterMenuSelectUseCaseImpl(),
): BaseViewModel(),
    CenterMenuSelectUseCase by useCase{
}