package com.xiaojinzi.tally.module.user.module.my.view

import com.xiaojinzi.tally.module.user.module.my.domain.MyUseCase
import com.xiaojinzi.tally.module.user.module.my.domain.MyUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer

@ViewLayer
class MyViewModel(
    private val useCase: MyUseCase = MyUseCaseImpl(),
): BaseViewModel(),
    MyUseCase by useCase{
}