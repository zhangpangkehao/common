package com.xiaojinzi.tally.module.base.module.bottom_menu_select.view

import com.xiaojinzi.tally.module.base.module.bottom_menu_select.domain.BottomMenuSelectUseCase
import com.xiaojinzi.tally.module.base.module.bottom_menu_select.domain.BottomMenuSelectUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer

@ViewLayer
class BottomMenuSelectViewModel(
    private val useCase: BottomMenuSelectUseCase = BottomMenuSelectUseCaseImpl(),
): BaseViewModel(),
    BottomMenuSelectUseCase by useCase{
}