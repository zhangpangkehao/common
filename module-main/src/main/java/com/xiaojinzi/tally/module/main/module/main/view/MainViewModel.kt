package com.xiaojinzi.tally.module.main.module.main.view

import com.xiaojinzi.tally.module.main.module.main.domain.MainUseCase
import com.xiaojinzi.tally.module.main.module.main.domain.MainUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer

@ViewLayer
class MainViewModel(
    private val useCase: MainUseCase = MainUseCaseImpl(),
): BaseViewModel(),
    MainUseCase by useCase{
}